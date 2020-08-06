package com.happy.live.common.base.annotation;

import java.lang.annotation.*;

/**
 * @name: CurrentUser <tb>
 * @title: 用于标识用户实体类入参，参数级注解  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/4/7 8:52 <tb>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

}
