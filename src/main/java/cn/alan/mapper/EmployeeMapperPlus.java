package cn.alan.mapper;

import cn.alan.entity.Employee;

import java.util.List;


public interface EmployeeMapperPlus {
    // 查询单条数据,返回结果集用自定义的resultMap进行封装
    public Employee getEmployeeById(int id);

    // 联合查询：查询员工及对应的部门
    public Employee getEmployeeAndDept(int id);

    // 使用association进行分步查询
    public Employee getEmployeeByIdStep(int id);

    // 按照部门id查找所有员工
    public List<Employee> getEmployeeListByDeptId(int deptId);

    // 使用鉴别器
    public Employee getEmployeeById2(int id);

}
