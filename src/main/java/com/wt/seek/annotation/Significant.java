package com.wt.seek.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 用来表示该方法,或该controller下的所有方法<br>
 * 是用户主动请求的,具有统计意义的.<br>
 * 注解本身没有其他含义,也不需要被解释
 * @author Daryl
 */
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Significant {

}
