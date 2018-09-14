package com.thinkgem.jeesite.acca.api.user.service;

import com.thinkgem.jeesite.acca.api.user.dao.AppUserCouponDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppUserCoupon;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AppUserCouponService extends CrudService<AppUserCouponDao, AppUserCoupon> {
}
