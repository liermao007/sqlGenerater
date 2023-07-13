package com.xian.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 数据库相关表字段
 * 
 * @author liguoxian
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("config_column")
public class ConfigColumn extends TenantEntity {
            
    /**
     * 主键
     */
    @TableId
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
                                        
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    
}

