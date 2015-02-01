package com.rotterdam.tools.json.serializer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by vasax32 on 20.01.15.
 */
@Component
@Scope("singleton")
public class JsonDoubleTimeSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double num, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        int h = num.intValue();

        int m = (int)((num -h) * 60);

        gen.writeString(h + "h " + m + "m");
    }
}
