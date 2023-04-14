package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;

public interface AdChannelService extends IService<AdChannel> {
    public ResponseResult findByNameAndPage(ChannelDto dto);
    public ResponseResult insert(AdChannel adChannel);
    public ResponseResult update(AdChannel adChannel);
    public ResponseResult deleteById(Integer id);
}
