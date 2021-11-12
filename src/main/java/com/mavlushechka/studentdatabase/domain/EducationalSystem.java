package com.mavlushechka.studentdatabase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "educationalSystems")
public class EducationalSystem {
    @Id
    private String id;
    private boolean exists;
    private byte number;
    private String degree;
    private byte course;
    private String formOfLearning;
    private String language;
    private short year;
    private String faculty;
    private int directionCode;
    private String directionName;
    private String group;
    private String formOfEducation;
    private String privilege;
    private String typeOfPlace;
    private String region;
    private String city;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public byte getCourse() {
        return course;
    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public String getFormOfLearning() {
        return formOfLearning;
    }

    public void setFormOfLearning(String formOfLearning) {
        this.formOfLearning = formOfLearning;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(int directionCode) {
        this.directionCode = directionCode;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(String formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getTypeOfPlace() {
        return typeOfPlace;
    }

    public void setTypeOfPlace(String typeOfPlace) {
        this.typeOfPlace = typeOfPlace;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
