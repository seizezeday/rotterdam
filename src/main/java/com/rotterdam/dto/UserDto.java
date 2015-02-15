package com.rotterdam.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by vasax32 on 21.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    public String Name;
    public String LastName;
    public String email;
    public String pass;
    public String passconfirm;
    public String regNum;
    public PaymentDto payment;

    public UserDto(String name, String lastName, String regNum) {
        this.Name = name;
        this.LastName = lastName;
        this.regNum = regNum;
    }

    public UserDto() {
    }
}
