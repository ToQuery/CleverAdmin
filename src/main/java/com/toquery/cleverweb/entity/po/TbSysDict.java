package com.toquery.cleverweb.entity.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Table(name = "tb_sys_dict")
public class TbSysDict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dict_id", unique = true, nullable = false)
    private String dictId;

    @Column(name = "value", unique = true, nullable = false)
    private String value;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "sort", unique = true, nullable = false)
    private Long sort;

    @Column(name = "parent_id", unique = true, nullable = false)
    private String parentId;

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

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.updateBy = updateBy == null ? null : updateBy.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}