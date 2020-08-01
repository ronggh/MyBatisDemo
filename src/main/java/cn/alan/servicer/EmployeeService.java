package cn.alan.servicer;

import cn.alan.mapper.EmployeeMapper;
import cn.alan.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;


    public Employee getEmployeeById(int id){
        return employeeMapper.getEmpById(id);
    }
}
