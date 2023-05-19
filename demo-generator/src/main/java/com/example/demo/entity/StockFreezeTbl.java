package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 商品库存冻结 : stock_freeze_tbl
 * Created by YuanJW on 2023-05-18 14:25:36
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "stock_freeze_tbl")
@ApiModel(value = "StockFreezeTbl", description = "商品库存冻结")
public class StockFreezeTbl implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "xid")
    @NotBlank(message = "[事务id]不能为空")
    @Size(max = 120, message = "编码长度不能超过120")
    @Length(max = 120, message = "编码长度不能超过120")
    @ApiModelProperty(value = "事务id")
    private String xid;
    @NotNull(message = "[商品id]不能为空")
    @ApiModelProperty(value = "商品id")
    private Long productId;
    @NotNull(message = "[冻结库存]不能为空")
    @ApiModelProperty(value = "冻结库存")
    private Integer freezeStock;
    @ApiModelProperty(value = "状态")
    private Integer state;

    /**
     * 事务状态
     */
    public static abstract class state {
        public final static Integer TRY = 0;

        public final static Integer CONFIRM = 1;

        public final static Integer CANCEL = 2;
    }
}
