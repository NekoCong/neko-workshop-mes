package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.ProcessPicture;
import io.neko.workshop.module.mes.dal.mysql.qc.ProcessPictureMapper;
import io.neko.workshop.module.mes.service.qc.ProcessPictureService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/11 17:34
 */
@Service
@RequiredArgsConstructor
public class ProcessPictureServiceImpl implements ProcessPictureService {

    private final ProcessPictureMapper processPictureMapper;


    @Override
    public void insertProcessPicture(ProcessPicture processPicture) {
        processPictureMapper.insert(processPicture);
    }
}
