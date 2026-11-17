package io.neko.workshop.framework.test.core.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.common.exception.ServiceException;
import io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 单元测试，assert 断言工具类
 *
 * @author 灵喵工坊
 */
public class AssertUtils {

    /**
     * 时间比较的容差：1 微秒
     *
     * 数据库（H2 / MySQL）的 datetime、timestamp 列最多保留 6 位小数（微秒），而 {@link LocalDateTime#now()}
     * 是 9 位（纳秒）；写入再读出时会被截断，甚至因四舍五入而进位到下一微秒。因此比较时间字段时允许
     * 1 微秒的误差，避免存储精度差异导致断言误报。
     */
    private static final long TEMPORAL_TOLERANCE_NANOS = 1000L;

    /**
     * 比对两个对象的属性是否一致
     *
     * 注意，如果 expected 存在的属性，actual 不存在的时候，会进行忽略
     *
     * @param expected 期望对象
     * @param actual 实际对象
     * @param ignoreFields 忽略的属性数组
     */
    public static void assertPojoEquals(Object expected, Object actual, String... ignoreFields) {
        Field[] expectedFields = ReflectUtil.getFields(expected.getClass());
        Arrays.stream(expectedFields).forEach(expectedField -> {
            // 忽略 jacoco 自动生成的 $jacocoData 属性的情况
            if (expectedField.isSynthetic()) {
                return;
            }
            // 如果是忽略的属性，则不进行比对
            if (ArrayUtil.contains(ignoreFields, expectedField.getName())) {
                return;
            }
            // 忽略不存在的属性
            Field actualField = ReflectUtil.getField(actual.getClass(), expectedField.getName());
            if (actualField == null) {
                return;
            }
            // 比对
            Object expectedValue = ReflectUtil.getFieldValue(expected, expectedField);
            Object actualValue = ReflectUtil.getFieldValue(actual, actualField);
            if (isTemporalEquals(expectedValue, actualValue)) {
                return;
            }
            Assertions.assertEquals(expectedValue, actualValue,
                    String.format("Field(%s) 不匹配", expectedField.getName()));
        });
    }

    /**
     * 比对两个对象的属性是否一致
     *
     * 注意，如果 expected 存在的属性，actual 不存在的时候，会进行忽略
     *
     * @param expected 期望对象
     * @param actual 实际对象
     * @param ignoreFields 忽略的属性数组
     * @return 是否一致
     */
    public static boolean isPojoEquals(Object expected, Object actual, String... ignoreFields) {
        Field[] expectedFields = ReflectUtil.getFields(expected.getClass());
        return Arrays.stream(expectedFields).allMatch(expectedField -> {
            // 如果是忽略的属性，则不进行比对
            if (ArrayUtil.contains(ignoreFields, expectedField.getName())) {
                return true;
            }
            // 忽略不存在的属性
            Field actualField = ReflectUtil.getField(actual.getClass(), expectedField.getName());
            if (actualField == null) {
                return true;
            }
            Object expectedValue = ReflectUtil.getFieldValue(expected, expectedField);
            Object actualValue = ReflectUtil.getFieldValue(actual, actualField);
            return isTemporalEquals(expectedValue, actualValue) || Objects.equals(expectedValue, actualValue);
        });
    }

    /**
     * 判断两个时间值是否在容差范围内相等（非时间类型一律返回 false，交由调用方按普通对象比较）
     *
     * @param expected 期望值
     * @param actual 实际值
     * @return 是否在容差范围内相等
     */
    private static boolean isTemporalEquals(Object expected, Object actual) {
        if (expected instanceof LocalDateTime && actual instanceof LocalDateTime) {
            return Math.abs(Duration.between((LocalDateTime) expected, (LocalDateTime) actual).toNanos())
                    <= TEMPORAL_TOLERANCE_NANOS;
        }
        if (expected instanceof LocalTime && actual instanceof LocalTime) {
            return Math.abs(Duration.between((LocalTime) expected, (LocalTime) actual).toNanos())
                    <= TEMPORAL_TOLERANCE_NANOS;
        }
        if (expected instanceof Instant && actual instanceof Instant) {
            return Math.abs(Duration.between((Instant) expected, (Instant) actual).toNanos())
                    <= TEMPORAL_TOLERANCE_NANOS;
        }
        return false;
    }

    /**
     * 执行方法，校验抛出的 Service 是否符合条件
     *
     * @param executable 业务异常
     * @param errorCode 错误码对象
     * @param messageParams 消息参数
     */
    public static void assertServiceException(Executable executable, ErrorCode errorCode, Object... messageParams) {
        // 调用方法
        ServiceException serviceException = assertThrows(ServiceException.class, executable);
        // 校验错误码
        Assertions.assertEquals(errorCode.getCode(), serviceException.getCode(), "错误码不匹配");
        String message = ServiceExceptionUtil.doFormat(errorCode.getCode(), errorCode.getMsg(), messageParams);
        Assertions.assertEquals(message, serviceException.getMessage(), "错误提示不匹配");
    }

}
