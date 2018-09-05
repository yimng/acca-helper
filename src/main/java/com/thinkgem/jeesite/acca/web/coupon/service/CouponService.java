package com.thinkgem.jeesite.acca.web.coupon.service;

import com.thinkgem.jeesite.acca.web.coupon.dao.CouponDao;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CouponService extends CrudService<CouponDao, Coupon> {
    @Override
    public Coupon get(String id) {
        return super.get(id);
    }

    @Override
    public List<Coupon> findList(Coupon coupon) {
        return super.findList(coupon);
    }

    @Override
    public Page<Coupon> findPage(Page<Coupon> page, Coupon coupon) {
        return super.findPage(page, coupon);
    }

    public Page<Coupon> findPageByName(Page<Coupon> page, String name) {
        Coupon coupon = new Coupon();
        coupon.setPage(page);
        page.setList(dao.findListByName(name));
        return page;
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Coupon coupon) {
        super.save(coupon);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Coupon coupon) {
        super.delete(coupon);
    }
}
