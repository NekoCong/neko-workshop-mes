package io.neko.workshop.module.mes.utils;

/**
 * io.neko.workshop.module.mes.utils
 *
 * @author 灵喵工坊
 * @time 2023/8/4 15:08
 */

@FunctionalInterface
public interface BeanCopyUtilCallBack <S, T> {

    void callBack(S t, T s);

}
