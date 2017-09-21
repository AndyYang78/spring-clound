package com.eryu.core.service.content.impl;

import com.eryu.content.client.ChatRoomClient;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomReportCreateParams;
import com.eryu.core.entity.dto.params.ReportListParams;
import com.eryu.core.repo.content.ReportChatRoomRepo;
import com.eryu.core.service.content.ChatRoomReportService;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 聊天室举报信息
 * Created by yangtao on 2017/7/18.
 */
@Service
public class ChatRoomReportServiceImpl implements ChatRoomReportService {

    @Resource
    private ReportChatRoomRepo reportChatRoomRepo;

    @Resource
    private ChatRoomClient chatRoomClient;

    /**
     * 列表
     */
    @Override
    public List<?> list(final ReportListParams params) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "state"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        return reportChatRoomRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //用户id
            if (StringUtils.hasText(params.getUserId())) {
                predicates.add(cb.equal(root.<String>get("reportUserEryuNo"), params.getUserId()));
            }
            //举报类型
            if (StringUtils.hasText(params.getReportType())) {
                predicates.add(cb.equal(root.<String>get("typeId"), params.getReportType()));
            }
            //状态
            if (null != params.getState()) {
                predicates.add(cb.equal(root.<Integer>get("state"), params.getState()));
            }
            //时间
            //举报类型
            if (StringUtils.hasText(params.getStart()) && StringUtils.hasText(params.getStart())) {
                //处理时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate;
                Date endDate;
                try {
                    startDate = format.parse(params.getStart());
                    endDate = format.parse(params.getEnd());
                } catch (ParseException e) {
                    throw new BusinessException(40000, "时间格式错误！");
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.DATE, 1);
                endDate = calendar.getTime();
                predicates.add(cb.between(root.get("createTime"), startDate, endDate));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, new Sort(orders));
    }

    /**
     * 创建
     */
    @Override
    public void create(ChatRoomReportCreateParams params) {
        handleResponse(chatRoomClient.report(params));
    }

    /**
     * 完成
     */
    @Override
    public void complete(ChatRoomIdParams params) {
        handleResponse(chatRoomClient.completeReport(params));
    }

    /**
     * 删除
     */
    @Override
    public void remove(ChatRoomIdParams params) {
        handleResponse(chatRoomClient.removeReport(params));

    }

    private void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(4000, response.getMessage());
    }
}
