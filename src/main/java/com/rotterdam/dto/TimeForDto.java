package com.rotterdam.dto;

/**
 * Created by root on 22.01.15.
 */
public class TimeForDto {
    public double timeForTime;
    public double timeForPay;

    public TimeForDto() {
    }

    public TimeForDto(double timeForTime, double timeForPay) {
        this.timeForTime = timeForTime;
        this.timeForPay = timeForPay;
    }

    @Override
    public String toString() {
        return "TimeForDto{" +
                "timeForTime=" + timeForTime +
                ", timeForPay=" + timeForPay +
                '}';
    }
}
