package com.rotterdam.service.payment;

import com.paypal.api.payments.*;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.PaymentResultDto;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 15.02.15.
 */
@Named
public class PaymentService {

    @Inject
    private UserDao userDao;

    private static String amountTotal = "7.47";

    private OAuthTokenCredential tokenCredential;

    public PaymentService() throws PayPalRESTException {
        InputStream is = this.getClass().getResourceAsStream("/sdk_config.properties");
        tokenCredential = Payment.initConfig(is);
    }

    public PaymentResultDto doPayment(String domain) throws PayPalRESTException {

        //domain = getOriginFromDomain(domain);

        String accessToken = tokenCredential.getAccessToken();
        APIContext apiContext = new APIContext(accessToken);


        Payment payment = new Payment().setIntent("sale");

//        payment.setPayer(
//                new Payer("credit_card").
//                        setFundingInstruments(Arrays.asList(
//                                new FundingInstrument().setCreditCard(new CreditCard()
//                                        .setNumber(paymentDto.cardNumber)
//                                        .setType(paymentDto.cardType.name())
//                                        .setExpireMonth(paymentDto.expireMonth)
//                                        .setExpireYear(paymentDto.expireYear)
//                                        .setCvv2(paymentDto.cvv2)
//                                        .setFirstName(paymentDto.firstName)
//                                        .setLastName(paymentDto.lastName)))));
        payment.setPayer(
                new Payer("paypal"));

        payment.setRedirectUrls(
                new RedirectUrls().
                        setReturnUrl(domain + "/api/finishPayment").
                        setCancelUrl(domain + "/"));


        Transaction transaction = new Transaction();
        transaction.setAmount(new Amount()
                .setTotal(amountTotal)
                .setCurrency("USD"))
                .setDescription("Payment for using rotterdam driving.");

        payment.setTransactions(Arrays.asList(transaction));

        Payment paymentCreated = payment.create(apiContext);

        String approvalUrl = paymentCreated.getLinks().get(1).getHref();
        String executeUrl = paymentCreated.getLinks().get(2).getHref();
        return new PaymentResultDto(approvalUrl, executeUrl, paymentCreated.getId());
    }

    public boolean isApproved(String paymentId) throws PayPalRESTException {
        String accessToken = tokenCredential.getAccessToken();
        Payment payment = Payment.get(accessToken, paymentId);

        return payment.getState().equals("approved");
    }

    public void execute(String paymentId, String payerId) throws PayPalRESTException {
        String accessToken = tokenCredential.getAccessToken();
        Payment payment = Payment.get(accessToken, paymentId);
        APIContext apiContext = new APIContext(accessToken);
        payment.execute(apiContext, new PaymentExecution(payerId));
    }

    @Transactional
    public void finishPayment(String paymentId, String payerId) throws PayPalRESTException {
        execute(paymentId, payerId);
        if(isApproved(paymentId)) {
            User user = userDao.selectByPaymentId(paymentId);
            user.setLastPaymentDate(new Date());
            userDao.update(user);
        }
    }

    private String getOriginFromDomain(String domain) {
        Pattern p = Pattern.compile(".*?([^.]+\\.[^.]+)");
        URI uri = null;
        try {
            uri = new URI(domain);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Matcher m = p.matcher(uri.getHost());
        if (m.matches()) {
            return m.group(1);
        } else return "";
    }
}
