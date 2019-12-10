package com.krt.system.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "person.addres")
public class Addres {

    private String school;
    private String home;

    public Addres() {
    }

    public Addres(String school, String home) {
        this.school = school;
        this.home = home;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Addres{" +
                "school='" + school + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
