package com.eryu.controller.manage;

import com.eryu.common.utils.ConfigEnum;
import com.eryu.common.utils.ExcelUtils;
import com.eryu.common.utils.TradeCode;
import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.finance.*;
import com.eryu.core.entity.dto.params.FinanceManualParam;
import com.eryu.core.entity.dto.params.FinanceParam;
import com.eryu.core.service.finance.FinanceService;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import com.eryu.trade.dto.request.WithdrawRejectRequest;
import com.eryu.trade.dto.request.WithdrawResultItem;
import com.eryu.trade.dto.request.WithdrawResultRequest;
import com.eryu.trade.dto.request.WithdrawVerifyRequest;
import com.eryu.trade.feignClients.WithdrawClient;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static cn.jpush.api.push.model.PushModel.gson;

/**
 *
 * 财务列表的相关信息 API
 * <P></P>
 * Created by troubleMan on 2017/7/27.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/finance")
public class FinanceController {

    private static Logger logger  = Logger.getLogger(FinanceController.class);

    @Autowired
    private FinanceService financeService;
    @Resource
    private WithdrawClient withdrawClient;
    /**
     * 查询打赏明细
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceRewardDTO
     */
    @GetMapping(value = "/reward/list")
    public PagingResultWrapper<FinaceRewardDTO> queryRewardList(FinanceParam financeParam) {
        return financeService.getFinaceReward(financeParam);
    }
    /**
     * 查询账户余额
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceAccountDTO
     */
    @GetMapping(value = "/account/balance")
    public PagingResultWrapper<FinaceAccountDTO> queryAccountBalance(FinanceParam financeParam) {
        return financeService.getFinaceAccountBalance(financeParam);
    }
    /**
     * 查询交易明细
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceRewardDTO
     */
    @GetMapping(value = "/trade/detail")
    public PagingResultWrapper<FinaceAccountDetailDTO> queryTradeDetail(FinanceParam financeParam) {
        return financeService.getTradeAccountDetail(financeParam);
    }
    /**
     * 查询充值订单明细
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceRewardDTO
     */
    @GetMapping(value = "/recharge/list")
    public PagingResultWrapper<FinaceRechargeListDTO> queryRechargeList(FinanceParam financeParam) {
        return financeService.getRechargeList(financeParam);
    }
    /**
     * 查询充值订单明细
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceWithDrawDTO
     */
    @GetMapping(value = "/withdraw/list")
    public PagingResultWrapper<FinaceWithDrawDTO> queryWithDrawList(FinanceParam financeParam) {
        return financeService.getWithdrawList(financeParam);
    }
    /**
     * 审核提现金额--通过
     * @param ids ，operater，id 操作人等
     * @return 通过是否成功
     */
    @PostMapping(value = "/withdraw/examine/approve")
    public SimpleResponse examineWithdrawApprove(String ids, String operator) {
        List<String> idList = gson.fromJson(ids,
                new TypeToken<List<String>>() {
                }.getType());
        WithdrawVerifyRequest withdrawVerifyRequest = new WithdrawRejectRequest();
        withdrawVerifyRequest.setIds(idList);
        withdrawVerifyRequest.setOperator(operator);
        SimpleResponse response = withdrawClient.withdrawApprove(withdrawVerifyRequest);
        return response;
    }

    /**
     * 审核提现金额--拒绝
     * @param ids, operater,reason限制参数，用户名，id，昵称等
     * @param operator, 操作人
     * @param reason, 原因
     * @return 拒绝是否成功
     */
    @PostMapping(value = "/withdraw/examine/reject")
    public SimpleResponse examineWithdrawReject(String ids, String operator, String reason) {
        List<String> idList = gson.fromJson(ids,
                new TypeToken<List<String>>() {
                }.getType());
        WithdrawRejectRequest withdrawVerifyRequest = new WithdrawRejectRequest();
        withdrawVerifyRequest.setIds(idList);
        withdrawVerifyRequest.setOperator(operator);
        withdrawVerifyRequest.setReason(reason);
        return withdrawClient.withdrawReject(withdrawVerifyRequest);
    }

    /**
     *
     * 打款失败，资金退回（打款之后用户的资金退回）
     *
     * @param ids 账户Id
     * @return 退款是否成功
     */
    @PostMapping(value = "/withdraw/money/back")
    public SimpleResponse returnAccount(String ids, String operator) {
        List<String> idList = gson.fromJson(ids,
                new TypeToken<List<String>>() {
                }.getType());
        return withdrawClient.withdrawPayFileRefund(idList);
    }

    /**
     * 手动将打款成功的记录设置为【打款失败-没有退款】!
     *
     * @param id 提现Id
     * @return 是否操作成功
     */
    @PostMapping("/withdraw/reset/fail")
    public SimpleResponse manualResetPayFail(String id,String password) {
        if (!password.equals(ConfigEnum.PASSWORD.toString())) {
            throw new BusinessException(TradeCode.PASSWORD_ERROR);
        }
        return withdrawClient.manualResetPayFail(id);
    }


    /**
     * 打款数据导入批量处理数据
     *
     * @param  payFile 账户Id 账户名 审核状态 审核结果
     * @return 批量审核
     */
    @PostMapping(value = "/batch/pay/deal")
    public SimpleResponse batchPaydeal(@RequestParam(value = "payFile") MultipartFile payFile, @RequestParam(value = "operator") String operator) {
        SimpleResponse response =new SimpleResponse();
        List<WithdrawResultItem> list = new ArrayList<>();
        //判断文件是否存在
        if(null == payFile){
            logger.error("文件不存在！");
            throw new BusinessException(TradeCode.NO_FILE);
        }
        try {
            Workbook workbook = ExcelUtils.getWorkBook(payFile);
            if(workbook != null){
                for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                    //获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    if(sheet == null){
                        continue;
                    }
                    //获得当前sheet的开始行
                    int firstRowNum  = sheet.getFirstRowNum();
                    //获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    //循环除了第一行的所有行
                    for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if(row == null){
                            continue;
                        }
                        //获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        //获得当前行的列数
                        int lastCellNum = row.getPhysicalNumberOfCells();
                        String[] cells = new String[row.getPhysicalNumberOfCells()];
                        if(cells.length!=10){
                            throw new BusinessException(TradeCode.WRONG_FILE);
                        }
                        //循环当前行
                        for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                            Cell cell = row.getCell(cellNum);
                            cells[cellNum] = ExcelUtils.getCellValue(cell);
                        }
                        WithdrawResultItem withdrawResultItem = new WithdrawResultItem();
                        //支付宝账号Id
                        withdrawResultItem.setAccountNo(cells[4]);
                        //提现Id
                        withdrawResultItem.setWithdrawId(cells[7]);
                        //用户Id
                        withdrawResultItem.setUserId(cells[8]);
                        //姓名
                        withdrawResultItem.setName(cells[1]);
                        //手机号
                        withdrawResultItem.setMobile(cells[0]);
                        //金额
                        withdrawResultItem.setAmount(Double.valueOf(cells[2]));
                        //水晶
                        withdrawResultItem.setCrystal(Integer.valueOf(cells[3]));
                        //打款状态
                        withdrawResultItem.setPayState(Integer.valueOf(cells[9]));
                        //打款备注
                        withdrawResultItem.setPayRemark(cells[6]);
                        //申请时间
                        withdrawResultItem.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(cells[5]));
                        list.add(withdrawResultItem);
                    }
                }
                workbook.close();
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setData(e.getMessage());
            return response;
        }
        WithdrawResultRequest request = new WithdrawResultRequest();
        request.setOperator(operator);
        request.setItems(list);
        return withdrawClient.withdrawPayResult(request);
    }

    /**
     * 取消打款
     *
     * @param id 提现Id列表
     * @return 是否处理成功
     */
    @PostMapping("/withdraw/pay/cancel")
    SimpleResponse withdrawPayCancel(@RequestParam("id") String id) {
        return withdrawClient.withdrawPayCancel(id);
    }

    /**
     * 查询手工充值订单明细
     * <p></p>
     *
     * @param financeParam 限制参数，用户名，id，昵称等
     * @return FinaceWithDrawDTO
     */
    @GetMapping(value = "/manual/recharge/list")
    public PagingResultWrapper<FinaceManualListDTO> queryManualRechargeList(FinanceParam financeParam) {
        return financeService.getManualList(financeParam);
    }

    /**
     * 手工充值用户的账户
     * <p></p>
     *
     * @param param 限制参数，用户手机，充值金额等
     * @return SimpleResponse
     */
    @PostMapping(value = "/manual/recharge")
    public SimpleResponse saveManualRecharge(FinanceManualParam param) {
        if (!param.getRechargePassword().equals(ConfigEnum.PASSWORD.toString())) {
            throw new BusinessException(TradeCode.PASSWORD_ERROR);
        }
        return financeService.saveManualRecharge(param);
    }


}