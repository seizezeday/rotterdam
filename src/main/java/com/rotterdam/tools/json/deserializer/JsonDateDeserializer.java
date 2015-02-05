package com.rotterdam.tools.json.deserializer;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vasax32 on 21.01.15.
 */
@Component
@Scope("singleton")
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateString = jp.getText();

        Date date = null;
        try {
            date = new Date(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                date = format.parse(dateString);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return date;
    }
}
