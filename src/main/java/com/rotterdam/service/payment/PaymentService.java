package com.rotterdam.service.payment;

import com.paypal.api.payments.*;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.PaymentDto;

import javax.inject.Named;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by root on 15.02.15.
 */
@Named
public class PaymentService {

    private static String amountTotal = "7.47";

    private OAuthTokenCredential tokenCredential;

    public PaymentService() throws PayPalRESTException {
        InputStream is = this.getClass().getResourceAsStream("/sdk_config.properties");
        tokenCredential = Payment.initConfig(is);
    }

    public String doPayment(PaymentDto paymentDto) throws PayPalRESTException {

        String accessToken = tokenCredential.getAccessToken();
        APIContext apiContext = new APIContext(accessToken);


        Payment payment = new Payment().setIntent("sale");

        payment.setPayer(
                new Payer("credit_card").
                        setFundingInstruments(Arrays.asList(
                                new FundingInstrument().setCreditCard(new CreditCard()
                                        .setNumber(paymentDto.cardNumber)
                                        .setType(paymentDto.cardType.name())
                                        .setExpireMonth(paymentDto.expireMonth)
                                        .setExpireYear(paymentDto.expireYear)
                                        .setCvv2(paymentDto.cvv2)
                                        .setFirstName(paymentDto.firstName)
                                        .setLastName(paymentDto.lastName)))));


        Transaction transaction = new Transaction();
        transaction.setAmount(new Amount()
                .setTotal(amountTotal)
                .setCurrency("USD"))
                .setDescription("Payment for using rotterdam driving.");

        payment.setTransactions(Arrays.asList(transaction));

        Payment paymentCreated = payment.create(apiContext);

        return paymentCreated.getId();
    }

    public boolean isApproved(String paymentId) throws PayPalRESTException {
        String accessToken = tokenCredential.getAccessToken();
        Payment payment = Payment.get(accessToken, paymentId);

        return payment.getState().equals("approved");
    }
}
