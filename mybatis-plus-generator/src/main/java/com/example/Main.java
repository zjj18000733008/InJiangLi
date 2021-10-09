package com.example;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/4
 */
public class Main {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ijl_im?characterEncoding=UTF-8&autoReconnect=true&useSSL"
                        + "=false",
                "root", "baidu@123")
                .globalConfig(builder -> {
                    builder.author("zhongjunjie") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/apple/IdeaProjects/InJiangLi/mybatis-plus-generator/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("im") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/apple/IdeaProjects"
                                    + "/InJiangLi/mybatis-plus-generator/src/main/resources/mapper")); // 设置mapperXml
                    // 生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("im_hello") // 设置需要生成的表名
                            .addTablePrefix("im_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
