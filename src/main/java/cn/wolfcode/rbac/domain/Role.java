package cn.wolfcode.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions;
}