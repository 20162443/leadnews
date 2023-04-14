package com.heima.model.admin.pojos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("ad_user")
@Data
public class AdUser implements Serializable {
    private static final long serialversionUID=1L;
    private Integer id;

    private String name;
    private String password;
    private String salt;
    private String image;
    private String phone;
    private Integer status;
    private String email;
    private Date loginTime;
    private Date createdTime;

}
