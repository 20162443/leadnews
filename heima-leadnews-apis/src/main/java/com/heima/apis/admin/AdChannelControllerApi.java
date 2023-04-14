package com.heima.apis.admin;

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "频道管理",tags = "channel",description = "频道管理")
public interface AdChannelControllerApi {
    @ApiOperation("频道分页查询")
    public ResponseResult findByNameAndPage(ChannelDto channelDto);
    public ResponseResult save(AdChannel adChannel);
}
