package com.modules.service.impl;

import com.modules.dao.CommentDao;
import com.modules.entities.Comment;
import com.modules.service.CommentService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.io.BsonOutput;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.regex.Pattern;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MongoTemplate mongoTemplate;


    //查询
    public List<Comment> findCommentList(){
        List<Comment> list = commentDao.findAll();
        System.out.println(list);
        return list;
    }

    //查询单条
    public Comment findById(){
        Comment comment = commentDao.findById("2000").get();
        System.out.println(comment);
        return comment;
    }

    //增加
    public void saveComment(Comment comment){
        Comment save = commentDao.save(comment);
        System.out.println("增加的" + save);
    }

    //更新
    public void updateComment(Comment comment){
        Comment save = commentDao.save(comment);
        System.out.println("修改的" + save);
    }

    //删除
    public void delComment(String id){
        commentDao.deleteById(id);
    }

    /**
     * 根据id查询分页列表
     *  parebtid  父级id
     *  page  当前页
     *  size   要查询的个数
     */
    public List<Comment> findPageByParebtid(String parebtid,Integer page,Integer size){

        Page<Comment> pages = commentDao.findByParebtid(parebtid, PageRequest.of(page - 1, size));
        // 返回查询个数
        long totalElements = pages.getTotalElements();
        // 返回 list集合
        List<Comment> list = pages.getContent();
        System.out.println(totalElements);
        System.out.println(list);
        return list;

    }

    /**
     * 根据id/用户id查询分页列表
     */
    public List<Comment> findByParebtidAndUserid(){
        String parebtid="2000";
        String userid="400";
        Integer page=1;
        Integer size=2;
        Page<Comment> pages = commentDao.findByParebtidAndUserid(parebtid,userid,PageRequest.of(page - 1, size));
        // 返回查询个数
        long totalElements = pages.getTotalElements();
        // 返回 list集合
        List<Comment> list = pages.getContent();
        System.out.println(totalElements);
        System.out.println(list);
        return list;
    }

    // mongoTemplate增删改查
    public void doMongoTemplate(){
        //查询全部
        List<Comment> list = mongoTemplate.findAll(Comment.class);
        System.out.println(list);
        //根据ID查询
        String id="2009";
        Comment byId = mongoTemplate.findById(id, Comment.class);
        System.out.println(byId);
        //增加
//        Comment c = new Comment();
//        c.setId("2009");
//        c.setArticleid("500");
//        c.setContent("mongoTem");
//        c.setLikenum(24);
//        c.setNickname("别玩游戏");
//        Comment save = mongoTemplate.insert(c);
//        System.out.println(save);
        //修改
        try {
            Query query = Query.query(Criteria.where("_id").is("2009"));
            Update update = new Update();
            update.set("nickname","别出差");
            update.set("likenum",18);
            UpdateResult result = mongoTemplate.updateFirst(query, update, Comment.class);
            long count = result.getModifiedCount();
            if (count > 0) {
                System.out.println("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //再查询
        Comment byId2 = mongoTemplate.findById("2009", Comment.class);
        System.out.println(byId2);
    }

    //查询个数
    public long doCount(){
        Query query = Query.query(Criteria.where("nickname").is("匹克"));
        long count = mongoTemplate.count(query, Comment.class);
        System.out.println(count);
        return count;
    }

    //准确查询 模糊查询以 【^】开始 以【$】结束 【.*】相当于Mysql中的%
    public List<Comment> doRegex(String nickname){
        //准确查询
        Query query = Query.query(Criteria.where("nickname").is("匹克"));
        //模糊查询
        //String.format()
        String regex = "^.*" + nickname + ".*$";
        //String regex = String.format("String","^.*", nickname, ".*$");
        //Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query2 =Query.query(Criteria.where("nickname").regex(regex));
        List<Comment> list = mongoTemplate.find(query2, Comment.class);
        System.out.println(list);
        return list;
    }

    //分页查询
    public List<Comment> doPage(){
        Query query = new Query();
        //query.addCriteria(Criteria.where())
        long count = mongoTemplate.count(query, Comment.class);
        List<Comment> list = mongoTemplate.find(query.skip(2).limit(2), Comment.class);
        System.out.println(list);
        return list;
    }

    //删除
    public void doDelete(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult remove = mongoTemplate.remove(query, Comment.class);
        long count = remove.getDeletedCount();
        if (count > 0) {
            System.out.println("删除成功");
        }
    }

    //点赞--1
    public Comment doAddOne(String id){

        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        //update.inc("likenum");
        update.inc("likenum",-1);
        mongoTemplate.updateFirst(query,update,Comment.class);
        Comment byId = mongoTemplate.findById(id, Comment.class);
        System.out.println(byId);
        return byId;

    }

    //条件
    public List<Comment> doWhere(){
        //大于
        //Query query = Query.query(Criteria.where("likenum").gt(20));
        //小于
        Query query2 = Query.query(Criteria.where("likenum").lt(50));
        //update.inc("likenum");
        List<Comment> list = mongoTemplate.find(query2, Comment.class);
        System.out.println(list);
        return list;

    }

    //排序
    public List<Comment> doSort(){
        Query query = new Query();
        Query likenum = query.with(new Sort(Sort.Direction.DESC, "likenum"));
        //query.with（new Sort(Sort.Direction.ASC, "age"). and(new Sort(Sort.Direction.DESC, "date")))
        List<Comment> comments = mongoTemplate.find(likenum, Comment.class);
        return comments;
    }



}
