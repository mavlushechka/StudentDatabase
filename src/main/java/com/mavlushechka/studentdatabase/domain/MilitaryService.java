package com.mavlushechka.studentdatabase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "militaryServices")
public class MilitaryService {
    @Id
    private String id;
    private long number;
    private String type;
    private String place;
    private String year;
    private String serialNumber;
    private boolean alternative;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isAlternative() {
        return alternative;
    }

    public void setAlternative(boolean alternative) {
        this.alternative = alternative;
    }
}
