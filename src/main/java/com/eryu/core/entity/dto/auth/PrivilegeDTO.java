package com.eryu.core.entity.dto.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限
 * Created by marco on 2017/2/22.
 */
@Getter
@Setter
public class PrivilegeDTO {

    private String name;

    private String category;

    private String shape;

    private String point;
}
