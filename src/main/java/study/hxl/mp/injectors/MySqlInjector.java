package study.hxl.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxl
 * @Date 2021-07-14 16:33
 */
public class MySqlInjector extends DefaultSqlInjector {
    
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> list = new ArrayList<>();
        
        //获取父类中的集合，不这样做的话原本自带的Mapper中的方法都没了
        list.addAll(super.getMethodList(mapperClass));
        //再扩充自定义的方法
        list.add(new FindAll());
        
        return list;
    }
}
