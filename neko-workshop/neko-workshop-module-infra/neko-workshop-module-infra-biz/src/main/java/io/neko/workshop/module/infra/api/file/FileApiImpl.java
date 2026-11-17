package io.neko.workshop.module.infra.api.file;

import io.neko.workshop.module.infra.service.file.FileService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * 文件 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class FileApiImpl implements FileApi {

    private final FileService fileService;

    @Override
    public String createFile(String name, String path, byte[] content) {
        return fileService.createFile(name, path, content);
    }

}
