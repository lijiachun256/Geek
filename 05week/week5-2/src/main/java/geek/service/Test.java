package geek.service;

import geek.model.Student;
import geek.model.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());

        Teacher teacher = (Teacher) context.getBean("teacher");
        System.out.println(teacher.toString());

    }
}
