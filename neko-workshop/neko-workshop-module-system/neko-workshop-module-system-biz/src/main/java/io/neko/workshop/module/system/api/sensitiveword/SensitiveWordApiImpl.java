package io.neko.workshop.module.system.api.sensitiveword;

import io.neko.workshop.module.system.service.sensitiveword.SensitiveWordService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 敏感词 API 实现类
 *
 * @author 永不言败
 */
@Service
@RequiredArgsConstructor
public class SensitiveWordApiImpl implements SensitiveWordApi {

    private final SensitiveWordService sensitiveWordService;

    @Override
    public List<String> validateText(String text, List<String> tags) {
        return sensitiveWordService.validateText(text, tags);
    }

    @Override
    public boolean isTextValid(String text, List<String> tags) {
        return sensitiveWordService.isTextValid(text, tags);
    }
}
