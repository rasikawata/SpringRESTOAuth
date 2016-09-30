package com.springapp.mvc.model;

import java.io.Serializable;

/**
 * Created by rasikaw on 11/19/2015.
 */
public class MyDto implements Serializable{

    private String name;
    private String school;

    private FeatureStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public FeatureStatus getStatus() {
        return status;
    }

    public void setStatus(FeatureStatus status) {
        this.status = status;
    }
}
