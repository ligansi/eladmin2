package me.zhengjie.modules.personnel.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author Li GanSi
* @date 2020-04-08
*/
@Data
public class InductionDTO implements Serializable {

    // ID
    private Long id;

    // 姓名
    private String username;

    // 性别
    private Integer sex;

    // 出生日期
    private Timestamp birthdate;

    // 年龄
    private Integer age;

    // 教育
    private String education;

    // 入职日期
    private Timestamp entryDate;

    // 部门编号
    private Long deptId;

    // 岗位编号
    private Long jobId;

    // 毕业院校
    private String graduateSchool;

    // 毕业日期
    private Timestamp graduateDate;

    // 毕业专业
    private String graduateMajor;

    // 面试记录
    private String interview;

    // 创建日期
    private Timestamp createTime;

    // 创建用户
    private String createUser;

    // 状态
    private String status;
}