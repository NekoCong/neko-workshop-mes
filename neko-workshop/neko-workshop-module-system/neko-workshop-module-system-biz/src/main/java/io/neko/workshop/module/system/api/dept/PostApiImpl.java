package io.neko.workshop.module.system.api.dept;

import io.neko.workshop.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

/**
 * 岗位 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class PostApiImpl implements PostApi {

    private final PostService postService;

    @Override
    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

}
