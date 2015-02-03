package com.rotterdam.dto;

import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.RideType;
import com.rotterdam.model.entity.WorkHour;
import com.rotterdam.tools.json.serializer.JsonRideTypeSerializer;
import com.rotterdam.tools.json.serializer.JsonTimeSerializer;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 19.01.15.
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

    public static boolean isDayChanged(List<WorkHourDto> workHourDtos, List<WorkHour> workHours){
        if(workHours == null || workHourDtos == null)
            return true;
        if(workHourDtos.size() != workHours.size())
            return true;

        for(int i = 0; i < workHours.size(); i++){
            WorkHour workHour = workHours.get(i);
            WorkHourDto workHourDto = workHourDtos.get(i);
            if(!workHour.getStartWorkingTime().equals(workHourDto.startWorkingTime))
                return true;
            if(!workHour.getEndWorkingTime().equals(workHourDto.endWorkingTime))
                return true;
            if(!new Integer(workHour.getRestTime()).equals(workHourDto.restTime))
                return true;
            if(!workHour.getRideType().equals(workHourDto.rideType))
                return true;
        }
        return false;
    }

    public static List<WorkHour> convertDayToWorkHour(List<WorkHourDto> workHourDtos, Day dayEntity){
        List<WorkHour> workHours = new ArrayList<>();
        for(WorkHourDto workHourDto : workHourDtos){
            WorkHour workHour = new WorkHour();
            workHour.setDay(dayEntity);
            workHour.setStartWorkingTime(workHourDto.startWorkingTime);
            workHour.setEndWorkingTime(workHourDto.endWorkingTime);
            workHour.setRestTime(workHourDto.restTime);
            workHour.setRideType(workHourDto.rideType);
            workHours.add(workHour);
        }
        return workHours;
    }
}
