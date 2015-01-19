package com.rotterdam.dto;

import com.rotterdam.model.entity.RideType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 19.01.15.
 */
public class WorkHourDto {
    public Date startWorkingTime;
    public Date endWorkingTime;
    public int restTime;
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
}
