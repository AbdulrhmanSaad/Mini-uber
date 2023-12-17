package com.example.uber;

public class Employee extends User{
    private String name;
    private String email;
    private String phoneNumber;
    private static Employee  employee=null;

    private Employee() {
    }

    public static Employee getInstance(String name,String email,String phoneNumber){
        if(employee==null){
            employee=new Employee();
            employee.setName(name);
            employee.setEmail(email);
            employee.setPhoneNumber(phoneNumber);
        }
        return employee;
    }


    @Override
    public String getName() {
        return name;

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
