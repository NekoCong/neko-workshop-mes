package io.neko.workshop.framework.operatelog.config;

import io.neko.workshop.framework.operatelog.core.aop.OperateLogAspect;
import io.neko.workshop.framework.operatelog.core.service.OperateLogFrameworkService;
import io.neko.workshop.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import io.neko.workshop.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class NekoWorksOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect(OperateLogFrameworkService operateLogFrameworkService) {
        return new OperateLogAspect(operateLogFrameworkService);
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
