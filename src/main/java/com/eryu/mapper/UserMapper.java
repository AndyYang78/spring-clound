package com.eryu.mapper;

import com.eryu.core.entity.dto.user.*;
import com.eryu.core.entity.dto.params.UserParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关SQL操作映射
 * <p>
 * Created by troubleMan 2017/07/01
 */
@Mapper
public interface UserMapper {


    /**
     * 查询用户收入
     *
     * @param params 传入参数
     * @return UserDTO
     */
     List<UserDTO> queryUserMessage(UserParams params);

    /**
     * 查询总的用户数
     *@param params 传入参数
     * @return int
     */
     int queryUserCount(UserParams params);

    /**
     * 查询用户的兴趣爱好
     *@param userId 传入参数
     * @return int
     */
    List<String> queryUserHobby(@Param("userId") String userId);

    /**
     * 查询用户的验证码列表
     *
     * @param mobile 账户状态
     * @return int
     */
    List<UserMobileCodeDTO> queryCodeMobile(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("mobile") String mobile);

    /**
     * 查询用户的验证码数量
     *
     * @param mobile 账户状态
     * @return int
     */
    int queryCodeMobileCount(@Param("mobile") String mobile);

    /**
     * 查询用户的反馈的信息
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return int
     */
    List<UserReportDTO> queryUserReport(UserParams params);
    /**
     * 查询用户的反馈的信息
     *
     * @param id id
     *
     * @return int
     */
    List<String> queryUserReportUrl(@Param("id") String id);

    /**
     * 查询用户的反馈的信息 计数
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return int
     */
    int queryUserReportCount(UserParams params);

    /**
     * 查询用户的账户的明细信息
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return int
     */
    List<UserAccountDetailDTO> queryUserAccountList(UserParams params);

    /**
     * 查询用户的账户的明细信息 --计数
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return int
     */
    int queryUserAccountCount(UserParams params);

    /**
     * 查询用户要审核的头像列表数据
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return UserAuditImageDTO
     */
    List<UserAuditImageDTO> queryUserAuditImageList(UserParams params);

    /**
     * 查询用户要审核的头像列表数据 --计数
     *
     * @param params 用户id，姓名，昵称，处理状态，时间
     * @return int
     */
    int queryUserAuditImageCount(UserParams params);

}
