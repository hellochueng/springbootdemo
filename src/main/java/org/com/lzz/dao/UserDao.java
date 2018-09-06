package org.com.lzz.dao;

import org.apache.ibatis.annotations.Select;
import org.com.lzz.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from User where id = #{1}")
    public User getUserById(String id);
}
