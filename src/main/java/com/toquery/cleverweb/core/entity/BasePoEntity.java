package com.toquery.cleverweb.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.core.annotation.Order;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by ToQuery
 */
@Data
@MappedSuperclass
public abstract class BasePoEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id",length = 32)
    //@ColumnPosition(1)
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="create_by")
    protected String createBy;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="update_by")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date")
    private Date updateDate;

    /**
     * 启用状态 :  1:启用   0:不启用
     */
    @Column(name="del_enable",length = 1)
    private Integer isEnable = 1;

    //删除标识(0:未删除1：删除)
    @Column(name="del_flag",length = 1)
    private Integer delFlag = 0;

    private String remarks;

    @Column(name="sort",length = 10)
    private Integer sort;


    /**
     * 插入之前执行方法，需要手动调用
     */
    @PrePersist
    public void preInsert() {
        this.updateBy = "";
        this.createBy = "";
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateBy = "";
        this.updateDate = new Date();
    }



}
