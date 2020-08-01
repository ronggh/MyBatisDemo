package cn.alan.mapper;

import cn.alan.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    // 查询，传多个参数
    public Employee getEmployeeByIdAndLastName(int id, String lastName);

    // 查询，传多个参数，使用命名参数的方式
    public Employee getEmployeeByIdAndLastName2(@Param("id") int id, @Param("lastName") String lastName);

    // 查询，多个参数，使用Map传递
    public Employee getEmployeeByMap(Map<String,Object> map);

    // 查询返回List
    public List<Employee> getByLastNameLike(String lastName);

    // 查询单条记录，返回Map类型,key为列名，value为列的值
    public Map<String,Object> getEmployeeByIdReturnMap(int id);

    // 查询多条记录，返回Map类型，key为记录的主健值，value为Employee对象
    // 用@MapKey指定用哪个字段作主健
    @MapKey("id")
    public Map<Integer,Employee> getEmployeeByLastNameLikeReturnMap(String lastName);

}
