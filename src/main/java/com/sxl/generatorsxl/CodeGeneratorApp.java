package com.sxl.generatorsxl;

import com.sxl.generatorsxl.config.DataBaseConfig;
import com.sxl.generatorsxl.config.PackageConfig;
import com.sxl.generatorsxl.generator.JavaOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sxl
 * @description: 代码生成器
 * @date 2022/7/7 14:00
 */
/**
 * @author sxl
 */
@SpringBootApplication
public class CodeGeneratorApp {
    public static void main(String[] args) {
        // 执行代码前，请于无参构造函数中配置生成代码的数据库信息
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        // 执行代码前，请于无参构造函数中配置生成代码的目录信息
        PackageConfig packageConfig = new PackageConfig();
        JavaOutput.builder(dataBaseConfig,packageConfig);
    }
}
