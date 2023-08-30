package com.xian.domain.vo;

import com.xian.domain.ConfigTableRelevance;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表关联信息 视图对象
 * 
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigTableRelevance.class)
public class ConfigTableRelevanceVo implements Serializable {
    
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
     * 数据表
     */      
    @ExcelProperty(value = "数据表")
    private String sourceTable;
            
    /**
     * 数据列
     */      
    @ExcelProperty(value = "数据列")
    private String sourceColumn;
            
    /**
     * 关联表
     */      
    @ExcelProperty(value = "关联表")
    private String linkTable;
            
    /**
     * 关联列
     */      
    @ExcelProperty(value = "关联列")
    private String linkColumn;
            
    /**
     * 数据表与关联表对应关系
     */      
    @ExcelProperty(value = "数据表与关联表对应关系")
    private String linkType;
                                
}

