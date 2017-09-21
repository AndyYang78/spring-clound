package com.eryu.core.service.family;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.family.FamilyDTO;
import com.eryu.core.entity.dto.family.FamilyIncomDTO;
import com.eryu.core.entity.dto.family.FamilyMembersDTO;
import com.eryu.core.entity.dto.params.FamilyParam;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.CreateFamilyRequest;
import com.eryu.user.dto.request.ModifyFamilyRequest;

/**
 *
 * 查询家族相关信息API实现
 *<p></p>
 *
 * Created by troubleMan on 2017/7/27.
 */
public interface FamilyService {

    /**
     * 查询家族的收益信息
     *@param familyParam 家族昵称  id  时间
     * @return  FamilyIncomDTO
     */
    PagingResultWrapper<FamilyIncomDTO> queryFamilyIncomList(FamilyParam familyParam);

    /**
     * 查询家族的信息列表
     *@param familyParam 家族昵称  id  时间
     * @return  FamilyIncomDTO
     */
    PagingResultWrapper<FamilyDTO> queryFamilyList(FamilyParam familyParam);

    /**
     * 查询家族的成员列表
     *@param familyParam 家族id
     * @return  FamilyIncomDTO
     */
    PagingResultWrapper<FamilyMembersDTO> queryFamilyMemberList(FamilyParam familyParam);

    /**
     * 添加家族成员
     *@param familyParam 家族，名称，头像 等信息
     * @return  void
     */
    SimpleResponse addFamily(CreateFamilyRequest familyParam);

    /**
     * 修改家族成员
     *@param familyParam 家族，名称，头像 等信息
     * @return  void
     */
    SimpleResponse updateFamily(ModifyFamilyRequest familyParam);

}
