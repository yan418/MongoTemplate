# mongoTemplate
使用mongoTemplate组件操作mongoDB数据库

## 搭建 mongodb

``` bash
  mongodb 官方网址 ：https://www.mongodb.com
  
  常用命令：
  创建库/使用库   use  库名
  插入           db.集合名.insert({...})
  查询           db.集合名.find()
  删除           db.集合名.remove()
  更新           db.集合名.update()

```
## 数据
在 mongodb.txt 下

## MongoRepository 使用

``` bash
  dao 层 
  继承 MongoRepository<集合名实体,ID类型>
```

## MongoTemplate 使用

``` bash
  Service 层 
  注入 MongoTemplate  ，继承的方法
```

## 接口

``` bash
  /doFind       查询列表
  /doFindByID   通过ID查询
  /doSave       增加
  /doUpdate     更新
  /doDel/{id}   删除
  /doFindByID/{id}/{page}   分页查询  参数 id 、 当前页
  /doFindAndUser 查询
  /mongoTemplate 操作
  /doCount      查询个数
  /doWhere      条件查询  大于 等于
  /doSort       排序查询
```


