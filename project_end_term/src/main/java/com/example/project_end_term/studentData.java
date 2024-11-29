package com.example.project_end_term;

import java.sql.Date;


public class studentData {
    private int studentId;
    private String year;
    private String course;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birth;
    private String status;
    private String image;
    private double firstSem;
    private double secondSem;
    private double finals;

    public studentData(int studentId, String year, String course, String firstName, String lastName, String gender, Date birth, String status, String image) {
        this.studentId = studentId;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.image = image;
    }

    public studentData(int studentId, String year, String course, double firstSem, double secondSem, double finals) {
        this.studentId = studentId;
        this.year = year;
        this.course = course;
        this.firstSem = firstSem;
        this.secondSem = secondSem;
        this.finals = finals;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public double getFirstSem() {
        return firstSem;
    }

    public double getSecondSem() {
        return secondSem;
    }

    public double getFinals() {
        return finals;
    }
}
