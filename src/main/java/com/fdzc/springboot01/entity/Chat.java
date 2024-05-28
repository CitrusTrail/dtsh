package com.fdzc.springboot01.entity;

public class Chat {

    private Integer groupId;
    private Integer userId;
    private String content;
    private String time;

    public Chat() {
    }

    public Chat(Integer groupId, Integer userId, String content, String time) {
        this.groupId = groupId;
        this.userId = userId;
        this.content = content;
        this.time = time;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "groupId=" + groupId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
