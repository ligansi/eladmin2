package me.zhengjie.modules.personnel.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.personnel.domain.Induction;
import me.zhengjie.modules.personnel.service.InductionService;
import me.zhengjie.modules.personnel.service.dto.InductionQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Li GanSi
* @date 2020-04-08
*/
@Api(tags = "Induction管理")
@RestController
@RequestMapping("/api/induction")
public class InductionController {

    private final InductionService inductionService;

    public InductionController(InductionService inductionService) {
        this.inductionService = inductionService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('induction:list')")
    public void download(HttpServletResponse response, InductionQueryCriteria criteria) throws IOException {
        inductionService.download(inductionService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Induction")
    @ApiOperation("查询Induction")
    @PreAuthorize("@el.check('induction:list')")
    public ResponseEntity getInductions(InductionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(inductionService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Induction")
    @ApiOperation("新增Induction")
    @PreAuthorize("@el.check('induction:add')")
    public ResponseEntity create(@Validated @RequestBody Induction resources){
        return new ResponseEntity<>(inductionService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Induction")
    @ApiOperation("修改Induction")
    @PreAuthorize("@el.check('induction:edit')")
    public ResponseEntity update(@Validated @RequestBody Induction resources){
        inductionService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除Induction")
    @ApiOperation("删除Induction")
    @PreAuthorize("@el.check('induction:del')")
    public ResponseEntity delete(@PathVariable Long id){
        inductionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}