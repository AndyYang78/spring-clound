package com.eryu.core.service.user.impl;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.params.UserParams;
import com.eryu.core.entity.dto.user.*;
import com.eryu.core.service.user.UserService;
import com.eryu.mapper.UserMapper;
import com.eryu.trade.feignClients.TradeClient;
import com.eryu.user.dto.request.ModifyUserRequest;
import com.eryu.user.feignClients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by troubleMan on 2017/06/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserClient userClient;

    @Resource
    private TradeClient tradeClient;



    /**
     * 查询用户详细信息
     *
     * @param params
     * @return
     */
    @Override
    public PagingResultWrapper<UserDTO> queryUserMessage(UserParams params) {
        getVipParams(params);
        //用户详细信息
        List<UserDTO> users = userMapper.queryUserMessage(params);
        Integer count = userMapper.queryUserCount(params);
        for (UserDTO userDTO : users) {
            userDTO.setMobile(turnMobile(userDTO.getMobile()));
            List<String> getHobby = userMapper.queryUserHobby(userDTO.getId());
            //获取缓存中的爱好数据
                if (getHobby!=null&&getHobby.size()>0) {
                for(String name:getHobby){
                    userDTO.setHobby(userDTO.getHobby()+" "+name);
                }
            }
        }
        PagingResultWrapper<UserDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / params.getLimit());
        wrapper.setPageSize(params.getLimit());
        wrapper.setData((users == null || users.size() > 0 && users.get(0) == null) ? new ArrayList<>() : users);
        return wrapper;
    }

    @Override
    public void updateUserStatus(String id, ModifyUserRequest dto, Integer state) {
        //更新用户的状态
        userClient.updateUserStatus(id, dto);
        //更新用户账户的状态
        tradeClient.updateUserStatus(id, state);
    }

    @Override
    public PagingResultWrapper<UserMobileCodeDTO> getUserMobileCode(Integer offset, Integer limit, String mobile) {
        List<UserMobileCodeDTO> userMobileCodeDTOS = userMapper.queryCodeMobile(offset, limit, mobile);
        PagingResultWrapper<UserMobileCodeDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(userMapper.queryCodeMobileCount(mobile));
        wrapper.setTotalPage(userMobileCodeDTOS.size() / limit);
        wrapper.setPageSize(limit);
        wrapper.setData((userMobileCodeDTOS == null || userMobileCodeDTOS.size() == 0) ? new ArrayList<>() : userMobileCodeDTOS);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<UserReportDTO> queryUserReport(UserParams params) {
        List<UserReportDTO> userMobileCodeDTOS = userMapper.queryUserReport(params);
        for (UserReportDTO userReportDTO : userMobileCodeDTOS) {
            List<String> userPicture = userMapper.queryUserReportUrl(userReportDTO.getUdid());
            if (userPicture != null && !userPicture.isEmpty()) {
                userReportDTO.setPicture(userPicture);
            }
        }
        int count = userMapper.queryUserReportCount(params);
        PagingResultWrapper<UserReportDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / params.getLimit());
        wrapper.setPageSize(params.getLimit());
        wrapper.setData((userMobileCodeDTOS == null || userMobileCodeDTOS.size() > 0 && userMobileCodeDTOS.get(0) == null) ? new ArrayList<>() : userMobileCodeDTOS);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<UserAccountDetailDTO> queryUserAccoutList(UserParams params) {
        List<UserAccountDetailDTO> userAccountDetailDTOList = userMapper.queryUserAccountList(params);
        PagingResultWrapper<UserAccountDetailDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(userMapper.queryUserAccountCount(params));
        wrapper.setTotalPage(userAccountDetailDTOList.size() / params.getLimit());
        wrapper.setPageSize(params.getLimit());
        wrapper.setData((userAccountDetailDTOList == null || userAccountDetailDTOList.size() == 0) ? new ArrayList<>() : userAccountDetailDTOList);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<UserAuditImageDTO> queryUserAuditImageList(UserParams params) {
        getVipParams(params);
        List<UserAuditImageDTO> userAuditImageDTOList = userMapper.queryUserAuditImageList(params);
        for (UserAuditImageDTO userDTO : userAuditImageDTOList)
            userDTO.setMobile(turnMobile(userDTO.getMobile()));
        PagingResultWrapper<UserAuditImageDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(userMapper.queryUserAuditImageCount(params));
        wrapper.setTotalPage(userAuditImageDTOList.size() / params.getLimit());
        wrapper.setPageSize(params.getLimit());
        wrapper.setData((userAuditImageDTOList == null || userAuditImageDTOList.size() == 0) ? new ArrayList<>() : userAuditImageDTOList);
        return wrapper;
    }


    /**
     * 手机号码加信号处理
     */
    private static String turnMobile(String content) {
        int frontNum = 3;
        int endNum = 4;
        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        String starStr = "";
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, frontNum) + starStr
                + content.substring(content.length() - endNum, content.length());

    }

    /**
     * vip转换
     */
    private UserParams getVipParams(UserParams params) {
        //vip转换成相应的钻石
        if (StringUtils.hasText(params.getVip())) {
            String[] vips = params.getVip().split(",");
            params.setVips(vips);
        }
        return params;

    }

}
