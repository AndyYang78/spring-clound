package com.eryu.controller.manage;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.params.UserParams;
import com.eryu.core.entity.dto.user.*;
import com.eryu.core.service.user.UserService;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.AuditImageListRequest;
import com.eryu.user.dto.request.AuditImageRequest;
import com.eryu.user.dto.request.FeedBackRequest;
import com.eryu.user.dto.request.ModifyUserRequest;
import com.eryu.user.feignClients.UserClient;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cn.jpush.api.push.model.PushModel.gson;


/**
 * 查询用户相关API
 * <p>
 * Created by troubleMan on 2017/06/27
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private UserClient userClient;

    /**
     * 查询用户纤细信息
     *
     * @param offset    偏移量
     * @param limit     偏移量
     * @param dateStart 开始
     * @param dateEnd   结束
     * @param erNo      耳语账号
     * @param mobile    手机
     * @param nickname  昵称
     * @param gender    性别
     * @param vip       vip
     * @param device    设备
     * @param is_auth   是否验证
     * @param status    状态
     * @param isNet     是否是网红
     * @return UserDTO
     */
    @GetMapping(value = "/query/user/list")
    public PagingResultWrapper<UserDTO> queryUserList(
            @RequestParam(value = "offset", required = true) Integer offset,
            @RequestParam(value = "limit", required = true) Integer limit,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "erNo", required = false) String erNo,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "gender", required = false) Integer gender,
            @RequestParam(value = "vip", required = false) String vip,
            @RequestParam(value = "device", required = false) String device,
            @RequestParam(value = "is_auth", required = false) String is_auth,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "isNetease", required = false) Integer isNetease,
            @RequestParam(value = "isNet", required = false) Integer isNet) {
        UserParams userParams = new UserParams();
        userParams.setOffset(offset);
        userParams.setLimit(limit);
        userParams.setIsNet(isNet);
        userParams.setIsNetease(isNetease);
        if (StringUtils.hasText(dateStart) && StringUtils.hasText(dateEnd)) {
            userParams.setDateStart(String.format("%s %s", dateStart, "00:00:00"));
            userParams.setDateEnd(String.format("%s %s", dateEnd, "23:59:59"));
        }
        if (StringUtils.hasText(erNo)) {
            userParams.setErNo(erNo);
        }
        if (StringUtils.hasText(vip)) {
            userParams.setVip(vip);
        }
        if (StringUtils.hasText(mobile)) {
            userParams.setMobile(mobile);
        }
        if (StringUtils.hasText(nickname)) {
            userParams.setNickname(nickname);
        }
        if (StringUtils.hasText(device)) {
            userParams.setDevice(device);
        }
        if (StringUtils.hasText(is_auth)) {
            userParams.setIs_auth(is_auth);
        }
        if (gender != null && gender > -1) {
            userParams.setGender(gender);
        } else if (status != null && status > -1) {
            userParams.setStatus(status);
        }

        PagingResultWrapper<UserDTO> wrapper = userService.queryUserMessage(userParams);
        return wrapper;
    }

    /**
     * 冻结或者解冻用户和账户
     *
     * @param dto 用户状态 账户状态 网红状态 用户ID
     * @return SimpleResponse
     */
    @PostMapping(value = "/status/update")
    public SimpleResponse queryUserAccount(ModifyUserRequest dto) {
        userService.updateUserStatus(dto.getUserId(), dto, dto.getAccountState());
        return SimpleResponse.builder().build();
    }

    /**
     * 查询用户的验证码
     *
     * @param offset
     * @param limit
     * @param mobile
     * @return UserMobileCodeDTO
     * @author troubleMan
     */

    @ApiOperation(value = "查询用户的验证码", notes = "查询用户的验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", required = false, value = "手机号码", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/code/list", method = RequestMethod.GET)
    public PagingResultWrapper<UserMobileCodeDTO> queryUserCodeLIst(
            @RequestParam(value = "offset", required = true) Integer offset,
            @RequestParam(value = "limit", required = true) Integer limit,
            @RequestParam(value = "mobile", required = true) String mobile) {
        return userService.getUserMobileCode(offset, limit, mobile);
    }

    /**
     * 查询用户的反馈的信息
     *
     * @return
     * @author troubleMan
     */
    @GetMapping(value = "/report")
    public PagingResultWrapper<UserReportDTO> queryUserReport(UserParams userParams) {

        if (StringUtils.hasText(userParams.getDateStart()) && StringUtils.hasText(userParams.getDateEnd())) {
            userParams.setDateStart(String.format("%s %s", userParams.getDateStart(), "00:00:00"));
            userParams.setDateEnd(String.format("%s %s", userParams.getDateEnd(), "23:59:59"));
        }
        return userService.queryUserReport(userParams);

    }

    /**
     * 回复用户的反馈的信息
     *
     * @param feedBackRequest 用户ID 反馈id等
     * @return 是否成功
     */
    @PostMapping(value = "/reply/report")
    public SimpleResponse replyUserReport(FeedBackRequest feedBackRequest) {
        return userClient.replyMessage(feedBackRequest);
    }

    /**
     * 查询用户的详细的账户信息
     *
     * @return UserAccountDetailDTO
     * @author troubleMan
     */
    @GetMapping(value = "/account/detail")
    public PagingResultWrapper<UserAccountDetailDTO> queryUserAccountDetail(UserParams userParams) {

        if (StringUtils.hasText(userParams.getDateStart()) && StringUtils.hasText(userParams.getDateEnd())) {
            userParams.setDateStart(String.format("%s %s", userParams.getDateStart(), "00:00:00"));
            userParams.setDateEnd(String.format("%s %s", userParams.getDateEnd(), "23:59:59"));
        }
        return userService.queryUserAccoutList(userParams);
    }

    /**
     * 查询需要审核的用户的头像和 主页图片信息
     *
     * @return UserAccountDetailDTO
     * @author troubleMan
     */
    @GetMapping(value = "/audit/user/image")
    public PagingResultWrapper<UserAuditImageDTO> queryAuditImage(UserParams userParams) {
        if (StringUtils.hasText(userParams.getDateStart()) && StringUtils.hasText(userParams.getDateEnd())) {
            userParams.setDateStart(String.format("%s %s", userParams.getDateStart(), "00:00:00"));
            userParams.setDateEnd(String.format("%s %s", userParams.getDateEnd(), "23:59:59"));
        }
        return userService.queryUserAuditImageList(userParams);
    }

    /**
     * 批量审核用户的 头像 主页图片
     *
     * @param userId    用户的id 审核的类型 type 拼接审核类型（id_&_type）
     * @param auditType 审核内容：1 审核通过 2审核不通过
     * @return 是否成功
     */
    @PostMapping(value = "/batch/audit/image")
    public SimpleResponse batchAuditImages(String userId, Integer auditType) {
        List<AuditImageRequest> userIds = gson.fromJson(userId,
                new TypeToken<List<AuditImageRequest>>() {
                }.getType());
        AuditImageListRequest userIdMessage = new AuditImageListRequest();
        userIdMessage.setUserIds(userIds);
        return userClient.batchAuditImages(userIdMessage, auditType);
    }

    /**
     * 批量审核用户的 头像 主页图片
     *
     * @param userId    用户的id 审核的类型 type 拼接审核类型（id_&_type）
     * @return 是否成功
     */
    @PostMapping(value = "/register")
    public SimpleResponse registerUser(@RequestParam("userId") String userId) {
        return userClient.registerUser(userId);
    }

}
