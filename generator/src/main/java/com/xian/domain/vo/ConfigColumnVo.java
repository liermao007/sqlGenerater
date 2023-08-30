package com.xian.domain.vo;

import com.xian.domain.ConfigColumn;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表字段 视图对象
 * 
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigColumn.class)
public class ConfigColumnVo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
        
    /**
     * 主键
     */      
    @ExcelProperty(value = "主键")
    private Long id;
            
    /**
     * 所属数据库
     */      
    @ExcelProperty(value = "所属数据库")
    private String tableSchema;
            
    /**
     * 表名
     */      
    @ExcelProperty(value = "表名")
    private String tableName;
            
    /**
     * 表唯一键值
     */      
    @ExcelProperty(value = "表唯一键值")
    private String tableKey;
            
    /**
     * 数据列名
     */      
    @ExcelProperty(value = "数据列名")
    private String columnName;
            
    /**
     * 数据列描述
     */      
    @ExcelProperty(value = "数据列描述")
    private String columnComment;
            
    /**
     * 唯一键值
     */      
    @ExcelProperty(value = "唯一键值")
    private String columnKey;
            
    /**
     * 数据类型
     */      
    @ExcelProperty(value = "数据类型")
    private String dataType;
            
    /**
     * 是否允许为空
     */      
    @ExcelProperty(value = "是否允许为空")
    private String isNullable;
                                
}

