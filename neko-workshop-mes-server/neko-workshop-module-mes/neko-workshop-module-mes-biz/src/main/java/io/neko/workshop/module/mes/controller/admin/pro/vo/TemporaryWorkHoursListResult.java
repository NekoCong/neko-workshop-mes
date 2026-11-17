package io.neko.workshop.module.mes.controller.admin.pro.vo;


import io.neko.workshop.module.mes.dal.dataobject.pro.TemporaryWorkHours;
import lombok.Data;
import java.util.List;



@Data
public class TemporaryWorkHoursListResult {
    private Double totalWorkhours;
    private List<TemporaryWorkHours> list;
}
