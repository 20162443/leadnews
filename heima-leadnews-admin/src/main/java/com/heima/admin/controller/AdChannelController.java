package com.heima.admin.controller;

import com.heima.admin.service.AdChannelService;
import com.heima.apis.admin.AdChannelControllerApi;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {
    @Autowired
    private AdChannelService adChannelService;
    @PostMapping("/list")
    @Override
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto channelDto) {
        return adChannelService.findByNameAndPage(channelDto);
    }

    @Override
    @PostMapping("/save")
    public ResponseResult save(AdChannel adChannel) {
        return adChannelService.insert(adChannel);
    }

    @Override
    public ResponseResult update(AdChannel adChannel) {
        return adChannelService.update(adChannel);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        return adChannelService.deleteById(id);
    }
}
