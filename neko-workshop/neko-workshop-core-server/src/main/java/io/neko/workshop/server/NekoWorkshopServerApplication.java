package io.neko.workshop.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 *
 * @author 灵喵工坊
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${neko-workshop.info.base-package}
@SpringBootApplication(scanBasePackages = {"${neko-workshop.info.base-package}.server", "${neko-workshop.info.base-package}.module"})
public class NekoWorkshopServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(NekoWorkshopServerApplication.class, args);
//        new SpringApplicationBuilder(NekoWorkshopServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);

    }

}
