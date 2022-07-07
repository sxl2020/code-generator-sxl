package com.sac.generatorsac.config;

import lombok.Data;

import java.io.File;

/**
 * @Package: com.study.generator
 * @Description: <自定义生成的文件路径和包路径>
 * @Author: milla
 * @CreateDate: 2020/11/12 17:47
 * @UpdateUser: milla
 * @UpdateDate: 2020/11/12 17:47
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Data
public class PackageInfoConfig {

    /**
     * 作者
     */
    private String author = "qiancheng-su" ;

    /**
     * 输出的Java源代码路径
     */
    private  String javaSrcPath = "" ;

    private  String packageName = "com.sac.xxxproject" ;

    /**
     * 生成文件路径、Dao、XML、Service、Controller
     * 父包名路径(文件输出路径,也是导包的路径)，用于配置生成的文件位置
     */
    private  String entityPackageLocation = "/entity/" ;
    private  String mapperPackageLocation = "/mapper/" ;
    private  String xmlPackageLocation = "/resources/mapper/" ;
    private  String servicePackageLocation = "/service/" ;
    private  String serviceImplPackageLocation = "/service/impl/" ;
    private  String controllerPackageLocation = "/controller/" ;



}

