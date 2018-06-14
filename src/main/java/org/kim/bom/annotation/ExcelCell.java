package org.kim.bom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Excel单元格的注解
 */
public @interface ExcelCell {
    /**
     * 对应Excel列名
     * @return
     */
    String columnName() default "";

    /**
     * 对应Excel列的位置
     * @return
     */
    int columnIndex() default -1;

    /**
     * 单元格的位置
     * @return
     */
    int rowIndex() default -1;
}
