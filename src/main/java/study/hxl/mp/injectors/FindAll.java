package study.hxl.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author hxl
 * @Date 2021-07-14 16:35
 */
public class FindAll extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
    
        String sql = "select * from " + tableInfo.getTableName();
        
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);
        
        return this.addSelectMappedStatementForTable(mapperClass, "findAll", sqlSource, tableInfo);
    }
}