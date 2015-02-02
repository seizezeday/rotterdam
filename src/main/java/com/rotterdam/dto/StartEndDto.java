package com.rotterdam.dto;

import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Created by root on 02.02.15.
 */
public class StartEndDto {
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date start;
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date end;

    public StartEndDto(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public StartEndDto() {
    }
}
