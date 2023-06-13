package ExceptionsAndLoggingAssignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StudentRepositoryTest {
    private StudentRepository studentRepository;
    private Student student;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
        student = new Student("Josh", "Doe", LocalDate.of(1985, 2, 10), "Male", "1234567891");
        Student student2 = new Student("Karl", "Smith", LocalDate.of(1980, 3, 13), "Male", "9876543212");
        Student student3 = new Student("Mark", "Joe", LocalDate.of(1990, 7, 15), "Male", "2468135793");
    }

    @Test
    void addStudentShouldPass() {
        studentRepository.addStudent(student);
        List<Student> students = studentRepository.getStudents();
        Assertions.assertEquals(students.size(), 1);

    }

    @Test
    void addStudentShouldThrowException() {
        Student student = new Student("Jack", "Sparrow", LocalDate.of(1990, 3, 30), "Male", "");
        assertThrows(IllegalArgumentException.class, () -> studentRepository.addStudent(student));
    }


    @Test
    void deleteStudentByIdShouldPass() {
        studentRepository.addStudent(student);
        studentRepository.deleteStudentById("1234567891");
        List<Student> students = studentRepository.getStudents();
        assertFalse(students.contains(student));

    }

    @Test
    void deleteStudentByIdShouldThrowException() {
        studentRepository.addStudent(student);
        student.setId("");
        assertThrows(IllegalArgumentException.class, () -> studentRepository.deleteStudentById("1234567891"));
    }

    @Test
    void retrieveStudentsWithAge() {
        studentRepository.addStudent(student);
        List<Student> studentsWithAge38 = studentRepository.retrieveStudentsWithAge(38);
        Assertions.assertEquals(1, studentsWithAge38.size());
    }

    @Test
    void retrieveStudentsWithAgeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> studentRepository.retrieveStudentsWithAge(-38));
    }


    @Test
    void listStudentsOrderByLastName() {
        Student student1 = new Student("Josh", "Doe", LocalDate.of(1985, 2, 10), "Male", "1234567891");
        Student student2 = new Student("Karl", "Smith", LocalDate.of(1980, 3, 13), "Male", "9876543212");
        Student student3 = new Student("Mark", "Joe", LocalDate.of(1990, 7, 15), "Male", "2468135793");

        studentRepository.addStudent(student1);
        studentRepository.addStudent(student2);
        studentRepository.addStudent(student3);

        List<Student> sortingMethod = studentRepository.listStudentsOrderByLastName();

        List<Student> students = studentRepository.getStudents();

        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparing(Student::getLastName));


        Assertions.assertEquals(sortedStudents, sortingMethod);
    }


    @Test
    void listStudentsOrderByBirthDate() {
        Student student1 = new Student("Josh", "Doe", LocalDate.of(1985, 2, 10), "Male", "1234567891");
        Student student2 = new Student("Karl", "Smith", LocalDate.of(1980, 3, 13), "Male", "9876543212");
        Student student3 = new Student("Mark", "Joe", LocalDate.of(1990, 7, 15), "Male", "2468135793");

        studentRepository.addStudent(student1);
        studentRepository.addStudent(student2);
        studentRepository.addStudent(student3);

        List<Student> sortingMethod = studentRepository.listStudentsOrderByBirthDate();

        List<Student> students = studentRepository.getStudents();

        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparing(Student::getDateOfBirth));
        Assertions.assertEquals(sortedStudents, sortingMethod);

    }
}