package com.toquery.cleverweb.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_sys_message")
public class TbSysMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id", unique = true, nullable = false)
    private String messageId;

    @Column(name = "content", unique = true, nullable = false)
    private String content;

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @Column(name = "receive_id", unique = true, nullable = false)
    private String receiveId;

    @Column(name = "send_id", unique = true, nullable = false)
    private String sendId;

    @Column(name = "send_time", unique = true, nullable = false)
    private Date sendTime;

    @Column(name = "status", unique = true, nullable = false)
    private String status;

    @Column(name = "sanme_id", unique = true, nullable = false)
    private String sanmeId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId == null ? null : receiveId.trim();
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSanmeId() {
        return sanmeId;
    }

    public void setSanmeId(String sanmeId) {
        this.sanmeId = sanmeId == null ? null : sanmeId.trim();
    }
}