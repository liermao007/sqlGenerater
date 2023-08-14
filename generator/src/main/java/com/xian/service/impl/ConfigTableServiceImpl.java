package com.xian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xian.domain.ConfigTable;
import com.xian.domain.bo.ConfigTableBo;
import com.xian.domain.vo.ConfigTableVo;
import com.xian.mapper.ConfigTableMapper;
import com.xian.service.IConfigTableService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库相关表 服务实现
 * 
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigTableServiceImpl extends BaseServiceImpl<ConfigTableMapper, ConfigTable, ConfigTableVo, ConfigTableBo> implements IConfigTableService {

    @Override
    public TableDataInfo<ConfigTableVo> selectPageList(ConfigTableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigTable> lqw = buildQueryWrapper(bo);
        Page<ConfigTableVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigTableVo> selectList(ConfigTableBo bo) {
        LambdaQueryWrapper<ConfigTable> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigTable> buildQueryWrapper(ConfigTableBo bo) {
        return new LambdaQueryWrapperX<ConfigTable>()
        .eqIfPresent(ConfigTable::getTableSchema, bo.getTableSchema())
        .eqIfPresent(ConfigTable::getTableName, bo.getTableName())
        .eqIfPresent(ConfigTable::getTableType, bo.getTableType())
        .eqIfPresent(ConfigTable::getTableComment, bo.getTableComment())
        .eqIfPresent(ConfigTable::getTableKey, bo.getTableKey())
        .eqIfPresent(ConfigTable::getCreateDept, bo.getCreateDept())
;
    }
}
