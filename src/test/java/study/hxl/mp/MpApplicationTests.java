package study.hxl.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.hxl.mp.bean.User;
import study.hxl.mp.enums.AgeEnum;
import study.hxl.mp.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MpApplicationTests {

    @Autowired
    private UserMapper userMapper;
    
    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert(){
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
        User user = new User();
        user.setName("lala");
        user.setAge(19);
        user.setEmail("lala@qqq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    
    
    }
    
    @Test
    public void testSelect(){
        User user = userMapper.selectById(2);
        System.out.println(user);
    }
    
    @Test
    public void testUpdate(){
//        User user = new User();
//        user.setId(2L);
//        user.setEmail("jack@qq.com");
//        userMapper.updateById(user);
    
        User user = new User();
        //设置要更新的字段
        user.setAge(20);
        user.setEmail("testupdate@qq.com");
    
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Tom");//把数据库表中字段name的值为Tom的数据，更新为user
    
        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    
    }
    
    
    @Test
    public void testUpdate2(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        
        wrapper.set("age",21).set("u_email","update2@qq.com")   //更新的字段
                .eq("name","haha"); //更新的条件
        
        int update = userMapper.update(null, wrapper);
        System.out.println(update);
        
    }
    
    @Test
    public void testDelete(){
        //测试删除
        //根据ID删除
//        int result = userMapper.deleteById(9L);
//        System.out.println(result);
        
        // 根据wrapper条件删除
        //用法1：
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        //条件之间隔着and
//        wrapper.eq("name","haha")
//        .eq("u_email","update2@qq.com");
    
        //用法2：（推荐使用）
        User user = new User();
        user.setName("lalala");
        user.setAge(5);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);
    
    }
    
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","mary");
        map.put("u_email","mary@qq.com");
        //根据Map删除数据，多条件之间是and关系
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }
    
    @Test
    public void testDeleteBatchIds(){
        //根据id批量删除
        int delete = userMapper.deleteBatchIds(Arrays.asList(11, 12));
        System.out.println(delete);
    
    }
    
    @Test
    public void testSelect2(){
        //批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L));
        users.forEach(System.out::println);
        
    }
    
    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jack");
        //若根据条件查询到的数据有多条，会报异常
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    
    }
    
    //根据条件查询数据的条数
    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //条件可以是eq、gt（大于）、lt（小于）等...
        wrapper.lt("age","40");
        
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }
    
    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("u_email","qq");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    //分页查询
    @Test
    public void testSelectPage(){
    
        Page<User> page = new Page<>(1,2);
        
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lt("age","40");
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + userPage.getTotal());
        System.out.println("数据总页数：" + userPage.getPages());
        System.out.println("当前页数：" + userPage.getCurrent());
    
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    
    }
    
    
    @Test
    public void testFindById(){
        User user = userMapper.findById(2);
        System.out.println(user);
    }
    
    
    @Test
    public void testAllEq(){
        
        Map<String,Object> params = new HashMap<>();
        params.put("name","曹操");
        params.put("age",90);
        params.put("u_email",null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//      SELECT id,name,age,u_email AS email FROM tb_user WHERE (u_email IS NULL AND name = ? AND age = ?)
//        wrapper.allEq(params);

//      SELECT id,name,age,u_email AS email FROM tb_user WHERE (name = ? AND age = ?)
//        wrapper.allEq(params,false);

//      SELECT id,name,age,u_email AS email FROM tb_user WHERE (age = ?)
//        意思是，从map中传进来的条件，只有在这个lambda表达式中符合，返回true，才能作为sql的条件进行查询，相当于一个过滤器
        wrapper.allEq( (k, v) -> (k.equals("age") || k.equals("id") ), params);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    
    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按年龄倒序排序
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    
    }
    
    
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jack").or().eq("age",24);
//        SELECT id,name,age,u_email AS email FROM tb_user WHERE (name = ? OR age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
 
    @Test
    public void testSelectWithOut(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jack")
                .or()
                .eq("age",24)
                .select("id","name","u_email");
//        SELECT id,name,u_email FROM tb_user WHERE (name = ? OR age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    
    @Test
    public void testFindAll(){
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    @Test
    public void testInsertWithFill(){
        User user = new User();
        user.setName("关于");
        user.setAge(99);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
    
    
    //测试逻辑删除
    @Test
    public void testDeleteLogic(){
//        int result = userMapper.deleteById(7L);
//        UPDATE tb_user SET deleted=1 WHERE id=? AND deleted=0
//        System.out.println(result);
        User user = userMapper.selectById(7L);
//        SELECT id,name,age,u_email AS email,version,deleted FROM tb_user WHERE id=? AND deleted=0
        System.out.println(user);
    }
    
    
    @Test
    public void testSelectByEnum(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("create_age",AgeEnum.TWO);
//        SELECT id,name,age,u_email AS email,version,deleted,create_age FROM tb_user
//        WHERE deleted=0 AND (create_age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
        
    }
    
}
