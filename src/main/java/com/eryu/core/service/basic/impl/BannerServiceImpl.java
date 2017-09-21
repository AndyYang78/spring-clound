package com.eryu.core.service.basic.impl;

import com.eryu.core.repo.content.BannerRepo;
import com.eryu.core.service.basic.BannerService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * service impl
 * Created by yangtao on 2017/8/1.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerRepo bannerRepo;

    /**
     * 列表
     */
    @Override
    public List<?> list(String position, Boolean usable) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "position"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "usable"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "weight"));
        return bannerRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(position))
                predicates.add(cb.equal(root.<String>get("position"), position));
            if (null != usable)
                predicates.add(cb.equal(root.<Boolean>get("usable"), usable));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, new Sort(orders));
    }
}
