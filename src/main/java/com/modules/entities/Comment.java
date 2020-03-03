package com.modules.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

//评论实体类
//@Document(collection = "comment")  //映射表名
public class Comment implements Serializable {

    @Id
    //主键   @Id映射主键
    private String id;

    //@Field("articleid")
    //文章ID  映射字段   @Indexed 索引
    private String articleid;
    //评论内容
    private String content;
    //评论人ID
    private String userid;
    //评论人昵称
    private String nickname;
    //点赞数
    private Integer likenum;
    //回复数
    private Integer replynum;
    //状态  0 不可见 1可见
    private String state;
    //上级ID
    private String parebtid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParebtid() {
        return parebtid;
    }

    public void setParebtid(String parebtid) {
        this.parebtid = parebtid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", articleid='" + articleid + '\'' +
                ", content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", likenum=" + likenum +
                ", replynum=" + replynum +
                ", state='" + state + '\'' +
                ", parebtid='" + parebtid + '\'' +
                '}';
    }
}
