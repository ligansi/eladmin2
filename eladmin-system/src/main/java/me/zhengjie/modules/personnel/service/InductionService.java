package me.zhengjie.modules.personnel.service;

import me.zhengjie.modules.personnel.domain.Induction;
import me.zhengjie.modules.personnel.service.dto.InductionDTO;
import me.zhengjie.modules.personnel.service.dto.InductionQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Li GanSi
* @date 2020-04-08
*/
public interface InductionService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(InductionQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<InductionDTO>
    */
    List<InductionDTO> queryAll(InductionQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return InductionDTO
     */
    InductionDTO findById(Long id);

    InductionDTO create(Induction resources);

    void update(Induction resources);

    void delete(Long id);

    void download(List<InductionDTO> all, HttpServletResponse response) throws IOException;
}