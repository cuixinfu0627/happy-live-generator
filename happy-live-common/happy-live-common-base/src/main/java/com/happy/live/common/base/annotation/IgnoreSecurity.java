package com.happy.live.common.base.annotation;

import java.lang.annotation.*;

/**https://blog.csdn.net/zhangpower1993/article/details/89016503
 * @name: IgnoreSecurity <tb>
 * @title: 标识是否忽略登录检查，方法级注解  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/4/7 8:53 <tb>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {

}

