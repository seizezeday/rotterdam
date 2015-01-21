package com.rotterdam.tools.json.serializer;

import com.rotterdam.model.entity.RideType;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by root on 20.01.15.
 */
@Component
@Scope("singleton")
public class JsonRideTypeSerializer extends JsonSerializer<RideType> {

    @Override
    public void serialize(RideType rideType, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedRideType = (rideType.ordinal()+1) + "";

        gen.writeString(formattedRideType);
    }
}
