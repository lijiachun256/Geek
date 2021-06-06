package geek.week.week58;

import geek.week.week58.demo.Klass;
import geek.week.week58.demo.School;
import geek.week.week58.demo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Week58AutoConfiguration {

    @Bean
    public Student student() {
        return new Student();
    }

    @Bean
    public Klass klass() {
        return new Klass();
    }

    @Bean
    public School school() {
        return new School();
    }
}
