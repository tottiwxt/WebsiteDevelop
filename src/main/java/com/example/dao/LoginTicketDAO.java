package com.example.dao;

import com.example.model.LoginTicket;
import com.example.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = "login_ticket";
    String INSERT_FIELDS = " user_id,expired,status,ticket ";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;
    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELDS,") " +
            "values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket loginTicket);

    @Select({"select ", SELECT_FIELDS, "FROM ",TABLE_NAME,"where ticket = #{ticket}"})
    LoginTicket selectByTicket(String ticket);



    @Update({"update ",TABLE_NAME," set status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("status") int status,
                      @Param("ticket") String ticket);

    @Delete({"delete from ",TABLE_NAME, "where ticket = #{ticket}"})
    void deleteTicket(@Param("ticket")  String ticket);
}
