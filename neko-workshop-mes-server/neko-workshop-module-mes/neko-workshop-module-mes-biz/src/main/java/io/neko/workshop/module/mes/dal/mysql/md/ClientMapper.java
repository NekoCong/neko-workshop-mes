package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ClientQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Client;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 客户 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface ClientMapper extends BaseMapperX<Client> {

    /**
     * 客户条件分页查询
     * @param reqVO
     * @return  PageResult<Client>
     */
    default PageResult<Client> selectPage(ClientQueryVo reqVO) {
        LambdaQueryWrapperX<Client> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(reqVO.getClientCode())){
            queryWrapper.like(Client::getClientCode,reqVO.getClientCode());
        }
        if(StringUtils.isNotBlank(reqVO.getClientName())){
            queryWrapper.like(Client::getClientName,reqVO.getClientName());
        }
        if(StringUtils.isNotBlank(reqVO.getClientNick())){
            queryWrapper.like(Client::getClientNick,reqVO.getClientNick());
        }
        if(StringUtils.isNotBlank(reqVO.getEnableFlag())){
            queryWrapper.eq(Client::getEnableFlag,reqVO.getEnableFlag());
        }
        if(StringUtils.isNotBlank(reqVO.getClientEn())){
            queryWrapper.like(Client::getClientEn,reqVO.getClientEn());
        }
        if(StringUtils.isNotBlank(reqVO.getTel())){
            queryWrapper.like(Client::getTel,reqVO.getTel());
        }
        if(StringUtils.isBlank(reqVO.getClientCode()) && StringUtils.isBlank(reqVO.getClientName()) && StringUtils.isBlank(reqVO.getClientNick())
                && StringUtils.isBlank(reqVO.getEnableFlag()) && StringUtils.isBlank(reqVO.getClientEn()) &&StringUtils.isBlank(reqVO.getTel())){
            queryWrapper.isNotNull(Client::getId);
        }
        queryWrapper.orderByDesc(Client::getId);
        return selectPage(reqVO, queryWrapper);
    }

    @Select("select client_code from md_client where client_name = #{name}")
    String findCodeByName(String name);


}
