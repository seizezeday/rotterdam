package com.rotterdam.dto;

import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.Declaration;
import com.rotterdam.tools.json.JsonCommands;
import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import com.rotterdam.tools.json.serializer.JsonTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 19.01.15.
 */
public class DayDeclarationDto {
    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date date;
    public List<Declaration> declarations = new ArrayList<>();
    @JsonSerialize(using = JsonTimeSerializer.class)
    public Date total;

    public DayDeclarationDto() {
    }

    public DayDeclarationDto(Day day){
        this.date = day.getDate();
        this.declarations = new ArrayList<>(day.getDeclarations());
    }

    @Override
    public String toString() {
        return "DayDto{" +
                "date=" + new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN).format( date) +
                ", declarations=" + declarations +
                '}';
    }
}
