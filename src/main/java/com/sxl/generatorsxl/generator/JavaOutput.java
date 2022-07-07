package com.sxl.generatorsxl.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.CaseFormat;
import com.sxl.generatorsxl.config.DataBaseConfig;
import com.sxl.generatorsxl.config.PackageConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.SLASH;
import static com.baomidou.mybatisplus.core.toolkit.StringPool.UNDERSCORE;
import static com.sxl.generatorsxl.config.TemplateConfig.*;


/**
 * @author sxl
 */
@Data
@Slf4j
public class JavaOutput{
    /**
     * 执行自动代码生成程序
     *
     */
    public static void builder(DataBaseConfig dataBaseConfig, PackageConfig packageConfig) {
        new AutoGenerator().setGlobalConfig(globalConfig(packageConfig.getAuthor()))
                .setDataSource(dataSourceConfig(dataBaseConfig))
                .setStrategy(strategyConfig(dataBaseConfig))
                .setCfg(injectionConfig(packageConfig,dataBaseConfig))
                .setPackageInfo(
                        new com.baomidou.mybatisplus.generator.config.PackageConfig()
                                .setParent(getPackageParent(packageConfig.getProjectPackage()))
                )
                .execute();
    }

    /**
     * 设置表格/字段等生成策略配置
     *
     * @return
     */
    private static StrategyConfig strategyConfig(DataBaseConfig dataBaseConfig) {
        StrategyConfig strategyConfig = new StrategyConfig()
                // 表名生成策略：下划线连转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表字段生成策略：下划线连转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(dataBaseConfig.getTables())
                // 生成controller
                .setRestControllerStyle(true)
                // controller映射地址：驼峰转连字符
                .setControllerMappingHyphenStyle(false)
                // 是否启用builder 模式
                .setChainModel(true)
                // 是否为lombok模型; 需要lombok依赖
                .setEntityLombokModel(true)
                // 生成实体类字段注解
                .setEntityTableFieldAnnotationEnable(true);
        return strategyConfig;
    }

    /**
     * 自定义配置
     *
     */
    private static InjectionConfig injectionConfig(PackageConfig packageConfig,DataBaseConfig dataBaseConfig) {
        /**
         * 自定义返回配置 Map 对象
         * 该对象可以传递到模板引擎通过 cfg.xxx 引用
         */
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入配置
                Map<String, Object> map = new HashMap<>(16);
                //指定表格的名称，在entity生成模板中使用
                map.put("customerTableName", true);
                //controller包路径
                map.put("controllerPackage", packageConfig.getControllerPackage());
                //service包名
                map.put("service", packageConfig.getServiceImplPackage());
                //实现类包名
                map.put("serviceImpl", packageConfig.getServiceImplPackage());
                //mapper包名
                map.put("mapper", packageConfig.getMapperPackage());
                // 公用类包名
                map.put("commonPackage", packageConfig.getCommonEntityPackage());

                for (String tableName : dataBaseConfig.getTables()) {
                    //指定每个类的serialVersionUID
                    long serialVersionUID = UUID.nameUUIDFromBytes(tableName.getBytes()).getLeastSignificantBits();
                    map.put(tableName, Math.abs(serialVersionUID));
                    //计算controller的路径
                    StringBuilder sb = new StringBuilder();
                    if (StringUtils.contains(tableName, UNDERSCORE)) {
                        sb.append(SLASH + tableName.substring(0, tableName.indexOf(UNDERSCORE)));
                        String url = tableName.substring(tableName.indexOf(UNDERSCORE) + 1);
                        sb.append(SLASH + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, url));
                        map.put(tableName + "path", sb.toString());
                        continue;
                    }
                    sb.append(SLASH + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, dataBaseConfig.getUrl()));
                    map.put(tableName + "path", sb.toString());
                }
                this.setMap(map);
            }
        }
                // 判断是否创建文件
                .setFileCreate((configBuilder, fileType, filePath) -> {
                    // 检查文件目录，不存在自动递归创建
                    File file = new File(filePath);
                    boolean exist = file.exists();
                    if (!exist) {
                        file.getParentFile().mkdirs();
                    }
                    return true;
                })
                // 自定义输出文件
                .setFileOutConfigList(fileOutConfigList(packageConfig));
    }

    /**
     * 全局配置
     *
     * @return
     */
    private static GlobalConfig globalConfig(String author) {
        return new GlobalConfig()
                // 打开文件
                .setOpen(false)
                // 文件覆盖
                .setFileOverride(true)
                // 开启activeRecord模式
                .setActiveRecord(false)
                // XML ResultMap: mapper.xml生成查询映射结果
                .setBaseResultMap(true)
                // XML ColumnList: mapper.xml生成查询结果列
                .setBaseColumnList(true)
                // swagger注解; 须添加swagger依赖
                .setSwagger2(true)
                // 作者
                .setAuthor(author)
                //设置服务类名称
                .setServiceName("%sService")
                ;
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig(DataBaseConfig dataBaseConfig) {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(dataBaseConfig.getDbType())
                // 连接驱动
                .setDriverName(dataBaseConfig.getDriverClassName())
                // 地址
                .setUrl(dataBaseConfig.getUrl())
                // 用户名
                .setUsername(dataBaseConfig.getUsername())
                // 密码
                .setPassword(dataBaseConfig.getPassword());
    }


    private static String getPackageParent(String packageName){
        return packageName;
    }

    /**
     * 自定义输出路径
     *
     */
    private static List<FileOutConfig> fileOutConfigList(PackageConfig packageConfig) {
        List<FileOutConfig> list = new ArrayList<>();
        // 当前项目路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:-------------->"+projectPath);
        // 实体类文件输出
        configEntity(list,packageConfig);
        // mapper xml文件输出
        configXml(list,packageConfig);
        // mapper文件输出
        configMapper(list,packageConfig);
        // service文件输出
        configService(list,packageConfig);
        // service impl文件输出
        configServiceImpl(list,packageConfig);
        // controller文件输出
        configController(list,packageConfig);
        return list;
    }

    private static void configEntity( List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(ENTITY_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String entityAbsPath = getPackageAbsPath(packageConfig.getJavaSrcPath(),
                            packageConfig.getEntityPackage(),packageConfig.getModelName());
                    return entityAbsPath + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getEntityName() + StringPool.DOT_JAVA;
                }
            }
        });
    }

    private static void configXml(List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(XML_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String xmlAbsPath = getPackageAbsPath(packageConfig.getResourcePath(),
                            packageConfig.getXmlPackage(),packageConfig.getModelName());
                    return xmlAbsPath + tableInfo.getMapperName() + StringPool.DOT_XML;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            }
        });
    }


    private static void configMapper(List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(MAPPER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String mapperAbsPath = getPackageAbsPath(packageConfig.getJavaSrcPath(),
                            packageConfig.getMapperPackage(),packageConfig.getModelName());
                    return mapperAbsPath + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            }
        });
    }

    private static void configService(List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(SERVICE_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String serviceAbsPath = getPackageAbsPath(packageConfig.getJavaSrcPath(),
                            packageConfig.getServicePackage(),packageConfig.getModelName());
                    return serviceAbsPath +  tableInfo.getServiceName() + StringPool.DOT_JAVA;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getServiceName() + StringPool.DOT_JAVA;
                }
            }
        });
    }

    private static void configServiceImpl(List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String serviceImplAbsPath = getPackageAbsPath(packageConfig.getJavaSrcPath(),
                            packageConfig.getServicePackage(),packageConfig.getModelName());
                    return serviceImplAbsPath +  tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                }
            }
        });
    }

    private static void configController(List<FileOutConfig> list,PackageConfig packageConfig){
        list.add(new FileOutConfig(CONTROLLER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                try {
                    String controllerAbsPath = getPackageAbsPath(packageConfig.getJavaSrcPath(),
                            packageConfig.getControllerPackage(),packageConfig.getModelName());
                    return controllerAbsPath +  tableInfo.getControllerName() + StringPool.DOT_JAVA;
                }catch (Exception e){
                    log.warn("PackageConfig 部分路径配置为null，请重新配置：{}",packageConfig.toString());
                    return tableInfo.getControllerName() + StringPool.DOT_JAVA;
                }
            }
        });
    }


    /**
     * @description: 将包路径修改为文件路径格式
     * @author sxl
     * @return: {@link String}
     * @date: 2022/7/7 14:36
     */
    private static String getPackageAbsPath(String rootPath,String packageName,String modelName) throws Exception {
        if(StringUtils.isEmpty(rootPath) || StringUtils.isEmpty(packageName)){
            log.error("PackageConfig 部分路径配置为空，请重新配置");
            throw new Exception("PackageConfig 路径配置为空，请重新配置");
        }
        if(!rootPath.endsWith(File.separator)){
            rootPath = rootPath + File.separator;
        }
        if(StringUtils.isEmpty(modelName)){
            return rootPath+packageName.replace('.',File.separatorChar)+File.separator;
        }else {
            return rootPath+packageName.replace('.',File.separatorChar)+File.separator + modelName + File.separator;
        }
    }


}
