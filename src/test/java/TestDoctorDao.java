import org.appUtils.AppUtils;
import org.dao.DoctorDao;
import org.entity.Doctor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDoctorDao {
    public static String DB_NAME = "tan21034591";
    private DoctorDao doctorDao;
    @BeforeAll
    public void setUp() {
        doctorDao = new DoctorDao(AppUtils.initDriver(), DB_NAME);
    }
    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor("0008", "Dr. John", "1234567890", "Cardiology");
        doctorDao.addDoctor(doctor);
            System.out.println("Thêm thành công");

    }
    @Test
    public void getNoOfDoctorBySpeciality() {
        doctorDao.getNoOfDoctorBySpeciality("Mercy Hospital").forEach((k, v) -> System.out.println(k + " : " + v));
    }
    @Test
    public void listDoctorBySpeciality() {
        doctorDao.listDoctorBySpeciality("Cardiology").forEach(System.out::println);
    }
    @Test
    public void updateDiagnosis() {
        doctorDao.updateDiagnosis("1", "6","Xau");
        System.out.println("Cập nhật thành công");
    }

    @AfterAll
    public void tearDown() {
        doctorDao.close();
    }

}
