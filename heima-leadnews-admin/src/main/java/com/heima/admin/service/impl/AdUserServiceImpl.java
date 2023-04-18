package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdUserMapper;
import com.heima.admin.service.AdUserService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {
    @Override
    public ResponseResult login(AdUserDto adUserDto) {
        if(StringUtils.isEmpty(adUserDto.getName())||StringUtils.isEmpty(adUserDto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"用户名和密码不能为空");
        }
        Wrapper wrapper=new QueryWrapper<AdUser>();
        ((QueryWrapper)wrapper).eq("name",adUserDto.getName());
        List<AdUser> list=list(wrapper);
        if(list!=null&&list.size()==1){
            AdUser adUser=list.get(0);
            String pswd= DigestUtils.md5DigestAsHex((adUserDto.getPassword()+adUser.getSalt()).getBytes());
            if(adUser.getPassword().equals(pswd)){
                Map<String,Object> map=new HashMap<>();
                adUser.setPassword("");
                adUser.setSalt("");
                String token=AppJwtUtil.getToken(adUser.getId().longValue());
                map.put("token", token);
                map.put("user",adUser);
                return ResponseResult.okResult(map);
            }else{
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"密码错误");
            }
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST,"用户名不存在");
        }
    }
}
