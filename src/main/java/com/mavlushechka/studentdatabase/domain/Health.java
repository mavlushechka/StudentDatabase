package com.mavlushechka.studentdatabase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "health")
public class Health {
    @Id
    private String id;
    private boolean invalid;
    private int invalidGroup;
    private String invalidDiagnosis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public int getInvalidGroup() {
        return invalidGroup;
    }

    public void setInvalidGroup(int invalidGroup) {
        this.invalidGroup = invalidGroup;
    }

    public String getInvalidDiagnosis() {
        return invalidDiagnosis;
    }

    public void setInvalidDiagnosis(String invalidDiagnosis) {
        this.invalidDiagnosis = invalidDiagnosis;
    }
}
