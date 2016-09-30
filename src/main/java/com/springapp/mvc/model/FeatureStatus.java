package com.springapp.mvc.model;

import org.codehaus.jackson.annotate.JsonCreator;

public enum FeatureStatus {
    Activated(0),
    Inactivated(1),
    Suspended(2),
    Deleted(3);

    private FeatureStatus(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public FeatureStatus valueOf(int id) {
        switch(id) {
            case 1: return Inactivated;
            case 2: return Suspended;
            case 3: return Deleted;
            default: return Activated;
        }
    }

    @JsonCreator
    public static FeatureStatus fromValue(String status) {
        if(status != null) {
            for(FeatureStatus featureStatus : FeatureStatus.values()) {
                if(featureStatus.toString().equals(status)) {
                    return featureStatus;
                }
            }

            throw new IllegalArgumentException(status + " is an invalid value.");
        }

        throw new IllegalArgumentException("A value was not provided.");
    }
}