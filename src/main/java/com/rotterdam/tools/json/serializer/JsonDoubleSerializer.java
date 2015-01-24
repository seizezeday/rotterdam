package com.rotterdam.tools.json.serializer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by root on 20.01.15.
 */
@Component
@Scope("singleton")
public class JsonDoubleSerializer extends JsonSerializer<Double> {

    private final DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void serialize(Double num, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedNum = df.format(num);

        gen.writeString(formattedNum);
    }
}
