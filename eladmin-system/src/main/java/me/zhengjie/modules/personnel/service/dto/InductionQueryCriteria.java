package me.zhengjie.modules.personnel.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author Li GanSi
* @date 2020-04-08
*/
@Data
public class InductionQueryCriteria{

    // 精确
    @Query
    private String username;

    // 精确
    @Query
    private Integer age;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String education;
}