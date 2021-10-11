package cn.wolfcode.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Employee {
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private Boolean admin;

    private Long deptId;

    //关联部门信息
    private Department department;

    //关联角色信息
    private List<Role> roles;

}