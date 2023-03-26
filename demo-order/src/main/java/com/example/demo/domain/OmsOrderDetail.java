package com.example.demo.domain;

import com.example.demo.entity.OmsOrder;
import com.example.demo.entity.PmsProduct;
import com.example.demo.entity.UmsMember;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YuanJW on 2023/2/15.
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    @ApiModelProperty("用户信息")
    private UmsMember umsMember;

    @Getter
    @Setter
    @ApiModelProperty("商品信息")
    private PmsProduct pmsPorduct;
}
