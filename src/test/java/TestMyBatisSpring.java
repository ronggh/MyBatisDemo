import cn.alan.entity.Employee;
import cn.alan.entity.MyNode;
import cn.alan.servicer.MyNodeService;
import cn.alan.utils.MyTreeUtils;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import cn.alan.servicer.EmployeeService;

import java.util.List;

public class TestMyBatisSpring {

    @Test
    public void test1() {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeById(1);
        System.out.println(employee);
    }


}
