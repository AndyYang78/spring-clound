package com.eryu.controller.manage;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.family.FamilyDTO;
import com.eryu.core.entity.dto.family.FamilyIncomDTO;
import com.eryu.core.entity.dto.family.FamilyMembersDTO;
import com.eryu.core.entity.dto.params.FamilyParam;
import com.eryu.core.service.family.FamilyService;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.CreateFamilyRequest;
import com.eryu.user.dto.request.ModifyFamilyRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 家族列表的相关信息 API
 * <P></P>
 * Created by troubleMan on 2017/7/27.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;
    /**
     * 查询家族收益列表
     *
     * @return
     * @author troubleMan
     */
    @ApiOperation(value = "查询家族收益列表", notes = "查询家族收益列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", required = true, value = "分页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", required = false, value = "限制", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "familyName", required = false, value = "家族名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "patriarchId", required = false, value = "族长ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "patriarchNick", required = false, value = "族长昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", required = false, value = "时间", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/income/list")
    public PagingResultWrapper<FamilyIncomDTO> queryFamilyIncome(FamilyParam familyParam) {
        return familyService.queryFamilyIncomList(familyParam);
    }

    /**
     * 查询家族列表
     *
     * @return
     * @author troubleMan
     */
    @ApiOperation(value = "查询家族列表", notes = "查询家族列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", required = true, value = "分页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", required = false, value = "限制", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "familyName", required = false, value = "家族名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "patriarchId", required = false, value = "族长ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "patriarchNick", required = false, value = "族长昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "familyStatus", required = false, value = "家族状态", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "examineStatus", required = false, value = "审核状态", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/list")
    public PagingResultWrapper<FamilyDTO> queryFamilyList(FamilyParam familyParam) {
       return familyService.queryFamilyList(familyParam);
    }


    /**
     * 查询家族的成员列表
     *
     * @return
     * @author troubleMan
     */
    @ApiOperation(value = "查询家族列表", notes = "查询家族列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", required = true, value = "分页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", required = true, value = "限制", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "familyId", required = false, value = "家族Id", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/member/list")
    public PagingResultWrapper<FamilyMembersDTO> queryFamilyMemberList(FamilyParam familyParam) {
        return familyService.queryFamilyMemberList(familyParam);
    }

    /**
     * 添加家族
     *
     * @return
     * @author troubleMan
     */
    @PostMapping(value = "/add")
    public SimpleResponse addFamilyMessage(CreateFamilyRequest familyParam) {
        return familyService.addFamily(familyParam);
    }
    /**
     * 更新家族
     *
     * @return
     * @author troubleMan
     */
    @PostMapping(value = "/update")
    public SimpleResponse updateFamilyMessage(ModifyFamilyRequest familyParam) {
        return familyService.updateFamily(familyParam);
    }


}
