package com.eryu.core.entity.dto.params;

import lombok.Getter;
import lombok.Setter;

/**
 * 家族的相关的进入的参数
 * Created by troubleMan on 2017/7/27.
 */
@Getter
@Setter
public class FamilyParam {
    private Integer limit;

    private Integer offset;

    /**
     * 家族名称
     */
    private String familyName;
    /**
     * 族长Id
     */
    private String patriarchId;
    /**
     * 族长昵称
     */
    private String patriarchNick;
    /**
     * 家族状态
     */
    private Integer familyStatus;
    /**
     * 审核状态
     */
    private Integer examineStatus;
    /**
     * 时间
     */
    private String date;
    /**
     * 家族id
     */
    private String familyId;
    /**
     * 族长手机号
     */
    private String mobile;
    /**
     * 家族人数上限
     */
    private Integer maxPerson;
    /**
     * 家族收益比例
     */
    private double incomeRate;
    /**
     * 家族介绍
     */
    private String introduceFamily;
    /**
     * 家族头像
     */
    private String familyPortrait;
    /**
     * 家族成员的角色
     */
    private String familyRole;

}
