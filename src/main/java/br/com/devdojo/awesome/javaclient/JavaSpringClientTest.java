package br.com.devdojo.awesome.javaclient;

import br.com.devdojo.awesome.model.Student;

import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {

        Student studentPost = new Student();
        studentPost.setName("John Wick");
        studentPost.setEmail("John@pencil.com");
        JavaClientDao dao = new JavaClientDao();
        System.out.println(dao.findById(1));
        List<Student> students = dao.listAll();
        System.out.println(dao.save(studentPost));
        System.out.println(students.size());

    }
}
