package com.eryu.controller.manage;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.finance.FinaceRewardDTO;
import com.eryu.core.entity.dto.params.FinanceParam;
import com.eryu.core.service.finance.FinanceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户作品列表的相关信息 API
 * <P></P>
 * Created by troubleMan on 2017/9/21.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private static Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private FinanceService financeService;

    /**
     * 查询用户作品明细
     *
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceRewardDTO
     */
    @GetMapping(value = "/list")
    public PagingResultWrapper<FinaceRewardDTO> queryProductList(FinanceParam financeParam) {
        return financeService.getFinaceReward(financeParam);
    }
}