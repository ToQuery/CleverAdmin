package com.cleverweb.entity.po;

public class TbSysMessage {
    private String messageId;

    private String content;

    private String type;

    private String toUsername;

    private String fromUsername;

    private String sendTime;

    private String status;

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

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername == null ? null : toUsername.trim();
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername == null ? null : fromUsername.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
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