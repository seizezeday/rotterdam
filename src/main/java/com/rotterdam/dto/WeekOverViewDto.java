package com.rotterdam.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by vasax32 on 19.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekOverViewDto {
    public OverViewDetailDto detail;
    public StartEndDto startEnd;

    @Override
    public String toString() {
        return "WeekDto{" +
                '}';
    }
}

