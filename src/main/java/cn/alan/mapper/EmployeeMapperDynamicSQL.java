package cn.alan.mapper;

import cn.alan.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmployeeByIf(Employee employee);
    public List<Employee> getEmployeeByTrim(Employee employee);
    public List<Employee> getEmployeeByChoose(Employee employee);
    public void updateEmployee(Employee employee);
    public List<Employee> getEmployeeByForEach(@Param("ids") List<Integer> ids);
    // 批量增加
    public void addEmployees(@Param("employees") List<Employee> employees);

    // 内部参数
    public List<Employee> testInnerParam(Employee employee);
}
