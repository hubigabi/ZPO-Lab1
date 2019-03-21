package model;

public class Employee {

    private Integer id;
    private String name;
    private String email;
    private Integer Salary;

    public Employee(){
        this.id = 0;
        this.name = "";
        this.email = "";
        this.Salary = 0;
    }

    public Employee(Integer id, String name, String email, Integer salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.Salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Salary=" + Salary +
                '}';
    }
}
