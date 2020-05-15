package me.zhengjie.modules.personnel.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.personnel.domain.Induction;
import me.zhengjie.modules.personnel.service.dto.InductionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Li GanSi
* @date 2020-04-08
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InductionMapper extends BaseMapper<InductionDTO, Induction> {

}