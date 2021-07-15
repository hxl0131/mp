package study.hxl.mp.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author hxl
 * @Date 2021-07-14 20:03
 */
public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一年"),
    TWO(2, "两年"),
    THREE(3, "三年");
    
    private int value;
    private String desc;
    
    AgeEnum(int value,String desc){
        this.value = value;
        this.desc = desc;
    }
    
    @Override
    public Integer getValue() {
        return this.value;
    }
}