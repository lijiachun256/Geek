package geek.model;

import lombok.Data;

@Data
public class Student {
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
