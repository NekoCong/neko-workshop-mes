package io.neko.workshop.module.mes.service.impl.pro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedbackHoursExcelVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedbackHoursVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.FeedbackHours;
import io.neko.workshop.module.mes.dal.mysql.pro.FeedbackHoursMapper;
import io.neko.workshop.module.mes.service.pro.FeedbackHoursService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FeedbackHoursServiceImpl  implements FeedbackHoursService {
    private final FeedbackHoursMapper feedbackHoursMapper;



    @Override
    public void removeFeedBackHoursById(Long id) {
        QueryWrapper<FeedbackHours> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("feedback_id",id);
        feedbackHoursMapper.delete(queryWrapper);
    }

    @Override
    public PageResult<FeedbackHours> list(FeedbackHoursVo feedbackHoursVO) throws ParseException {
        return feedbackHoursMapper.list(feedbackHoursVO);
    }




    @Override
    public List<FeedbackHoursExcelVo> listData(List<Long> ids) {
        List<FeedbackHours> dictList;
        if (ids != null && !ids.isEmpty()) {
            dictList = feedbackHoursMapper.selectBatchIds(ids);
        } else {
            dictList = feedbackHoursMapper.selectList(null);
        }
        ArrayList<FeedbackHoursExcelVo> excelDictDTOList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            FeedbackHoursExcelVo excelDictDTO = new FeedbackHoursExcelVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;
    }

}
