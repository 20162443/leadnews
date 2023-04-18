package com.heima.user.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("leadnews-article")
public interface ArticleFeign {

    @GetMapping("/api/v1/author/findByUserId/{id}")
    public ApAuthor findByUserId(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/author/save")
    public ResponseResult save(@RequestBody ApAuthor apAuthor);
}
