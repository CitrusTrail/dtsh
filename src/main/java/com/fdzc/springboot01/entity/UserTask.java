package com.fdzc.springboot01.entity;

public class UserTask {

    private Integer userId;
    private Integer taskId;
    private String completedTime;

    public UserTask() {
    }

    public UserTask(Integer userId, Integer taskId, String completedTime) {
        this.userId = userId;
        this.taskId = taskId;
        this.completedTime = completedTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                ", completedTime='" + completedTime + '\'' +
                '}';
    }
}
