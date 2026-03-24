package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;

public class JavaSpringClientTest {
    public static void main(String[] args) {

        Student studentPost = new Student();
        studentPost.setName("Joao das flores");
        studentPost.setEmail("JohnFlores@pencil.com");
        studentPost.setId(15L);
        JavaClientDao dao = new JavaClientDao();
//        System.out.println(dao.findById(112));
//        List<Student> students = dao.listAll();
//        System.out.println(dao.save(studentPost));
//        System.out.println(students.size());

//        dao.update(studentPost);
        dao.delete(14);
    }
}
