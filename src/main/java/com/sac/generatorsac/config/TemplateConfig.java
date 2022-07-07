package com.sac.generatorsac;

/**
 * @Package: com.study.generator
 * @Description: <模版位置配置类,模板引擎默认为：velocity>
 * @Author: qiancheng-su
 * @CreateDate: 2020/11/12 18:09
 * @UpdateUser: qiancheng-su
 * @UpdateDate: 2020/11/12 18:09
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public final class TemplateConstant {

    /**
     * entity输出模板
     */
    public static String ENTITY_TEMPLATE = "templates/entity.java.vm" ;
    /**
     * mapper.xml输出模板
     */
    public static String XML_TEMPLATE = "templates/mapper.xml.vm" ;
    /**
     * mapper.java输出模板
     */
    public static String MAPPER_TEMPLATE = "templates/mapper.java.vm" ;
    /**
     * service输出模板
     */
    public static String SERVICE_TEMPLATE = "templates/service.java.vm" ;
    /**
     * serviceImpl输出模板
     */
    public static String SERVICE_IMPL_TEMPLATE = "templates/serviceImpl.java.vm" ;

    /**
     * controller输出模板
     */
    public static String CONTROLLER_TEMPLATE = "templates/controller.java.vm" ;
}
