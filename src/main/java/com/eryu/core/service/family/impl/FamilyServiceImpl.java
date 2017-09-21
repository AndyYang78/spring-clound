package com.eryu.core.service.family.impl;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.family.FamilyDTO;
import com.eryu.core.entity.dto.family.FamilyIncomDTO;
import com.eryu.core.entity.dto.family.FamilyMembersDTO;
import com.eryu.core.entity.dto.params.FamilyParam;
import com.eryu.core.service.family.FamilyService;
import com.eryu.dto.SimpleResponse;
import com.eryu.mapper.FamilyMapper;
import com.eryu.user.dto.request.CreateFamilyRequest;
import com.eryu.user.dto.request.ModifyFamilyRequest;
import com.eryu.user.feignClients.FamilyClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 家族列表信息的相关实现  Api
 *
 * Created by troubleMan on 2017/7/27.
 */
@Service("familyService")
public class FamilyServiceImpl implements FamilyService {

    @Resource
    private FamilyMapper familyMapper;

    @Resource
    private FamilyClient familyClient;

    @Override
    public PagingResultWrapper<FamilyIncomDTO> queryFamilyIncomList(FamilyParam familyParam) {
        List<FamilyIncomDTO> familyIncomDTOS = familyMapper.queryFamilyIncome(familyParam);
        int count = familyMapper.queryFamilyIncomeCount(familyParam);
        PagingResultWrapper<FamilyIncomDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count/familyParam.getLimit());
        wrapper.setPageSize(familyParam.getLimit());
        wrapper.setData((familyIncomDTOS == null || familyIncomDTOS.isEmpty()) ? new ArrayList<>() : familyIncomDTOS);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<FamilyDTO> queryFamilyList(FamilyParam familyParam) {
        int count = familyMapper.queryFamilyListCount(familyParam);
        List<FamilyDTO> familyDTOS = familyMapper.queryFamilyList(familyParam);
        PagingResultWrapper<FamilyDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count/familyParam.getLimit());
        wrapper.setPageSize(familyParam.getLimit());
        wrapper.setData((familyDTOS == null || familyDTOS.isEmpty()) ? new ArrayList<>() : familyDTOS);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<FamilyMembersDTO> queryFamilyMemberList(FamilyParam familyParam) {
        int count = familyMapper.queryFamilyMemberCount(familyParam.getFamilyId());
        List<FamilyMembersDTO> familyMembersDTOList = familyMapper.queryFamilyMemberList(familyParam);
        PagingResultWrapper<FamilyMembersDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count/familyParam.getLimit());
        wrapper.setPageSize(familyParam.getLimit());
        wrapper.setData((familyMembersDTOList == null || familyMembersDTOList.isEmpty()) ? new ArrayList<>() : familyMembersDTOList);
        return wrapper;
    }

    @Override
    public SimpleResponse addFamily(CreateFamilyRequest familyParam) {
        //家族的uuid
        return familyClient.createFamilyOperating(familyParam);
    }

    @Override
    public SimpleResponse updateFamily(ModifyFamilyRequest familyParam) {
        return familyClient.updateFamilyOperation(familyParam);
    }
}
