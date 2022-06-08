import com.hoshino.mapper.Group.GroupMapper;
import com.hoshino.mapper.UserEvent.UserEventMapper;
import com.hoshino.mapper.UserFriends.UserFriendsMapper;
import com.hoshino.mapper.User.UserMapper;
import com.hoshino.pojo.User;
import com.hoshino.util.MD5Utils;
import com.hoshino.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.hoshino.service.friendService.friendServiceImpl;

import java.util.List;

public class Mytest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.addUser(new User("hello", "123456")));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.selectUser("ganderun"));
        sqlSession.close();
    }

    @Test
    public void test3(){
        // 892876dd376b870dd2c34cd7f3842676
        System.out.println(MD5Utils.stringToMD5("ganderun12"));
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserEventMapper mapper = sqlSession.getMapper(UserEventMapper.class);
        mapper.getEventByUserId(1).forEach(userEvent -> {
            System.out.println(userEvent.toString());
        });
        sqlSession.close();
    }

    @Test
    public void test5(){

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserFriendsMapper mapper = sqlSession.getMapper(UserFriendsMapper.class);
        List<User> userFriendsById = mapper.getUserFriendsById(3);
        userFriendsById.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void test6(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        System.out.println(mapper.groupAddUser(3, 3));
        System.out.println(mapper.groupAddUser(2, 3));
        System.out.println(mapper.groupAddUser(1, 3));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test7(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
        System.out.println(mapper.getUserGroups(2));
        sqlSession.close();
    }
    @Test
    public void test8(){
        friendServiceImpl friendService = new friendServiceImpl();
        List<User> friends = friendService.getFriends(3);
        System.out.println(friends);
    }
    @Test
    public void test9(){
        friendServiceImpl friendService = new friendServiceImpl();
        boolean friends = friendService.addFriend(3,7);
        System.out.println(friends);
    }
}
