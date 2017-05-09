package com.ksoft.kdm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksoft.kdm.common.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangjianbo
 * @date 2017/4/20
 */
@ApiModel(value = "点赞消息查询DTO",description="点赞消息查询DTO")
public class ShowDzLogSearchDto extends BasePageDto {

    @JsonIgnore
    @ApiModelProperty(value = "门店id",required = true)
    private Long storeId;

    @ApiModelProperty(value = "上一页最后一笔点赞数据dzLogId,(第一页传-1，如果第一页没有未读数据，第二页传0，否则传上一页最后一笔点赞数据dzLogId)",required = true)
    private Long lastDzLogId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getLastDzLogId() {
        return lastDzLogId;
    }

    public void setLastDzLogId(Long lastDzLogId) {
        this.lastDzLogId = lastDzLogId;
    }
}
