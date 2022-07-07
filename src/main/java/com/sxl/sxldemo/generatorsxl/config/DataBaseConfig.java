package com.sxl.sxldemo.generatorsxl.config;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

/**
 * @Package: com.sxl.sxlcodegenerator
 * @Description: <表格和数据源配置项>
 * @Author: sxl
 * @CreateDate: 2020/11/12 17:48
 * @UpdateUser: sxl
 * @UpdateDate: 2020/11/12 17:48
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Data
public class DataBaseConfig {

    /**
     * 表名
     */
    private  String[] tables;
    
    /**
     * 数据库
     */
    private  String username;
    private  String password ;
    private  String url;
    private  DbType dbType;
    private  String driverClassName;


    public DataBaseConfig(){
        this.tables =new String[] {
                "ips_user"
        };
        this.username = "root" ;
        this.password = "xxxxx" ;
        this.url = "jdbc:mysql://127.0.0.1:13306/xxxxx?useAffectedRows=true&allowMultiQueries=true&characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&maxReconnects=10" ;
        this.dbType = DbType.MYSQL;
        this.driverClassName = "com.mysql.cj.jdbc.Driver" ;
    }
}
