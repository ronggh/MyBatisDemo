package cn.alan.mapper;

import cn.alan.entity.MyNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyNodeMapper {
     List<MyNode> buildNodesFrom(@Param("id") Integer id);
}
