package io.neko.workshop.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ClassUtils;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author 灵喵工坊
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n----------------------------------------------------------\n\t" +
                                            "灵喵工坊平台启动成功！\n\t" +
                                            "官方网站: \t{} \n" +
                            "----------------------------------------------------------",
                       "https://github.com/nekoworks");
/*
            // 数据报表
            if (isNotPresent("io.neko.workshop.module.report.framework.security.config.SecurityConfiguration")) {
                System.out.println("[报表模块 neko-workshop-module-report - 已禁用][参考 https://github.com/nekoworks/report/ 开启]");
            }
            // 工作流
            if (isNotPresent("io.neko.workshop.framework.flowable.config.NekoWorksFlowableConfiguration")) {
                System.out.println("[工作流模块 neko-workshop-module-bpm - 已禁用][参考 https://github.com/nekoworks/bpm/ 开启]");
            }
            // 微信公众号
            if (isNotPresent("io.neko.workshop.module.mp.framework.mp.config.MpConfiguration")) {
                System.out.println("[微信公众号 neko-workshop-module-mp - 已禁用][参考 https://github.com/nekoworks/mp/build/ 开启]");
            }
            // 商城
            if (isNotPresent("io.neko.workshop.module.trade.framework.web.config.TradeWebConfiguration")) {
                System.out.println("[商城系统 neko-workshop-module-mall - 已禁用][参考 https://github.com/nekoworks/mall/build/ 开启]");
            }

 */
        });
    }

    private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }

}
