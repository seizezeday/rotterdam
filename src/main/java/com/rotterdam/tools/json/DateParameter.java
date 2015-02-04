package com.rotterdam.tools.json;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 04.02.15.
 */
public class DateParameter implements Serializable {

   private static final SimpleDateFormat df = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN);

    public static DateParameter valueOf(String dateString) throws ParseException {
        Date date;
        try {
            date = new Date(dateString); // yes, I know this is a deprecated method
        } catch(Exception e) {
            date = df.parse(dateString);
        }
        return new DateParameter(date);
    }

    private Date date;

    public DateParameter(Date date) {
        this.date = date;
    }

    public DateParameter() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
