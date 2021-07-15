package study.hxl.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.hxl.mp.bean.User;
import study.hxl.mp.enums.AgeEnum;

import java.util.List;

/**
 * @author hxl
 * @Date 2021-07-14 12:24
 */
@SpringBootTest
public class TestMapperAR {
    
    @Test
    public void testSelectById(){
        User user = new User();
        user.setId(2L);
        User user1 = user.selectById();
        System.out.println(user1);
    
    }
    
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("刘备");
        user.setAge(80);
        user.setEmail("liubei@qq.com");
        //调用AR的insert方法
        boolean insert = user.insert();
        System.out.println(insert);
    
    }
    
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3L);
        user.setAge(69);
        boolean update = user.updateById();
        System.out.println(update);
    }
    
    @Test
    public void testDelete(){
        User user = new User();
        boolean b = user.deleteById(6);
        System.out.println(b);
    
    }
    
    @Test
    public void testSelect(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.le("age",70);
        List<User> users = user.selectList(wrapper);
        for (User u : users) {
            System.out.println(u);
        }
        
    }
    
    @Test
    public void testUpdateVersion(){
        User user = new User();
        user.setId(3L);
        //先查到当前版本，再去修改
        User userVersion = user.selectById();
    
        user.setAge(69);
        //当前的版本信息 如果这个值和数据库中版本不一致，则更新失败
//        user.setVersion(1L);
//        UPDATE tb_user SET age=69, version=2 WHERE id=3 AND version=1
        
        user.setVersion(userVersion.getVersion());
        boolean update = user.updateById();
        System.out.println(update);
    }
    
    @Test
    public void testEnum(){
        User user = new User();
        user.setName("wahaha");
        user.setAge(10);
        user.setEmail("wahaha@qq.com");
        user.setCreateAge(AgeEnum.TWO);//枚举
        boolean insert = user.insert();
        System.out.println(insert);
    }
}
