package com.rotterdam.dto;

import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 29.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeclarationsDto {
    public List<DayDeclarationDto> daysDeclaration = new ArrayList<>();
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date date;

    public DeclarationsDto() {
    }

    public DeclarationsDto(List<DayDeclarationDto> daysDeclaration) {
        this.daysDeclaration = daysDeclaration;
    }
}
