package com.example.dao;


import com.example.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = " name,password,salt,head_url ";
    String SELECT_FIELDS = "id, name, password, salt, head_url";
    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELDS,") " +
            "values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, "FROM ",TABLE_NAME,"where id = #{id}"})
    User selectById(int id);

    @Delete({"delete from ",TABLE_NAME,"where id  = #{id}"})
    void delectById(int id);

    @Update({"update ",TABLE_NAME," set password = #{password} where id = #{id}"})
    void updateById(User user);

    @Select({"select * from ", TABLE_NAME})
    List<User> getAllUsers();
}
