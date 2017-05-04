package com.toquery.cleverweb.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ToQuery
 * @version 1.0
 * @date 17-5-4.
 */
@Entity
@Table(name = "tb_sys_area")
public class TbSysArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "parent_id", unique = true, nullable = false)
    private String parentId;

    @Column(name = "parent_ids", unique = true, nullable = false)
    private String parentIds;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "short_name", unique = true, nullable = false)
    private String shortName;

    @Column(name = "sort", unique = true, nullable = false)
    private Integer sort;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @Column(name = "create_by", unique = true, nullable = false)
    private String createBy;

    @Column(name = "create_date", unique = true, nullable = false)
    private Date createDate;

    @Column(name = "update_by", unique = true, nullable = false)
    private String updateBy;

    @Column(name = "update_date", unique = true, nullable = false)
    private Date updateDate;

    @Column(name = "remarks", unique = true, nullable = false)
    private String remarks;

    @Column(name = "del_flag", unique = true, nullable = false)
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
