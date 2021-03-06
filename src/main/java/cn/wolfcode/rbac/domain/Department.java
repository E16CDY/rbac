package cn.wolfcode.rbac.domain;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long id;
    private String name;
    private String sn;
}