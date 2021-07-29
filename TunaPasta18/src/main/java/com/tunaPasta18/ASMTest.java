package com.tunaPasta18;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author TunaSashimi
 * @date 2021/7/28 15:41
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ASMTest {
}
