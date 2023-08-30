package com.xian.domain.vo;

import com.xian.domain.ConfigTable;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表 视图对象
 * 
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigTable.class)
public class ConfigTableVo implements Serializable {
    
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
     * 表类型
     */      
    @ExcelProperty(value = "表类型")
    private String tableType;
    
/**
     * 表描述
     */      
    @ExcelProperty(value = "表描述")
    private String tableComment;
    
/**
     * 唯一键值
     */      
    @ExcelProperty(value = "唯一键值")
    private String tableKey;

}

