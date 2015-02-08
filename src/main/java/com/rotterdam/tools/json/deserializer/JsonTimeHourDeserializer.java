package com.rotterdam.tools.json.deserializer;

import com.rotterdam.tools.json.JsonCommands;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vasax32 on 21.01.15.
 */
@Component
@Scope("singleton")
public class JsonTimeHourDeserializer extends JsonDeserializer<Date> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JsonCommands.PARAM_HOUR_PATTERN);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String time = jp.getText();
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            try {
                return simpleDateFormat.parse("00");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
