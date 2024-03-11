package com.example.ABC.Laboratories.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String FirstName;
    private String LastName;
    private String Speciality;
    private String MobileNo;

    public Doctor(){}

    public Doctor(int id,String FirstName,String LastName,String Speciality,String MobileNo) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Speciality = Speciality;
        this.MobileNo = MobileNo;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String Speciality) {
        this.Speciality = Speciality;
    }

    public String getMobile() {
        return MobileNo;
    }

    public void setMobile(String MobileNo) {
        this.MobileNo = MobileNo;
    }
    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", hospital='" + Speciality + '\'' +
                ", mobile='" + MobileNo + '\'' +
                '}';
    }
}
