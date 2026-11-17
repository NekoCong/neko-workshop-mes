package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.OtherPicture;
import io.neko.workshop.module.mes.dal.mysql.qc.OtherPictureMapper;
import io.neko.workshop.module.mes.service.qc.OtherPictureService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Service
@RequiredArgsConstructor
public class OtherPictureServiceImpl implements OtherPictureService {

    private final OtherPictureMapper otherPictureMapper;


    @Override
    public Integer saveOtherPicture(OtherPicture otherPicture) {
        return otherPictureMapper.insert(otherPicture);
    }
}
