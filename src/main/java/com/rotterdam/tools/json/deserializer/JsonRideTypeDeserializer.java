package com.rotterdam.tools.json.deserializer;

import com.rotterdam.model.entity.RideType;
import com.rotterdam.tools.json.JsonCommands;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by vasax32 on 21.01.15.
 */
@Component
@Scope("singleton")
public class JsonRideTypeDeserializer extends JsonDeserializer<RideType> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JsonCommands.PARAM_HOUR_PATTERN);

    @Override
    public RideType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String typeString = jp.getText();

        try{
            int typeInteger = Integer.parseInt(typeString);
            return RideType.values()[typeInteger];
        } catch (NumberFormatException e){
            return null;
        }
    }
}
