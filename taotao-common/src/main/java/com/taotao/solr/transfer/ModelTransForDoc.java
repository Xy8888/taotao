package com.taotao.solr.transfer;

import com.taotao.solr.annotation.SolrField;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelTransForDoc {

    /**
     * 将SolrInputDocument转成相应的对象
     */
    public static <T> T doc2Model(SolrDocument doc, Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 获取定义的所有属性域
        Field[] fields = clazz.getDeclaredFields();
        //对属性域进行迭代判断是否有对应的注解
        if (fields != null && fields.length > 0) {
            //构建一个实例
            T newInstance = clazz.newInstance();
            //循环迭代
            for (Field field : fields) {
                //获取域上的SolrTransf注解
                SolrField solrField = field.getAnnotation(SolrField.class);
                //注解不为空
                if (solrField != null && solrField.stored()) {
                    //获取域中的值
                    String value = doc.getFieldValue(solrField.value()).toString();
                    //获取域的数据类型
                    Class<?> type = field.getType();
                    //找到它的set属性方法   setXxxxx
                    Method method = clazz.getDeclaredMethod("set" + upper(field.getName()), type);
                    //方法存在，则调用方法赋值
                    if (method != null) {
                        method.invoke(newInstance, ConvertUtils.convert(value, type));
                    }
                }
            }
            return (T) newInstance;
        }
        return null;
    }

    /**
     * 将对象转成SolrInputDocument
     */
    public static SolrInputDocument model2Doc(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //创建Document对象
        SolrInputDocument doc = new SolrInputDocument();
        //获取对应JavaBean的实例的Class的atic 所有属性
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //判断属性上是否存在SolrField注解
            SolrField solrField = field.getAnnotation(SolrField.class);
            //获取对应的域
            if (solrField != null) {
                String value = solrField.value();
                //获取对应于所对应的属性的值-------->调用getXxx
                //获取方法
                Method method = clazz.getMethod("get" + upper(field.getName()), null);
                if (method != null) {
                    //调用获取对应的值
                    Object result = method.invoke(object, null);
                    //填充
                    doc.addField(value, result);
                }
            }
        }
        return doc;
    }

    /**
     * 将对象属性第一个字母转大写
     * @param name
     * @return
     */
    private static String upper(String name) {
        if (name == null || name.trim() == "") {
            return null;
        }
        String head = name.substring(0, 1).toUpperCase();
        return head + name.substring(1);
    }
}
