## 工程简介
根据数据库表，自动生成
entity,xml,mapper.service,controller 等一套代码


## 使用说明

1.进入 CodeGeneratorApp 运行生成代码
```$xslt
 public static void main(String[] args) {
        // 执行代码前，请于无参构造函数中配置生成代码的数据库信息
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        // 执行代码前，请于无参构造函数中配置生成代码的目录信息
        PackageConfig packageConfig = new PackageConfig();
        JavaOutput.builder(dataBaseConfig,packageConfig);
    }
```

2.需要配置两个配置类：DataBaseConfig 和 PackageConfig

其中，DataBaseConfig配置项信息如下：
```$xslt
  this.tables =new String[] {
                "ips_user"
        };
        this.username = "root" ;
        this.password = "xxxxx" ;
        this.url = "jdbc:mysql://127.0.0.1:13306/xxxxx?useAffectedRows=true&allowMultiQueries=true&characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&maxReconnects=10" ;
        this.dbType = DbType.MYSQL;
        this.driverClassName = "com.mysql.cj.jdbc.Driver" ;
```
PackageConfig配置信息如下：
```$xslt
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
```