package com.rotterdam.tools.json.serializer;

import com.rotterdam.tools.json.JsonCommands;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vasax32 on 20.01.15.
 */
@Component
@Scope("singleton")
public class JsonTimeHourSerializer extends JsonSerializer<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(JsonCommands.PARAM_HOUR_PATTERN);

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }
}
