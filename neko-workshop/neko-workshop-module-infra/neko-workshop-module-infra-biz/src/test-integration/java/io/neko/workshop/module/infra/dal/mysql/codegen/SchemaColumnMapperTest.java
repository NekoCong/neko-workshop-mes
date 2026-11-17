package io.neko.workshop.module.infra.dal.mysql.codegen;

import io.neko.workshop.module.tool.dal.dataobject.codegen.SchemaColumnDO;
import io.neko.workshop.module.tool.test.BaseDbUnitTest;
import org.junit.jupiter.api.Test;

import jakarta.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaColumnMapperTest extends BaseDbUnitTest {

    @Resource
    private SchemaColumnMapper schemaColumnMapper;

    @Test
    public void testSelectListByTableName() {
        List<SchemaColumnDO> columns = schemaColumnMapper.selectListByTableName("", "inf_config");
        assertTrue(columns.size() > 0);
    }

}
