package io.neko.workshop.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目的启动类
 * @author 灵喵工坊
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${neko-workshop.info.base-package}
@SpringBootApplication(scanBasePackages = {"${neko-workshop.info.base-package}.module"})
//开启定时任务
@EnableScheduling
public class NekoWorkshopServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NekoWorkshopServerApplication.class, args);
    }




}
