package io.neko.workshop.module.infra.service.codegen;

import io.neko.workshop.module.infra.test.BaseDbUnitTest;
import org.junit.jupiter.api.Test;

import jakarta.annotation.Resource;

class CodegenServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CodegenServiceImpl codegenService;

    @Test
    public void tetCreateCodegenTable() {
        codegenService.createCodegen(0L, "infra_test_demo");
//        infraCodegenService.createCodegenTable("infra_codegen_table");
//        infraCodegenService.createCodegen("infra_codegen_column");
    }

}
