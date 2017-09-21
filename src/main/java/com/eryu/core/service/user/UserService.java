package com.eryu.core.service.user;
import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.user.*;
import com.eryu.core.entity.dto.params.UserParams;
import com.eryu.user.dto.request.ModifyUserRequest;


/**
 * 用户相关的信息
 * Created by troubleMan on 2017/07/25.
 */
public interface UserService {

    /**
     * 查询用户收入信息
     *
     * @param params 用户的状态 账户的状态等
     * @return UserDTO
     */
    PagingResultWrapper<UserDTO> queryUserMessage(UserParams params);
    /**
     * 查询用户收入信息
     *
     * @param dto 用户的状态  网红
     * @param state 用户账户的状态
     * @param id 耳语号
     */
    void updateUserStatus(String id,ModifyUserRequest dto,Integer state);

    /**
     * 查询用户手机号码验证码
     *
     * @param mobile 用户的手机号码
     * @return UserMobileCodeDTO
     */
    PagingResultWrapper<UserMobileCodeDTO>  getUserMobileCode(Integer offset,Integer limit,String mobile);

    /**
     * 查询用户反馈的信息
     *
     * @param params 时间，状态等
     * @return UserReportDTO
     */
    PagingResultWrapper<UserReportDTO> queryUserReport(UserParams params);


    /**
     * 查询用户用户的账户明细数据
     *
     * @param params 时间，状态等
     * @return UserAccountDetailDTO
     */
    PagingResultWrapper<UserAccountDetailDTO> queryUserAccoutList(UserParams params);

    /**
     * 查询用户要审核的头像列表数据
     *
     * @param params 时间，状态等
     * @return UserAuditImageDTO
     */
    PagingResultWrapper<UserAuditImageDTO> queryUserAuditImageList(UserParams params);


}
