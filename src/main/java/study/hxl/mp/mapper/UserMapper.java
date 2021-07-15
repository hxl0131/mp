package study.hxl.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.hxl.mp.bean.User;

import java.util.List;

/**
 * @author hxl
 * @Date 2021-07-13 12:28
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User> {

    User findById(Integer id);
    
    
}
