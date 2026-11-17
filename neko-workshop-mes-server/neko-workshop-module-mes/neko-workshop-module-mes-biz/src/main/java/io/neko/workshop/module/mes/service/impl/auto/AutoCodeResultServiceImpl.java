package io.neko.workshop.module.mes.service.impl.auto;

import io.neko.workshop.module.mes.dal.dataobject.auto.AutoCodeResult;
import io.neko.workshop.module.mes.dal.mysql.auto.AutoCodeResultMapper;
import io.neko.workshop.module.mes.service.auto.AutoCodeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutoCodeResultServiceImpl implements AutoCodeResultService {

    private final AutoCodeResultMapper sysAutoCodeResultMapper;

    @Override
    public List<AutoCodeResult> list(AutoCodeResult sysAutoCodeResult) {
        return sysAutoCodeResultMapper.selectSysAutoCodeResultList(sysAutoCodeResult);
    }

    @Override
    public void saveAutoCodeResult(AutoCodeResult sysAutoCodeResult) {
        sysAutoCodeResultMapper.insert(sysAutoCodeResult);
    }

    @Override
    public void updateAutoCodeResult(AutoCodeResult sysAutoCodeResult) {
        sysAutoCodeResultMapper.updateById(sysAutoCodeResult);
    }
}
