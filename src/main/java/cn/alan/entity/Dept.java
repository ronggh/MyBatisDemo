package cn.alan.entity;

import java.io.Serializable;
import java.util.List;

// 实现序列化接口，以便MyBatis使用
public class Dept implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String deptName;

    // 部门下有很多员工
    private List<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Dept() {
    }

    public Dept(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
