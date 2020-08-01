package cn.alan.mapper;

import cn.alan.entity.Dept;

public interface DeptMapper {
    //
    public Dept getDeptById(int id);

    // 查部门，并同时将部门下的员工也一并查出来
    public Dept getDeptWithEmployeeById(int id);

    // 使用分步查询实现：查部门，并同时将部门下的员工也一并查出来
    public Dept getDeptWithEmployeeByIdStep(int id);
}
