package com.xian.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import com.xian.domain.bo.ConfigTableRelevanceBo;
import com.xian.domain.vo.ConfigTableRelevanceVo;
import com.xian.service.IConfigTableRelevanceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库相关表关联信息
 * 
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config/configTableRelevance")
public class ConfigTableRelevanceController extends BaseController {

    private final IConfigTableRelevanceService configTableRelevanceService;
    
    /**
     * 分页查询数据库相关表关联信息
     * @return
     */
    @SaCheckPermission("configTableRelevance:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigTableRelevanceVo> list(ConfigTableRelevanceBo bo, PageQuery pageQuery) {
        return configTableRelevanceService.selectPageList(bo, pageQuery);
    }
    
    /**
     * 根据ID获取数据库相关表关联信息
     * @param id 数据ID
     * @return
     */
    @SaCheckPermission("configTableRelevance:query")
    @GetMapping(value = "/{id}")
    public R<ConfigTableRelevanceVo> getInfo(@PathVariable Long id) {
        return R.ok(configTableRelevanceService.selectById(id));
    }

    /**
     * 新增数据库相关表关联信息
     * @return
     */
    @SaCheckPermission("configTableRelevance:add")
    @Log(title = "数据库相关表关联信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigTableRelevanceBo bo) {
        return toAjax(configTableRelevanceService.insert(bo));
    }

    /**
     * 修改数据库相关表关联信息
     * @return
     */
    @SaCheckPermission("configTableRelevance:edit")
    @Log(title = "数据库相关表关联信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigTableRelevanceBo bo) {
        return toAjax(configTableRelevanceService.update(bo));
    }

    /**
     * 删除数据库相关表关联信息
     * @param ids 数据ID字符串集合
     * @return
     */
    @SaCheckPermission("configTableRelevance:remove")
    @Log(title = "数据库相关表关联信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configTableRelevanceService.deleteByIds(ids));
    }
}

