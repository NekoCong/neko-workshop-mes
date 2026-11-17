package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.ReceivePicture;
import io.neko.workshop.module.mes.dal.mysql.qc.ReceivePictureMapper;
import io.neko.workshop.module.mes.service.qc.ReceivePictureService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 14:59
 */
@Service
@RequiredArgsConstructor
public class ReceivePictureServiceImpl implements ReceivePictureService {


    private final ReceivePictureMapper receivePictureMapper;

    @Override
    public Integer insertProcessPicture(ReceivePicture receivePicture) {
        return receivePictureMapper.insert(receivePicture);
    }
}
