package com.heima.admin.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdChannelMapper;
import com.heima.admin.service.AdChannelService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        if(dto==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        Page page=new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<AdChannel> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getName())){
           lambdaQueryWrapper.eq(AdChannel::getName,dto.getName());
        }
        IPage result = page(page, lambdaQueryWrapper);
        ResponseResult responseResult=new PageResponseResult(dto.getPage(),dto.getSize(),(int)result.getTotal());
        responseResult.setData(result.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult insert(AdChannel adChannel) {
        if(adChannel==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        adChannel.setCreatedTime(new Date());
        save(adChannel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
