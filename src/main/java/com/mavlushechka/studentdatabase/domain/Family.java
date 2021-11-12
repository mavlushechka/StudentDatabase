package com.mavlushechka.studentdatabase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "families")
public class Family {
    @Id
    private String id;
    private String parentsDivorced;
    private boolean isMarried;
    private Parent father;
    private Parent mother;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentsDivorced() {
        return parentsDivorced;
    }

    public void setParentsDivorced(String parentsDivorced) {
        this.parentsDivorced = parentsDivorced;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }
}
