package com.xian.domain.bo;

import com.xian.domain.ConfigTable;

/**
 * 数据库相关表 参数对象
 * 
 * @author liguoxian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigTable.class, reverseConvertGenerate = false)
public class ConfigTableBo extends BaseEntity {
        
/**
     * 主键
     */     
    private Long id;
        
/**
     * 所属数据库
     */     
    private String tableSchema;
        
/**
     * 表名
     */     
    private String tableName;
        
/**
     * 表类型
     */     
    private String tableType;
        
/**
     * 表描述
     */     
    private String tableComment;
        
/**
     * 唯一键值
     */     
    private String tableKey;

}

