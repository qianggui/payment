package com.xjw.payment.vo;


import lombok.Data;
import lombok.experimental.Accessors;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2022/8/5 14:23
 * @Version 1.0
 */


/**
 * 引用Lombok
 */
@Data
/**
 * 开启链式编程
 */
@Accessors(chain = true)
public class Res {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应返回的数据
     */
    private Map<String, Object> data = new HashMap<>();

    public static Res ok(){
        Res r = new Res();
        r.setCode(0);
        r.setMessage("成功");
        return r;
    }

    public static Res error(){
        Res r = new Res();
        r.setCode(-1);
        r.setMessage("失败");
        return r;
    }

    public Res data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}
