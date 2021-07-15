package study.hxl.mp.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.hxl.mp.bean.User;
import study.hxl.mp.mapper.UserMapper;

import java.util.List;

/**
 * @author hxl
 * @Date 2021-07-14 23:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
