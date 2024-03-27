package org.entity;

public class Patient extends Person{
//        "address": "New Yorks",

//                "PatientID": "1",

//                "dateOfBirth": "5/15/1990"
    private String address;
    private String PatientID;
    private String dateOfBirth;

    public Patient(String id, String name, String phone, String address, String PatientID, String dateOfBirth) {
        super(id, name, phone);
        this.address = address;
        this.PatientID = PatientID;
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "address='" + address + '\'' +
                ", PatientID='" + PatientID + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
