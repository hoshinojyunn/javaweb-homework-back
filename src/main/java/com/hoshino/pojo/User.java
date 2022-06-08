package com.hoshino.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Description: 实体类 对应数据库user表
 */
public class User {
    public static final String USER_SESSION = "userId";
    /**
     * username: 对应表的username
     * password: 对应表的password
     */
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
