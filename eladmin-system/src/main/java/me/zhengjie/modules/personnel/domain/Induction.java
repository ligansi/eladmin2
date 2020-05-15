package me.zhengjie.modules.personnel.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author Li GanSi
* @date 2020-04-08
*/
@Entity
@Data
@Table(name="induction")
public class Induction implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 姓名
    @Column(name = "username")
    private String username;

    // 性别
    @Column(name = "sex")
    private Integer sex;

    // 出生日期
    @Column(name = "birthdate")
    private Timestamp birthdate;

    // 年龄
    @Column(name = "age")
    private Integer age;

    // 教育
    @Column(name = "education")
    private String education;

    // 入职日期
    @Column(name = "entry_date")
    private Timestamp entryDate;

    // 部门编号
    @Column(name = "dept_id")
    private Long deptId;

    // 岗位编号
    @Column(name = "job_id")
    private Long jobId;

    // 毕业院校
    @Column(name = "graduate_school")
    private String graduateSchool;

    // 毕业日期
    @Column(name = "graduate_date")
    private Timestamp graduateDate;

    // 毕业专业
    @Column(name = "graduate_major")
    private String graduateMajor;

    // 面试记录
    @Column(name = "interview")
    private String interview;

    // 创建日期
    @Column(name = "create_time")
    private Timestamp createTime;

    // 创建用户
    @Column(name = "create_user")
    private String createUser;

    // 状态
    @Column(name = "status")
    private String status;

    public void copy(Induction source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}