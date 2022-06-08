package com.hoshino.mapper.User;

import com.hoshino.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * Date:2022.6.5
 * description:<p>此为用户相关功能的Mapper</p>
 * <p>{@code GroupMapper}提供了用户相关服务的数据库操作的API</p>
 * <p> <strong>通过ID获取用户的API{@link #getUserById(int)}</strong></p>
 *  <p> <strong>添加用户的API{@link #addUser(User)}</strong></p>
 *  <p> <strong>选择用户的API{@link #selectUser(String)}</strong></p>
 *  <p> <strong>设置用户头像的API{@link #setUserAvatar(int, String)}</strong></p>
 *  <p> <strong>获取用户头像的API{@link #getUserAvatar(int)}</strong></p>
 *  <p> <strong>获取用户名称的API{@link #getUserName(String)}</strong></p>
 *  <p> <strong>提供默认个人设置的API{@link #defaultPersonalSettings(int)}</strong></p>
 *  <p> <strong>重新设置用户密码的API{@link #getUserName(String)}</strong></p>
 *   @author GouHang
 *   @version 1.4
 */
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
