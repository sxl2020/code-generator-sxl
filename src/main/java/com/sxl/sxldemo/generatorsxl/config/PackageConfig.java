package com.sxl.sxldemo.generatorsxl.config;

import lombok.Data;

/**
 * @Package: com.study.generator
 * @Description: <自定义生成的文件路径和包路径>
 * @Author: sxl
 * @CreateDate: 2020/11/12 17:47
 * @UpdateUser: sxl
 * @UpdateDate: 2020/11/12 17:47
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Data
public class PackageConfig {

    /**
     * 作者
     */
    private String author;

    /**
     * 输出的Java源代码路径:绝对路径,以src结尾
     */
    private String javaSrcPath ;
    /**
     * resources资源文件路径:绝对路径,以resources结尾
     */
    private String resourcePath ;

    private String projectPackage;

    private String modelName;

    /**
     * 生成文件路径、Dao、XML、Service、Controller
     * 父包名路径(文件输出路径,也是导包的路径)，用于配置生成的文件位置
     */
    private  String entityPackage ;
    private  String mapperPackage ;
    private  String servicePackage  ;
    private  String serviceImplPackage ;
    private  String controllerPackage;
    private  String xmlPackage;


    /**
     * 公共类所在的包: 生成Controller时，需要填写此项目
     * 常见的统一返回格式
     */
    private String commonEntityPackage ;



    public PackageConfig(){
        /**
         * 作者
         */
        this.author = "sxl" ;

        /**
         * 输出的Java源代码路径:绝对路径,以src
         */
        this.javaSrcPath = "E:\\code_birth_plcace\\output\\sxlDemo\\src\\main\\java" ;
        this.resourcePath = "E:\\code_birth_plcace\\output\\sxlDemo\\src\\main\\resources" ;

        /**
         * 在项目pom文件中的 groupId.artifactId
         */
        this.projectPackage = "com.sxl.sxlDemo" ;


        /**
         * 生成文件路径、Dao、XML、Service、Controller
         * 父包名路径(文件输出路径,也是导包的路径)，用于配置生成的文件位置
         */
        this.entityPackage = "com.sxl.sxlDemo.dpo" ;
        this.mapperPackage = "com.sxl.sxlDemo.mapper" ;
        this.servicePackage = "com.sxl.sxlDemo.service" ;
        this.serviceImplPackage = "com.sxl.sxlDemo.service.impl" ;
        this.controllerPackage = "com.sxl.sxlDemo.controller" ;
        this.xmlPackage = "mapper.mysql" ;

        /**
         * 生成代码的功能模块名称。若不填，则直接在上面各类文件的package目录下生成
         */
        this.modelName = "";

        /**
         * 公共类所在的包: 生成Controller时，需要填写此项
         * 常见的公共类有： Controller中统一的返回格式对象
         */
        this.commonEntityPackage = "com.sxl.sxlDemo.dto.bean" ;
    }

}

