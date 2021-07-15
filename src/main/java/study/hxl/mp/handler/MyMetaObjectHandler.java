package study.hxl.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author hxl
 * @Date 2021-07-14 18:08
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 先获取到email的值，再进行判断，如果为空就进行填充，如果不为空就不做处理
        Object email = getFieldValByName("email", metaObject);
        if( null == email){
            setFieldValByName("email","xxx@qq.com",metaObject);
        }
        
    }
    
    /**
     * 更新数据时填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
    
    }
}
