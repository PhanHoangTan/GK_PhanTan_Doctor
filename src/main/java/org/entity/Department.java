package org.entity;

public class Department {
//  "name": "City General",
//          "location": "123 Main Street",
//          "DeptID": "1"
    private String name;
    private String location;
    private String DeptID;

    public Department(String name, String location, String DeptID) {
        this.name = name;
        this.location = location;
        this.DeptID = DeptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String DeptID) {
        this.DeptID = DeptID;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", DeptID='" + DeptID + '\'' +
                '}';
    }
}
