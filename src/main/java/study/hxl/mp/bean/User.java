package study.hxl.mp.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.hxl.mp.enums.AgeEnum;


/**
 * @author hxl
 * @Date 2021-07-13 12:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")//mp的注解 表示指定对应数据库中的某张表
public class User extends Model<User> {
    
    @TableId(type = IdType.AUTO)//表示该字段在数据库中是自增的
    private Long id;
    private String name;
//    @TableField(select = false)//查询时不返回这个字段
    private Integer age;
    //指定数据库表中的字段值   fill设置插入数据时填充
    @TableField(value = "u_email",fill = FieldFill.INSERT)
    private String email;
    @TableField(exist = false)
    private String address;//在数据库表中不存在
    
    @Version    //乐观锁的版本字段
    private Integer version;
    
    @TableLogic //逻辑删除字段 -- 1代表已删除 0代表未删除
    private Integer deleted;
    
    private AgeEnum createAge;//用户创建时长 枚举类型
    
}
