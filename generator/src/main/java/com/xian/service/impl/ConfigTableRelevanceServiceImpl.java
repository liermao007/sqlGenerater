package com.xian.service.impl;

import com.xian.domain.ConfigTableRelevance;
import com.xian.domain.bo.ConfigTableRelevanceBo;
import com.xian.domain.vo.ConfigTableRelevanceVo;
import com.xian.mapper.ConfigTableRelevanceMapper;
import com.xian.service.IConfigTableRelevanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库相关表关联信息 服务实现
 * 
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigTableRelevanceServiceImpl extends BaseServiceImpl<ConfigTableRelevanceMapper, ConfigTableRelevance, ConfigTableRelevanceVo, ConfigTableRelevanceBo> implements IConfigTableRelevanceService {

    @Override
    public TableDataInfo<ConfigTableRelevanceVo> selectPageList(ConfigTableRelevanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigTableRelevance> lqw = buildQueryWrapper(bo);
        Page<ConfigTableRelevanceVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigTableRelevanceVo> selectList(ConfigTableRelevanceBo bo) {
        LambdaQueryWrapper<ConfigTableRelevance> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigTableRelevance> buildQueryWrapper(ConfigTableRelevanceBo bo) {
        return new LambdaQueryWrapperX<ConfigTableRelevance>()
                .eqIfPresent(ConfigTableRelevance::getTableSchema, bo.getTableSchema())
                .eqIfPresent(ConfigTableRelevance::getSourceTable, bo.getSourceTable())
                .eqIfPresent(ConfigTableRelevance::getSourceColumn, bo.getSourceColumn())
                .eqIfPresent(ConfigTableRelevance::getLinkTable, bo.getLinkTable())
                .eqIfPresent(ConfigTableRelevance::getLinkColumn, bo.getLinkColumn())
                .eqIfPresent(ConfigTableRelevance::getLinkType, bo.getLinkType())
                .eqIfPresent(ConfigTableRelevance::getCreateDept, bo.getCreateDept())
        ;
    }
}
