package io.neko.workshop.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档地址
 *
 * @author 灵喵工坊
 */
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("https://github.com/nekoworks", "Redis 安装文档"),
    TENANT("https://github.com/nekoworks", "SaaS 多租户文档");

    private final String url;
    private final String memo;

}
