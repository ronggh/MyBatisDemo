package dao;

import entity.Employee;

public interface EmployeeMapper {
    // 查询单条数据
    public Employee getEmpById(int id);
    // 增删改返回结果可以定义为void、boolean、int、long及其包装类
    // 增加一个员工
    public Long addEmployee(Employee employee);
    // 更新一个员工
    public Boolean updateEmployee(Employee employee);
    // 删除一个员工
    public void deleteEmployee(int id);
}
