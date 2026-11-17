package io.neko.workshop.module.mes.controller.admin.auto;
import io.neko.workshop.module.mes.dal.dataobject.auto.AutoCodePart;
import org.springframework.stereotype.Component;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PartTypeHandler {

    private final List<PartTypeTemplate> partTypeTemplates;


    public String choiceExecute(AutoCodePart sysAutoCodePart){
        String partType = sysAutoCodePart.getPartType();

        return partTypeTemplates.get(PartTypeEnum.getByCode(partType).getBeanIndex()).partHandle(sysAutoCodePart);
    }

}
