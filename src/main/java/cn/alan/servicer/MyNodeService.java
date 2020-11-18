package cn.alan.servicer;

import cn.alan.entity.MyNode;
import cn.alan.mapper.MyNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyNodeService {
    @Autowired
    private MyNodeMapper myNodeMapper;

    public List<MyNode> buildNodesFrom(Integer id){
        return myNodeMapper.buildNodesFrom(id);
    }
}
