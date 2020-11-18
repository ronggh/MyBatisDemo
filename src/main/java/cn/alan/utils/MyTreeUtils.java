package cn.alan.utils;

import cn.alan.entity.MyNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class MyTreeUtils {
    public static List<MyNode> buildTree(List<MyNode> nodes,Integer id) {
        if (null == nodes) {
            return null;
        }
        //
        Map<Integer, List<MyNode>> children = nodes.stream().filter(node -> node.getPid() != id).collect(Collectors.groupingBy(node -> node.getPid()));
        // System.out.println("children =====> " + children);
        nodes.forEach(node -> node.setChildren(children.get(node.getId())));
        return nodes.stream().filter(node -> node.getPid() == id).collect(Collectors.toList());
    }
}
