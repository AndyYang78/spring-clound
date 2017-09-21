package com.eryu.common.security;

import com.eryu.core.entity.po.manager.LocalPrivilege;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认权限配置
 * Created by yangtao on 2017/8/4.
 */
public final class DefaultPrivilege {

    //默认权限列表
    public List<LocalPrivilege> DEFAULT_PRIVILEGES = new ArrayList<>();

    {
        /*
         * 管理员
         */
        LocalPrivilege admin = new LocalPrivilege("超级管理员", "/view", "admin", "", "");
        DEFAULT_PRIVILEGES.add(admin);
        /*
         * 基础管理
         */
        LocalPrivilege basic = new LocalPrivilege("基础管理", "/view/basic", "basic", "", "");
        DEFAULT_PRIVILEGES.add(basic);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("页面模板", "/view/basic/template", basic.getModel(), "template", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("版本控制", "/view/basic/version", basic.getModel(), "version", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("系统公告", "/view/basic/announcement", basic.getModel(), "announcement", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("安卓自动更新", "/view/basic/update/auto", basic.getModel(), "update-auto", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("APP版本管理", "/view/basic/app/version", basic.getModel(), "app-version", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("Banner配置", "/view/basic/banner", basic.getModel(), "banner", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("兑换配置", "/view/basic/app", basic.getModel(), "app", "", basic));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("首页聊天室Tab配置", "/view/basic/tab", basic.getModel(), "tab", "", basic));

        /*
         * 用户管理
         */
        LocalPrivilege user = new LocalPrivilege("用户管理", "/view/user", "user", "", "");
        DEFAULT_PRIVILEGES.add(user);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("用户列表", "/view/user/list", user.getModel(), "list", "", user));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("用户反馈", "/view/user/report", user.getModel(), "feedback", "", user));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("验证码列表", "/view/user/code", user.getModel(), "verification-code", "", user));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("头像/背景审核", "/view/user/audit", user.getModel(), "audit", "", user));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("活动管理", "/view/user/activity", user.getModel(), "activity", "", user));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("账户明细", "/view/user/account", user.getModel(), "account-details", "", user));

        /*
         * 财务
         */
        LocalPrivilege finance = new LocalPrivilege("财务", "/view/finance", "finance", "finance", "");
        DEFAULT_PRIVILEGES.add(finance);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("充值订单", "/view/finance/recharge-order", finance.getModel(), "recharge-order", "", finance));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("提现列表", "/view/finance/cash-list", finance.getModel(), "cash-list", "", finance));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("打赏明细", "/view/finance/reward-list", finance.getModel(), "reward-list", "", finance));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("手工充值", "/view/finance/manual-recharge", finance.getModel(), "manual-recharge", "", finance));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("账户余额", "/view/finance/account-balance", finance.getModel(), "account-balance", "", finance));

        /*
         * 聊天室
         */
        LocalPrivilege chatroom = new LocalPrivilege("聊天室", "/view/chatroom", "chatroom", "", "");
        DEFAULT_PRIVILEGES.add(chatroom);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("在线聊天室", "/view/chatroom/online", chatroom.getModel(), "online", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("聊天室列表", "/view/chatroom/list", chatroom.getModel(), "list", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("打赏列表", "/view/chatroom/reward-list", chatroom.getModel(), "reward-list", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("打赏排行榜", "/view/chatroom/rank-give", chatroom.getModel(), "reward-rank-give", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("受赏排行榜", "/view/chatroom/reward-rank-get", chatroom.getModel(), "reward-rank-get", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("标签配置", "/view/chatroom/type", chatroom.getModel(), "type", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("模板配置", "/view/chatroom/template", chatroom.getModel(), "template", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("举报类型", "/view/chatroom/report-type", chatroom.getModel(), "report-type", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("举报信息管理", "/view/chatroom/report", chatroom.getModel(), "report", "", chatroom));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("举报用户管理", "/view/chatroom/report-user", chatroom.getModel(), "report-user", "", chatroom));

        /*
         * 家族
         */
        LocalPrivilege family = new LocalPrivilege("家族", "/view/family", "family", "", "");
        DEFAULT_PRIVILEGES.add(family);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("家族列表", "/view/family/list", family.getModel(), "list", "", family));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("家族收益列表", "/view/family/income-list", family.getModel(), "income-list", "", family));

        /*
         * 礼物
         */
        LocalPrivilege gift = new LocalPrivilege("礼物", "/view/gift", "gift", "", "");
        DEFAULT_PRIVILEGES.add(gift);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("礼物列表", "/view/gift/list", gift.getModel(), "list", "", gift));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("动效列表", "/view/gift/animation", gift.getModel(), "animation", "", gift));

        /**
         * 作品
         */
        LocalPrivilege production = new LocalPrivilege("作品", "/view/production", "production", "", "");
        DEFAULT_PRIVILEGES.add(production);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("作品列表", "/view/production/list", production.getModel(), "production-list", "", production));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("作品审核列表", "/view/production/audit", production.getModel(), "production-audit", "", production));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("分类管理", "/view/production/classify", production.getModel(), "production-classify", "", production));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("作品举报列表", "/view/production/report", production.getModel(), "production-report", "", production));

        /*
         * 系统设置
         */
        LocalPrivilege system = new LocalPrivilege("系统设置", "/view/system", "system", "", "");
        DEFAULT_PRIVILEGES.add(system);
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("系统用户管理", "/view/system/user", system.getModel(), "user", "", system));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("系统角色管理", "/view/system/role", system.getModel(), "role", "", system));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("系统权限管理", "/view/system/privilege", system.getModel(), "privilege", "", system));
        DEFAULT_PRIVILEGES.add(new LocalPrivilege("系统偏好设置", "/view/system/custom", system.getModel(), "custom", "", system));
    }
}