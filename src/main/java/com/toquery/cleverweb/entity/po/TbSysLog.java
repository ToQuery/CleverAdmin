package com.toquery.cleverweb.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author toquery
 * @version 1
 */
@Entity
@Table(name = "tb_sys_log")
public class TbSysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id", unique = true, nullable = false)
    private String logId;

    @Column(name = "log_type", unique = true, nullable = false)
    private String logType;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "request_url", unique = true, nullable = false)
    private String requestUrl;

    @Column(name = "request_date", unique = true, nullable = false)
    private Date requestDate;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
