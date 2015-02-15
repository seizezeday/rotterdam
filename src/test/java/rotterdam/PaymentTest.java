package rotterdam;

import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.CardType;
import com.rotterdam.dto.PaymentDto;
import com.rotterdam.service.payment.PaymentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 15.02.15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class PaymentTest {

//    @Inject
    //private PaymentService paymentService;

    @Test
    public void basicPayment() throws PayPalRESTException {
        PaymentService paymentService = new PaymentService();


        PaymentDto paymentDto = new PaymentDto();
        paymentDto.cardNumber = "4032038157997366";
        paymentDto.cardType = CardType.visa;
        paymentDto.expireMonth = 2;
        paymentDto.expireYear = 2020;
        paymentDto.cvv2 = 123;
        paymentDto.firstName = "Joe";
        paymentDto.lastName = "Shopper";

        String paymentId = paymentService.doPayment(paymentDto);

        Assert.assertTrue(paymentService.isApproved(paymentId));

    }
}
