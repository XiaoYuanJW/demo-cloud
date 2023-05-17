package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AccountFreezeTbl
 *
 * @author YuanJW
 * @date 2023/04/14
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "account_freeze_tbl")
@ApiModel(value = "AccountFreezeTbl", description = "用户Ttc金额冻结表")
public class AccountFreezeTbl {
    @NotNull(message = "[事务id]不能为空")
    @ApiModelProperty(value = "事务id")
    private String xid;
    @NotNull(message = "[会员id]不能为空")
    @ApiModelProperty(value = "会员id")
    private Long memberId;
    @NotNull(message = "[冻结金额]不能为空")
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeAmount;
    @NotNull(message = "[状态（0->try 1->confirm 2->cancel）]不能为空")
    @ApiModelProperty(value = "状态（0->try 1->confirm 2->cancel）")
    private Integer state;

    /**
     * TCC事务状态
     */
    public static abstract class State {
        public final static Integer TRY = 0;

        public final static Integer CONFIRM = 1;

        public final static Integer CANCEL = 2;
    }
}
