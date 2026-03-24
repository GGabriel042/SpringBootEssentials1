package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.assertj.core.api.AbstractCharSequenceAssert;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createShouldPersistData() {
        Student student = new Student("William", "William@devdojo.com");
        this.studentRepository.save(student);
        Assertions.assertThat(student.getId()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo("William");
        Assertions.assertThat(student.getEmail()).isEqualTo("William@devdojo.com");
    }

    @Test
    public void deleteShouldRemoveData(){
        Student student = new Student("William", "William@devdojo.com");
        this.studentRepository.save(student);
        studentRepository.delete(student);
        Assertions.assertThat(studentRepository.findOne(student.getId())).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData(){
        Student student = new Student("William", "William@devdojo.com");
        this.studentRepository.save(student);
        student.setName("William2");
        student.setEmail("William2@devdojo.com");
        this.studentRepository.save(student);
        student = this.studentRepository.findOne(student.getId());
        Assertions.assertThat(student.getName()).isEqualTo("William2");
        Assertions.assertThat(student.getEmail()).isEqualTo("William2@devdojo.com");
    }


    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase(){
        Student student = new Student("William", "William@devdojo.com");
        Student student2 = new Student("william", "William2@devdojo.com");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);
        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("william");
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }


    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo nome do estudante é obrigatório");
        this.studentRepository.save(new Student());

    }


    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        Student student = new Student();
        student.setName("William");
        this.studentRepository.save(student);
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido");
        Student student = new Student();
        student.setName("William");
        student.setEmail("William");
        this.studentRepository.save(student);
    }
}
