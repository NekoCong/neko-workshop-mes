package io.neko.workshop.module.mes.dal.dataobject.pro;

import lombok.Data;
import java.util.List;



/**
 * 甘特图任务
 * @author 灵喵工坊
 */
@Data
public class GanttTask {

    private List<GanttData> data;

    private List<GanttLink> links;

}
