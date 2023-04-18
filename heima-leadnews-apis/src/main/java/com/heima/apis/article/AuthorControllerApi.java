package com.heima.apis.article;

import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorControllerApi {

    public ApAuthor findByUserId(@PathVariable("id") Integer id);
    public ResponseResult save(@RequestBody ApAuthor apAuthor);
}
