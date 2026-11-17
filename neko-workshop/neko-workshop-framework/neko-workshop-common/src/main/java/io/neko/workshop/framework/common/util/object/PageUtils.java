package io.neko.workshop.framework.common.util.object;

import io.neko.workshop.framework.common.pojo.PageParam;

/**
 * {@link io.neko.workshop.framework.common.pojo.PageParam} 工具类
 *
 * @author 灵喵工坊
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
