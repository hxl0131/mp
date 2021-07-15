package study.hxl.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.hxl.mp.bean.User;
import study.hxl.mp.service.UserService;

import java.util.List;

/**
 * @author hxl
 * @Date 2021-07-14 22:58
 */
@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/userList")
    public List<User> getAllUserList(){
        List<User> list = userService.list();
        return list;
    }
    
}
