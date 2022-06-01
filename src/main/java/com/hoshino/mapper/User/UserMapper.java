package com.hoshino.mapper.User;

import com.hoshino.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserMapper {

    public User getUserById(@Param("id") int id);

    public int addUser(User user);

    public User selectUser(@Param("username") String username);

    public int setUserAvatar(@Param("userId")int userId,@Param("imgUrl")String imgUrl);

    public String getUserAvatar(@Param("userId")int userId);

    public String getUserName(@Param("username") String username);

    public int defaultPersonalSettings(@Param("userId")int userId);

    public int resetPassword(@Param("userId")int userId,@Param("password")String password);

}
