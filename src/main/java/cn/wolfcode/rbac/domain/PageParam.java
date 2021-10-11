package cn.wolfcode.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {
    //当前页码
    private int currentPage = 1;
    //每页显示数据量
    private int pageSize = 5;
    //计算偏移量
    public int getStart(){
        return (currentPage - 1) * pageSize;
    }
}
