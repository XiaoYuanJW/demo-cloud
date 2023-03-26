package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
import java.math.BigDecimal;

/**
* 商品表 : pms_product
* Created by YuanJW on 2023-02-15 10:15:15
*/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value ="pms_product")
@ApiModel(value = "PmsProduct", description = "商品表")
public class PmsProduct extends BaseEntity {
    @NotNull(message = "[商品id]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品id")
    private Long id;
    
    @NotBlank(message = "[商品名称]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty(value = "商品名称")
    private String name;

    @Size(max = 255, message = "编码长度不能超过255")
    @Length(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty(value = "商品图片地址")
    private String pic;
    
    @NotBlank(message = "[商品货号]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty(value = "商品货号")
    private String sn;
    
    @NotNull(message = "[商品品牌分类id]不能为空")
    @ApiModelProperty(value = "商品品牌分类id")
    private Long brandId;
    
    @NotNull(message = "[商品种类]不能为空")
    @ApiModelProperty(value = "商品种类")
    private Long categoryId;
    
    @NotNull(message = "[商品运费模板id]不能为空")
    @ApiModelProperty(value = "商品运费模板id")
    private Long feightTemplateId;
    
    @NotNull(message = "[商品销量]不能为空")
    @ApiModelProperty(value = "商品销量")
    private Integer sale;
    
    @NotNull(message = "[商品价格]不能为空")
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;
    
    @ApiModelProperty(value = "商品促销价格")
    private BigDecimal promotionPrice;

    @Size(max = -1, message = "编码长度不能超过-1")
    @Length(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty(value = "商品描述")
    private String description;
    
    @ApiModelProperty(value = "商品库存")
    private Integer stock;

    @Size(max = 16, message = "编码长度不能超过16")
    @Length(max = 16, message = "编码长度不能超过16")
    @ApiModelProperty(value = "商品单位")
    private String unit;
    
    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;
}
