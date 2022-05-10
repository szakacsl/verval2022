package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository homeworkXMLRepository = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository gradeXMLRepository = new GradeXMLRepository(gradeValidator, "grades.xml");
        service = new Service(studentXMLRepository, homeworkXMLRepository, gradeXMLRepository);
        service.saveHomework("6", "C", 3, 1);
        service.saveStudent("6", "Bela", 533);
    }

    @AfterEach
    void clean() {
        List<Homework> homeworks = new ArrayList<>();
        service.findAllHomework().forEach(homeworks::add);
        for (Homework homework : homeworks) {
            service.deleteHomework(homework.getID());
        }

        List<Student> students = new ArrayList<>();
        service.findAllStudents().forEach(students::add);
        for (Student student : students) {
            service.deleteStudent(student.getID());
        }
    }

    @Test
    void saveHomework() {
        assertEquals(1, service.saveHomework("1", "C", 3, 1), "saveStudent OK");
    }

    @Test
    void saveStudent() {
        assertEquals(1, service.saveStudent("1", "Bela", 533), "saveStudent OK");
    }

    @Test
    void deleteHomework() {
        assertTrue(service.deleteHomework("6") == 1);
    }

    @Test
    void deleteStudent() {
        assertNotEquals(0, service.deleteStudent("6"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "1", "0", "-1", "42"})
    void saveHomeworkParametrized(String stringId) {
        assertEquals(1, service.saveHomework(stringId, "C", 3, 1), "saveStudent OK");
    }

}