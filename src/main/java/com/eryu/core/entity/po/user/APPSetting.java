package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * APP的兑换配置
 * <p>
 * Created by troubleMan on 2017/7/18.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_APP_SETTING")
public class APPSetting extends AbstractEntity {

    /**
     * 名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '配置名'")
    private String name;

    /**
     * 配置键
     */
    @Column(name = "CONFIG_KEY", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '配置键'")
    private String configKey;

    /**
     * 配置值
     */
    @Column(name = "CONFIG_VALUE", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '配置值'")
    private String configValue;

    /**
     * 水晶兑换现金比例
     */
    @Column(name = "CRYSTAL_CASH_RATE", columnDefinition = "decimal(8,4) NOT NULL DEFAULT 0.0700 COMMENT '水晶兑换现金比例'")
    private Integer crystalCashRate;
    /**
     * 是否打开IAP
     */
    @Column(name = "IAP", columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否打开IAP'")
    private Integer iap;
}
