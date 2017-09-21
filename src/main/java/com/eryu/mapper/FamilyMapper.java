package com.eryu.mapper;

import com.eryu.core.entity.dto.family.FamilyDTO;
import com.eryu.core.entity.dto.family.FamilyIncomDTO;
import com.eryu.core.entity.dto.family.FamilyMembersDTO;
import com.eryu.core.entity.dto.params.FamilyParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 家族相关SQL操作映射
 * <p>
 * Created by troubleMan 2017/07/27
 */
@Mapper
public interface FamilyMapper {

    /**
     * 查询家族的收益列表
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FamilyIncomDTO
     */
    List<FamilyIncomDTO> queryFamilyIncome(FamilyParam params);

    /**
     * 查询家族的收益列表 -- 数量
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FamilyIncomDTO
     */
    int queryFamilyIncomeCount(FamilyParam params);

    /**
     * 查询家族的信息列表
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FamilyIncomDTO
     */
    List<FamilyDTO> queryFamilyList(FamilyParam params);

    /**
     * 查询家族的信息列表-计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FamilyIncomDTO
     */
    int queryFamilyListCount(FamilyParam params);

    /**
     * 查询家族的成员信息列表
     *
     * @param params 传入参数 家族ID,分页数据
     * @return FamilyMembersDTO
     */
    List<FamilyMembersDTO> queryFamilyMemberList(FamilyParam params);
    /**
     * 查询家族的成员信息列表  计数
     *
     * @param familyId 传入参数 家族ID
     * @return FamilyMembersDTO
     */
    int queryFamilyMemberCount(@Param("familyId") String familyId);
    /**
     *
     * 查询族长的Id 通过手机
     * @param mobile 族长的手机
     */
    String getMasterId(@Param("mobile") String mobile);
    /**
     * 添加家族
     *
     * @param params 传入参数 家族信息
     */
    void addFamily(FamilyParam params);
    /**
     * 添加家族 成员信息
     *
     * @param params 传入参数 家族信息
     */
    void addFamilyMember(FamilyParam params);
}
