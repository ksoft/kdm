package com.zcckj.storeshow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zcckj.storeshow.common.BaseDto;
import com.zcckj.storeshow.enums.DzChannelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangjianbo
 * @date 2017/4/20
 */
@ApiModel(value = "点赞/取消点赞DTO",description="点赞/取消点赞DTO")
public class ShowDzLogSaveDto extends BaseDto {
    @ApiModelProperty(value = "标志，点赞:1，取消点赞：-1",required = true)
    private Integer flag;
    @JsonIgnore
    @ApiModelProperty(value = "点赞人，APP端存storeId,微信端存openId,用户端通过记录Cookie标识，Cookie无法记录的，只加不减",required = true)
    private String createBy ;
    @JsonIgnore
    @ApiModelProperty(value = "点赞人名称")
    private String createByName;
    @JsonIgnore
    @ApiModelProperty(value = "门店头像URL")
    private String storeIconUrl;
    @JsonIgnore
    @ApiModelProperty(value = "点赞来源 APP:APP，WX:微信，QT:其它，来源非APP的作为消费者点赞数")
    private DzChannelEnum dzChannel;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public DzChannelEnum getDzChannel() {
        return dzChannel;
    }

    public void setDzChannel(DzChannelEnum dzChannel) {
        this.dzChannel = dzChannel;
    }

    public String getStoreIconUrl() {
        return storeIconUrl;
    }

    public void setStoreIconUrl(String storeIconUrl) {
        this.storeIconUrl = storeIconUrl;
    }
}
