package com.rotterdam.dto;

import com.rotterdam.model.entity.Declaration;
import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 29.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeclarationsDto {
    public List<Declaration> declarations = new ArrayList<>();
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date date;

    public DeclarationsDto() {
    }

    public DeclarationsDto(List<Declaration> declarations) {
        this.declarations = declarations;
    }
}
