package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

@Component
public class Share {
    @TableId
    private Integer id;
    private Integer userId;
    private String content;
    private String time;
    private Integer likes;

    public Share() {
    }

    public Share(Integer id, Integer userId, String content, String time, Integer likes) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", likes=" + likes +
                '}';
    }
}
