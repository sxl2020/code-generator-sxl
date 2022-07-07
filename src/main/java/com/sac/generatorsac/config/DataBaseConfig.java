package com.sac.generatorsac;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @Package: com.sac.saccodegenerator
 * @Description: <表格和数据源配置项>
 * @Author: milla
 * @CreateDate: 2020/11/12 17:48
 * @UpdateUser: milla
 * @UpdateDate: 2020/11/12 17:48
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public final class DataBaseConstant {
    /**
     * 作者
     */
    public static String AUTHOR = "qiancheng-su" ;
    
    /**
     * 表名
     */
    public static String[] TABLES = {
            "rob_inspection_device"
    };
    
    /**
     * 数据库
     */
    public static String username = "root" ;
    public static String password = "Sac@8888" ;
    public static String url = "jdbc:mysql://171.20.99.220:13306/rs6500?useAffectedRows=true&allowMultiQueries=true&characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&maxReconnects=10" ;
    public static DbType DB_TYPE = DbType.MYSQL;
    public static String driverClassName = "com.mysql.cj.jdbc.Driver" ;
}
