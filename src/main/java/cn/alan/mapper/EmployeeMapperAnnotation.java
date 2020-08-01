package cn.alan.mapper;

import cn.alan.entity.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {
    // 基于注解的方式进行SQL映射
    @Select("select id,lastName,email,gender from t_employee where id = #{id}")
    Employee getEmpById(int id);
}
