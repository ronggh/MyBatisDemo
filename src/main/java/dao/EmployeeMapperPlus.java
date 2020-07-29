package dao;

import entity.Employee;


public interface EmployeeMapperPlus {
    // 查询单条数据,返回结果集用自定义的resultMap进行封装
    public Employee getEmployeeById(int id);

    // 联合查询：查询员工及对应的部门
    public Employee getEmployeeAndDept(int id);

    // 使用association进行分步查询
    public Employee getEmployeeByIdStep(int id);

}
