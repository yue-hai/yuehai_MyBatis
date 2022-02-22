package com.yuehai.mybatis.bean;

import org.omg.PortableInterceptor.Interceptor;

/**
 * @author 月海
 * @create 2022/2/4 20:41
 */

/**
 * 希望数据库保存的是 100、200这种状态码
 * msg 展示给前端用户
 */
// 枚举类
public enum EmpStatus {
    // 登录（状态码）
    LOGIN(100,"用户登录"),
    // 登出
    LOGOUT(200,"用户登出"),
    // 被管理员移除
    REMOVE(300,"用户不存在");

    private Integer code;
    private String msg;

    // 构造器
    private EmpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    // 按照状态码返回枚举对象
    public static EmpStatus getEmpStatusByCode(Integer coed){
        switch (coed){
            case 100:
                return LOGIN;
            case  200:
                return LOGOUT;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }
}
