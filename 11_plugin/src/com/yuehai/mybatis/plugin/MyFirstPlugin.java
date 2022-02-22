package com.yuehai.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @author 月海
 * @create 2022/2/3 20:13
 */

/**
 * 使用 @Intercepts 注解完成插件签名：告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts(
    {
        /**
         * type=StatementHandler.class：拦截 StatementHandler 对象
         * method="parameterize"：拦截 StatementHandler 对象中的 parameterize 方法
         * args：参数
         */
        @Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
    })
public class MyFirstPlugin implements Interceptor {
    /**
     * intercept：拦截：拦截目标对象的目标方法的执行；
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 动态的改变一下sql运行的参数：以前1号员工，实际从数据库查询3号员工
        // invocation.getTarget()：通过 invocation 获取代理的目标对象
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象："+target);
        // 拿到：StatementHandler==>ParameterHandler===>parameterObject
        // 拿到 target 的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是："+value);
        // 修改完 sql 语句要用的参数
        System.out.println("sql语句用的参数改为：11");
        metaObject.setValue("parameterHandler.parameterObject", 11);

        // 放行，执行目标方法
        Object proceed = invocation.proceed();

        // 返回执行后的返回值
        return proceed;
    }

    /**
     * plugin：包装目标对象：包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        // 我们可以借助 Plugin 的 wrap 方法来使用当前 Interceptor 包装我们目标对象
        Object wrap = Plugin.wrap(target, this);

        // 返回为当前 target 创建的动态代理
        return wrap;
    }

    /**
     * setProperties：将插件注册时的 property 属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息：" + properties);
    }
}
