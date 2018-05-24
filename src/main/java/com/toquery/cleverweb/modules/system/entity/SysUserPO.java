package com.toquery.cleverweb.modules.system.entity;

import com.toquery.cleverweb.core.entity.BasePoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户Entity
 *
 */

@Data
@Entity
@Table(name = "tb_sys_user")
@EqualsAndHashCode(callSuper = false)
public class SysUserPO extends BasePoEntity {
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 部门id
     */
    private String orgId;
    /**
     * 状态(1：启用  2：冻结）
     */
    private Integer status;

}



