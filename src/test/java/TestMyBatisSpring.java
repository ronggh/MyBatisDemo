import cn.alan.entity.Employee;
import org.junit.jupiter.api.Test;
import cn.alan.servicer.EmployeeService;

public class TestMyBatisSpring {
    @Test
    public void test1(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeById(1);
        System.out.println(employee);
    }
}
