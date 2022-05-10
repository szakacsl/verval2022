package service;

import domain.Homework;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceMockTest {
    private Service service;

    @Mock
    private StudentXMLRepository studentXMLRepository;

    @Mock
    private HomeworkXMLRepository homeworkXMLRepository;

    @Mock
    private GradeXMLRepository gradeXMLRepository;

    @BeforeEach
    void init() {
        studentXMLRepository = Mockito.mock(StudentXMLRepository.class);
        homeworkXMLRepository = Mockito.mock(HomeworkXMLRepository.class);
        gradeXMLRepository = Mockito.mock(GradeXMLRepository.class);

        this.service = new Service(studentXMLRepository, homeworkXMLRepository, gradeXMLRepository);
    }

    @Test
    void updateHomeworkResultCode() {
        Homework dummyHomework = new Homework("1", "default", 1, 2);
        Mockito.when(homeworkXMLRepository.update(dummyHomework)).thenReturn(dummyHomework);
        assertEquals(1, this.service.updateHomework("1", "default", 1, 2));
    }

    @Test
    void saveHomeworkResultCode() {
        Homework dummyHomework = new Homework("2", "default", 1, 2);
        Mockito.when(homeworkXMLRepository.save(dummyHomework)).thenReturn(null);
        assertEquals(1, this.service.saveHomework("2", "default", 1, 2));
    }

    @Test
    void deleteHomeworkResultCode() {
        Homework dummyHomework = new Homework("2", "default", 1, 2);
        Mockito.when(homeworkXMLRepository.delete("2")).thenReturn(dummyHomework);
        assertEquals(1, this.service.deleteHomework("2"));
    }

}
