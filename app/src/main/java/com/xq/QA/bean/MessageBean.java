package com.xq.QA.bean;

import java.io.Serializable;

//商家会员实体
public class MessageBean implements Serializable {
    public int id;//	分类id
    public String content;//	分类名称


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
