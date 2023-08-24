package com.xian.domain.bo;

import com.xian.domain.ConfigColumn;

/**
 * 数据库相关表字段 参数对象
 * 
 * @author liguoxian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigColumn.class, reverseConvertGenerate = false)
public class ConfigColumnBo extends BaseEntity {
            
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
     * 表唯一键值
     */     
    private String tableKey;
                
    /**
     * 数据列名
     */     
    private String columnName;
                
    /**
     * 数据列描述
     */     
    private String columnComment;
                
    /**
     * 唯一键值
     */     
    private String columnKey;
                
    /**
     * 数据类型
     */     
    private String dataType;
                
    /**
     * 是否允许为空
     */     
    private String isNullable;
                                
}

