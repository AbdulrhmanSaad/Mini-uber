package com.example.uber;

public class ModuleOption {
    public static final int EMPLOYEE = 1;
    public static final int RIDER = 2;
    public static final int DRIVER = 3;
    public static final int CAR = 4;


    public static String getReferenceName(int moduleOption) {
        if (moduleOption == ModuleOption.EMPLOYEE) {
            return "Employees";
        } else if (moduleOption == ModuleOption.RIDER) {
            return "Riders";
        } else if (moduleOption == ModuleOption.DRIVER) {
            return "Drivers";
        } else if (moduleOption == ModuleOption.CAR) {
            return "Cars";
        }
        return "";

    }
}
