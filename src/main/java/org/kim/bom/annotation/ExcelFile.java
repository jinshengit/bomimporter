package org.kim.bom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 对Excel导入文件的注解
 */
public @interface ExcelFile {
    /**
     * 忽略的行数
     */
    int ignoreRows() default 0;
    /**
     * 有效的列数
     */
    int columnCount() default 0;

    /**
     * 读取的sheet的索引
     * @return
     */
    int sheetIndex() default 0;
}
