package com.toquery.mybatis.toolkit;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Proxy;

/**
 * 插件工具类
 *
 * @author TaoYu
 */
public final class PluginUtils {

    private PluginUtils() {
    }

    /**
     * 获得真正的处理对象,可能多层代理.
     */
    public static Object realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return target;
    }

}
