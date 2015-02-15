package com.rotterdam.tools.json.deserializer;

import com.rotterdam.dto.CardType;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by vasax32 on 21.01.15.
 */
@Component
@Scope("singleton")
public class JsonCardTypeDeserializer extends JsonDeserializer<CardType> {


    @Override
    public CardType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String typeString = jp.getText();

        try{
            int typeInteger = Integer.parseInt(typeString);
            return CardType.values()[typeInteger];
        } catch (NumberFormatException e){
            return null;
        }
    }
}
