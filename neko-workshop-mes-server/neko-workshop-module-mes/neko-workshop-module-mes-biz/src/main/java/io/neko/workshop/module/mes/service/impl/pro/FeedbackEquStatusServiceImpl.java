package io.neko.workshop.module.mes.service.impl.pro;

import io.neko.workshop.module.mes.dal.dataobject.pro.FeedbackEquStatus;
import io.neko.workshop.module.mes.dal.mysql.pro.FeedbackEquStatusMapper;
import io.neko.workshop.module.mes.service.pro.FeedbackEquStatusService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class FeedbackEquStatusServiceImpl implements FeedbackEquStatusService {


    private final FeedbackEquStatusMapper feedbackEquStatusMapper;



    @Override
    public Integer save(FeedbackEquStatus feedbackEquStatus) {
        return feedbackEquStatusMapper.insert(feedbackEquStatus);
    }

    @Override
    public Integer removeFeedbackEquStatusById(Long id) {
        return feedbackEquStatusMapper.deleteById(id);
    }
}
