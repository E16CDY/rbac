package cn.wolfcode.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeParam extends PageParam{
    private String keyword;
    private Long deptId;
}
