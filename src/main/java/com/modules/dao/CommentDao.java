package com.modules.dao;

import com.modules.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


// 继承MongoRepository<集合名实体,ID类型>
public interface CommentDao extends MongoRepository<Comment,String> {

    //根据parebtid查询分页列表  Pageable分页封装的参数
    Page<Comment> findByParebtid(String parebtid, Pageable pageable);

    //根据parebtid查询分页列表  Pageable分页封装的参数
    Page<Comment> findByParebtidAndUserid(String parebtid,String userid, Pageable pageable);

}
