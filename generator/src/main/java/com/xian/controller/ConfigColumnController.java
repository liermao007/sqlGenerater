package com.xian.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import com.xian.domain.bo.ConfigColumnBo;
import com.xian.domain.vo.ConfigColumnVo;
import com.xian.service.IConfigColumnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库相关表字段
 * 
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config/configColumn")
public class ConfigColumnController extends BaseController {

    private final IConfigColumnService configColumnService;
    
    /**
     * 分页查询数据库相关表字段
     * @return
     */
    @SaCheckPermission("configColumn:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigColumnVo> list(ConfigColumnBo bo, PageQuery pageQuery) {
        return configColumnService.selectPageList(bo, pageQuery);
    }
    
    /**
     * 根据ID获取数据库相关表字段
     * @param id 数据ID
     * @return
     */
    @SaCheckPermission("configColumn:query")
    @GetMapping(value = "/{id}")
    public R<ConfigColumnVo> getInfo(@PathVariable Long id) {
        return R.ok(configColumnService.selectById(id));
    }

    /**
     * 新增数据库相关表字段
     * @return
     */
    @SaCheckPermission("configColumn:add")
    @Log(title = "数据库相关表字段管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigColumnBo bo) {
        return toAjax(configColumnService.insert(bo));
    }

    /**
     * 修改数据库相关表字段
     * @return
     */
    @SaCheckPermission("configColumn:edit")
    @Log(title = "数据库相关表字段管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigColumnBo bo) {
        return toAjax(configColumnService.update(bo));
    }

    /**
     * 删除数据库相关表字段
     * @param ids 数据ID字符串集合
     * @return
     */
    @SaCheckPermission("configColumn:remove")
    @Log(title = "数据库相关表字段管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configColumnService.deleteByIds(ids));
    }
}

