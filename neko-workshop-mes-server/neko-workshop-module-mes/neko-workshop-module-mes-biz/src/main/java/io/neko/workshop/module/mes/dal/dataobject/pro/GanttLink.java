package io.neko.workshop.module.mes.dal.dataobject.pro;

import lombok.Data;


/**
 * 甘特图Link
 * @author 灵喵工坊
 */
@Data
public class GanttLink {

    private int id;

    private int source;

    private int target;

    private String itemName;

    private String type;




}
