package com.modules.service;

import com.modules.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CommentService {

    public List<Comment> findCommentList();

    public void saveComment(Comment comment);

    public Comment findById();

    public void updateComment(Comment comment);

    public void delComment(String id);

    public List<Comment> findPageByParebtid(String parebtid,Integer page,Integer size);

    public List<Comment> findByParebtidAndUserid();

    public void doMongoTemplate();

    public long doCount();

    public List<Comment> doRegex(String nickname);

    public List<Comment> doPage();

    public void doDelete(String id);

    public Comment doAddOne(String id);

    public List<Comment> doWhere();

    public List<Comment> doSort();

}
