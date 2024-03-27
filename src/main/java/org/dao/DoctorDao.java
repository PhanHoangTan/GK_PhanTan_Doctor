package org.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.appUtils.AppUtils;
import org.entity.Doctor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorDao {
    private static final Gson GSON = new GsonBuilder().create();
     private static Driver driver;
     private static SessionConfig sessionConfig;

    public DoctorDao(Driver driver, String dbName) {
        this.driver = AppUtils.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }

    public void close() {
        driver.close();
    }

    //Them 1 doctor moi boolean "speciality='" + speciality + '\'' +
    //                ", id='" + id + '\'' +
    //                ", name='" + name + '\'' +
    //                ", phone='" + phone + '\'' +

    public boolean addDoctor(Doctor doctor) {
        String query = "CREATE (doctor:Doctor {speciality: $speciality, id: $id, name: $name, phone: $phone})";
        Map<String, Object> map = AppUtils.convertToMap(doctor);
        try (org.neo4j.driver.Session session = driver.session(sessionConfig)) {
            session.writeTransaction(tx -> {
                tx.run(query, map);
                return null;
            });
        }
        return true;
    }
    //Thong ke bac si theo tung chuyen khoa(speciality) cua mot khoa (department) nao do khi biet ten khoa su dung
    // ham getNoOfDoctorBySpeciality(depatmentName : String) : Map<String, Long>
    //query MATCH (doctor:Doctor)-[:BELONGS_TO]->(department:Department) WHERE department.name = $name RETURN doctor.speciality as speciality, count(doctor) as count


    public Map<String, Long> getNoOfDoctorBySpeciality(String departmentName) {

        String query = "MATCH (doctor:Doctor)-[:BELONGS_TO]->(department:Department) WHERE department.name = $name RETURN doctor.speciality as speciality, count(doctor) as count";
        Map<String, Object> map = Map.of("name", departmentName);

        try (org.neo4j.driver.Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, map);
                if (!result.hasNext()) {
                    return null;
                }
                return result.stream().collect(Collectors.toMap(
                        record -> record.get("speciality").asString(),
                        record -> record.get("count").asLong()
                ));
            });
        }
    }
    //Dung full-text search de tim kiem cac bac si theo chuyen khoa
    //su dung ham listDocTorBySpeciality(keyword : String) : List<Doctor>

    //query CALL db.index.fulltext.queryNodes('doctor', $keyword) YIELD node RETURN node
    //tao db.index.fulltext.queryNodes trong neo4j
    //CREATE FULLTEXT INDEX doctor FOR (n:Doctor) ON EACH [n.speciality] SEARCH INDEX

        public List<Doctor> listDoctorBySpeciality(String keyword) {
            String query = "CALL db.index.fulltext.queryNodes('queryNodes', $keyword) YIELD node RETURN node";
            Map<String, Object> map = Map.of("keyword", keyword);
            try (org.neo4j.driver.Session session = driver.session(sessionConfig)) {
                return session.executeRead(tx -> {
                    Result result = tx.run(query, map);
                    if (!result.hasNext()) {
                        return null;
                    }
                    return result.stream().map(record -> {
                        Node node = record.get("node").asNode();
                        return AppUtils.convert(node, Doctor.class);
                    }).toList();
                });
            }
        }

//cap nhat lai chuan doan (diagnosis) cua mot luot dieu tri nao do khi biet ma so bac si va ma so benh nhan
    //luu y chi duoc phep cap nhat chuan doan cho luot dieu tri chua ket thuc( tuc ngay ket thuc bang null)

    //su dung ham updateDiagnosis(doctorID : String, patientID : String, diagnosis : String) : boolean

    //query MATCH (doctor:Doctor)-[:BE_TREATED]->(treatment:Treatment)-[:TREAT]->(patient:Patient) WHERE doctor.id = $doctorID AND patient.id = $patientID AND treatment.endDate IS NULL SET treatment.diagnosis = $diagnosis
//sua ngay endDate bang null co patientID = 1

    public boolean updateDiagnosis(String doctorID, String patientID, String diagnosis) {
        String query = "MATCH (doctor:Doctor)-[:BE_TREATED]->(treatment:Treatment)-[:TREAT]->(patient:Patient) WHERE doctor.id = $doctorID AND patient.id = $patientID AND treatment.endDate IS NULL SET treatment.diagnosis = $diagnosis";
        Map<String, Object> map = Map.of("doctorID", doctorID, "patientID", patientID, "diagnosis", diagnosis);
        try (org.neo4j.driver.Session session = driver.session(sessionConfig)) {
            session.writeTransaction(tx -> {
                tx.run(query, map);
                return null;
            });
        }
        return true;
    }





    }







