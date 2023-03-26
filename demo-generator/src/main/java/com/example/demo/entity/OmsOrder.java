package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

/**
* 订单表 : oms_order
* Created by YuanJW on 2023-02-15 10:38:43
*/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value ="oms_order")
@ApiModel(value = "OmsOrder", description = "订单表")
public class OmsOrder extends BaseEntity {
    @NotNull(message = "[订单id]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "订单id")
    private Long id;
    
    @NotNull(message = "[会员id]不能为空")
    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @NotNull(message = "[商品id]不能为空")
    @ApiModelProperty(value = "商品id")
    private Long productId;

    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    
    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty(value = "用户帐号")
    private String memberName;
    
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;
    
    @ApiModelProperty(value = "应付金额（实际支付金额）")
    private BigDecimal payAmount;
    
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;
    
    @ApiModelProperty(value = "积分抵扣金额")
    private BigDecimal integrationAmount;
    
    @ApiModelProperty(value = "优惠券抵扣金额")
    private BigDecimal couponAmount;

    @Size(max = 255, message = "编码长度不能超过255")
    @Length(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty(value = "")
    private String payType;
    
    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
    
    @NotBlank(message = "[收货人姓名]不能为空")
    @Size(max = 100, message = "编码长度不能超过100")
    @Length(max = 100, message = "编码长度不能超过100")
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    
    @NotBlank(message = "[收货人电话]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @Length(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;

    @Size(max = 200, message = "编码长度不能超过200")
    @Length(max = 200, message = "编码长度不能超过200")
    @ApiModelProperty(value = "详细地址")
    private String receiverDetailAddress;
    
    @ApiModelProperty(value = "确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评价时间")
    private Date commentTime;
}
