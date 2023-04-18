package com.heima.apis.wemedia;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmUser;

public interface WmUserControllerApi {
  public ResponseResult save(WmUser wmUser);
  public WmUser findByName(String name);
}
