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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
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
        Student student2 = new Student("William2", "William2@devdojo.com");
        this.studentRepository.save(student);
        student.setName("William2");
        student.setEmail("William2@devdojo.com");
        student = this.studentRepository.save(student);
        Assertions.assertThat(student.getName()).isEqualTo("William2");
        Assertions.assertThat(student.getEmail()).isEqualTo("William2@devdojo.com");
    }
}
