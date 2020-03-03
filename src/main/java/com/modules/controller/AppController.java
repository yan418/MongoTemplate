package com.modules.controller;

import com.modules.entities.Comment;
import com.modules.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AppController {

    @Autowired
    private CommentService commentService;

    //查询
    @GetMapping("/doFind")
    public String doFind(){
        List<Comment> list =  commentService.findCommentList();
        return  list.toString();
    }

    //查询一个
    @GetMapping("/doFindByID")
    public String doFindByID(){
        Comment comment = commentService.findById();
        return  comment.toString();
    }

    //增加
    @GetMapping("/doSave")
    public String doSave(){
        Comment c = new Comment();
        c.setArticleid("2000");
        c.setContent("没事评论");
        c.setLikenum(40);
        c.setNickname("save增加1");
        commentService.saveComment(c);
        return  "增加成功";
    }

    //更新
    @GetMapping("/doUpdate")
    public String doUpdate(){
        Comment c = new Comment();
        c.setId("2000");
        c.setArticleid("2000");
        c.setContent("我修改了评论");
        c.setLikenum(50);
        c.setNickname("save增加1");
        commentService.updateComment(c);
        return  "更新成功";
    }

    @GetMapping("/doDel/{id}")
    public String doDel(@PathVariable("id") String id){
        System.out.println(id);
        commentService.delComment(id);
        return  "删除成功";
    }

    @GetMapping("/doFindByID/{id}/{page}")
    public String doFindByID(@PathVariable("id") String id,@PathVariable("page") Integer page){
        if(page<=0){
            return "当前页要大于0";
        }
        //Integer page=1;
        Integer size=3;
        List<Comment> list = commentService.findPageByParebtid(id, page, size);
        return  list.toString();
    }

    @GetMapping("/doFindAndUser")
    public String doFindAndUser(){
        List<Comment> list = commentService.findByParebtidAndUserid();
        return  list.toString();
    }

    @GetMapping("/mongoTemplate")
    public String mongoTemplate(){
        commentService.doMongoTemplate();
        return  "控制台查看";
    }

    @GetMapping("/doCount")
    public String doCount(){
        long lo = commentService.doCount();
        return  Long.toString(lo);
    }

    @GetMapping("/doCount/{nickname}")
    public String doRegex(@PathVariable("nickname") String nickname){
        List<Comment> comments = commentService.doRegex(nickname);
        return  comments.toString();
    }

    @GetMapping("/doPage")
    public String doPage(){
        List<Comment> list = commentService.doPage();
        return  list.toString();
    }

    @GetMapping("/doDelete/{id}")
    public String doDelete(@PathVariable("id") String id){
        commentService.doDelete(id);
        return  "删除成功";
    }

    @GetMapping("/doAddOne/{id}")
    public String doAddOne(@PathVariable("id") String id){
        Comment comment = commentService.doAddOne(id);
        return  comment.toString();
    }

    @GetMapping("/doWhere")
    public String doWhere(){
        List<Comment> comments = commentService.doWhere();
        return  comments.toString();
    }


    @GetMapping("/doSort")
    public String doSort(){
        List<Comment> comments = commentService.doSort();
        return  comments.toString();
    }

}
