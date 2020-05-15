package me.zhengjie.modules.personnel.service.impl;

import me.zhengjie.modules.personnel.domain.Induction;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.personnel.repository.InductionRepository;
import me.zhengjie.modules.personnel.service.InductionService;
import me.zhengjie.modules.personnel.service.dto.InductionDTO;
import me.zhengjie.modules.personnel.service.dto.InductionQueryCriteria;
import me.zhengjie.modules.personnel.service.mapper.InductionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Li GanSi
* @date 2020-04-08
*/
@Service
@CacheConfig(cacheNames = "induction")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InductionServiceImpl implements InductionService {

    private final InductionRepository inductionRepository;

    private final InductionMapper inductionMapper;

    public InductionServiceImpl(InductionRepository inductionRepository, InductionMapper inductionMapper) {
        this.inductionRepository = inductionRepository;
        this.inductionMapper = inductionMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(InductionQueryCriteria criteria, Pageable pageable){
        Page<Induction> page = inductionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(inductionMapper::toDto));
    }

    @Override
    @Cacheable
    public List<InductionDTO> queryAll(InductionQueryCriteria criteria){
        return inductionMapper.toDto(inductionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public InductionDTO findById(Long id) {
        Induction induction = inductionRepository.findById(id).orElseGet(Induction::new);
        ValidationUtil.isNull(induction.getId(),"Induction","id",id);
        return inductionMapper.toDto(induction);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public InductionDTO create(Induction resources) {
        return inductionMapper.toDto(inductionRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Induction resources) {
        Induction induction = inductionRepository.findById(resources.getId()).orElseGet(Induction::new);
        ValidationUtil.isNull( induction.getId(),"Induction","id",resources.getId());
        induction.copy(resources);
        inductionRepository.save(induction);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        inductionRepository.deleteById(id);
    }


    @Override
    public void download(List<InductionDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (InductionDTO induction : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("姓名", induction.getUsername());
            map.put("性别", induction.getSex());
            map.put("出生日期", induction.getBirthdate());
            map.put("年龄", induction.getAge());
            map.put("教育", induction.getEducation());
            map.put("入职日期", induction.getEntryDate());
            map.put("部门编号", induction.getDeptId());
            map.put("岗位编号", induction.getJobId());
            map.put("毕业院校", induction.getGraduateSchool());
            map.put("毕业日期", induction.getGraduateDate());
            map.put("毕业专业", induction.getGraduateMajor());
            map.put("面试记录", induction.getInterview());
            map.put("创建日期", induction.getCreateTime());
            map.put("创建用户", induction.getCreateUser());
            map.put("状态", induction.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}