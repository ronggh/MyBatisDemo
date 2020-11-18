package cn.alan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyNode implements Serializable {
    private Integer id;
    private String name;
    private Integer pid;
    private List<MyNode> children;

}
