package com.rotterdam.dto;

import com.rotterdam.tools.json.deserializer.JsonCardTypeDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Created by root on 15.02.15.
 */
public class PaymentDto {
    public String cardNumber;
    @JsonDeserialize(using = JsonCardTypeDeserializer.class)
    public CardType cardType;
    public int expireMonth;
    public int expireYear;
    public int cvv2;
    public String firstName;
    public String lastName;
}
