package com.xian.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.xian.domain.bo.ConfigTableBo;
import com.xian.domain.vo.ConfigTableVo;
import com.xian.service.IConfigTableService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库相关表
 * 
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config/configTable")
public class ConfigTableController extends BaseController {

    private final IConfigTableService configTableService;
    
    /**
     * 分页查询数据库相关表
     * @return
     */
    @SaCheckPermission("configTable:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigTableVo> list(ConfigTableBo bo, PageQuery pageQuery) {
        return configTableService.selectPageList(bo, pageQuery);
    }
    
    /**
     * 根据ID获取数据库相关表
     * @param id 数据ID
     * @return
     */
    @SaCheckPermission("configTable:query")
    @GetMapping(value = "/{id}")
    public R<ConfigTableVo> getInfo(@PathVariable Long id) {
        return R.ok(configTableService.selectById(id));
    }

    /**
     * 新增数据库相关表
     * @return
     */
    @SaCheckPermission("configTable:add")
    @Log(title = "数据库相关表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigTableBo bo) {
        return toAjax(configTableService.insert(bo));
    }

    /**
     * 修改数据库相关表
     * @return
     */
    @SaCheckPermission("configTable:edit")
    @Log(title = "数据库相关表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigTableBo bo) {
        return toAjax(configTableService.update(bo));
    }

    /**
     * 删除数据库相关表
     * @param ids 数据ID字符串集合
     * @return
     */
    @SaCheckPermission("configTable:remove")
    @Log(title = "数据库相关表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configTableService.deleteByIds(ids));
    }
}

