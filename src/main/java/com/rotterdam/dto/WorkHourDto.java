package com.rotterdam.dto;

import com.rotterdam.model.entity.RideType;
import com.rotterdam.tools.json.serializer.JsonRideTypeSerializer;
import com.rotterdam.tools.json.serializer.JsonTimeSerializer;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by root on 19.01.15.
 */
public class WorkHourDto {
    @JsonSerialize(using= JsonTimeSerializer.class)
    public Date startWorkingTime;
    @JsonSerialize(using= JsonTimeSerializer.class)
    public Date endWorkingTime;
    public int restTime;
    @JsonSerialize(using=JsonRideTypeSerializer.class)
    @JsonProperty("dayType")
    public RideType rideType;

    @Override
    public String toString() {
        return "WorkHourDto{" +
                "startWorkingTime=" + new SimpleDateFormat("hh:mm").format(startWorkingTime) +
                ", endWorkingTime=" + new SimpleDateFormat("hh:mm").format(endWorkingTime) +
                ", restTime=" + restTime +
                ", rideType=" + rideType +
                '}';
    }

    public static WorkHourDtoComparatorByStartWorkingTime workHourDtoComparatorByStartWorkingTime =
            new WorkHourDtoComparatorByStartWorkingTime();

    private static class WorkHourDtoComparatorByStartWorkingTime implements Comparator<WorkHourDto>{

        @Override
        public int compare(WorkHourDto o1, WorkHourDto o2) {
            return o1.startWorkingTime.compareTo(o2.startWorkingTime);
        }
    }
}
