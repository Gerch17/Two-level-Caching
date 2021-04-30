import java.io.Serializable;

public class Employee implements Serializable {
    private String position;
    private int age;

    public Employee(String position, int age) {
        this.position = position;
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
