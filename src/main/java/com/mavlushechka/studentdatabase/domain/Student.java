package com.mavlushechka.studentdatabase.domain;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String diploma;
    private long telephoneNumber;
    private String religion;
    private String car;
    private String house;
    private Binary photo;
    private String encodedPhoto;

    public Student() {
    }

    public Student(String id, String diploma, long telephoneNumber, String religion, String car, String house, Binary photo) {
        this.id = id;
        this.diploma = diploma;
        this.telephoneNumber = telephoneNumber;
        this.religion = religion;
        this.car = car;
        this.house = house;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Binary getPhoto() {
        return photo;
    }

    public void setPhoto(Binary photo) {
        this.photo = photo;
    }

    public String getEncodedPhoto() {
        return encodedPhoto;
    }

    public void setEncodedPhoto(String encodedPhoto) {
        this.encodedPhoto = encodedPhoto;
    }
}
