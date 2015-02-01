package com.rotterdam.dto;

/**
 * Created by vasax32 on 22.01.15.
 */
public class TimeForDto {
    public double timeForTime;
    public double overTime;

    public TimeForDto() {
    }

    public TimeForDto(double timeForTime, double overTime) {
        this.timeForTime = timeForTime;
        this.overTime = overTime;
    }

    @Override
    public String toString() {
        return "TimeForDto{" +
                "timeForTime=" + timeForTime +
                ", overTime=" + overTime +
                '}';
    }
}
