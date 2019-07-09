package com.taotao.solr.annotation;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SolrField {

    //标注映射关系
    String value() default "";

    //标注是否存储
    boolean stored() default true;

}
