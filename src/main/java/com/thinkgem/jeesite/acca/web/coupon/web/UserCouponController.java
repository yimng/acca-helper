package com.thinkgem.jeesite.acca.web.coupon.web;

import com.thinkgem.jeesite.acca.web.coupon.entity.UserCoupon;
import com.thinkgem.jeesite.acca.web.coupon.service.UserCouponService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/web/usercoupon")
public class UserCouponController extends BaseController {

    @Autowired
    private UserCouponService userCouponService;

    @ModelAttribute
    public UserCoupon get(@RequestParam(required=false) String id) {
        UserCoupon entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = userCouponService.get(id);
        }
        if (entity == null){
            entity = new UserCoupon();
        }
        return entity;
    }

    @RequiresPermissions("web:coupon:view")
    @RequestMapping(value = "form")
    public String form(UserCoupon userCoupon, Model model) {
        model.addAttribute("usercoupon", userCoupon);
        return "web/coupon/usercouponForm";
    }

    @RequiresPermissions("web:coupon:view")
    @RequestMapping(value = {"list", ""})
    public String list(UserCoupon userCoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<UserCoupon> page = userCouponService.findPage(new Page<UserCoupon>(request, response), userCoupon);
        model.addAttribute("page", page);
        return "web/coupon/usercouponList";
    }

    @RequiresPermissions("web:coupon:edit")
    @RequestMapping(value = "save")
    public String save(UserCoupon userCoupon, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, userCoupon)) {
            return form(userCoupon,model);

        }
        userCouponService.save(userCoupon);
        addMessage(redirectAttributes, "保存UserCoupon成功");
        return "redirect:" + Global.getAdminPath() + "web/coupon/?repage";
    }
}
