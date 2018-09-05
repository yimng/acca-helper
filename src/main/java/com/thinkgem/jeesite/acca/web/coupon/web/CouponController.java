package com.thinkgem.jeesite.acca.web.coupon.web;

import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.coupon.service.CouponService;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.feedback.entity.WebFeedback;
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
@RequestMapping(value = "${adminPath}/web/coupon")
public class CouponController extends BaseController {
    @Autowired
    private CouponService  couponService;

    @ModelAttribute
    public Coupon get(@RequestParam(required=false) String id) {
        Coupon entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = couponService.get(id);
        }
        if (entity == null){
            entity = new Coupon();
        }
        return entity;
    }

    @RequiresPermissions("web:coupon:view")
    @RequestMapping(value = "form")
    public String form(Coupon coupon, Model model) {
        model.addAttribute("coupon", coupon);
        return "web/coupon/couponForm";
    }

    @RequiresPermissions("web:coupon:view")
    @RequestMapping(value = {"list", ""})
    public String list(Coupon coupon, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Coupon> page = couponService.findPage(new Page<Coupon>(request, response), coupon);
        model.addAttribute("page", page);
        return "web/coupon/couponList";
    }

//    @RequiresPermissions("web:coupon:view")
//    @RequestMapping(value = {"listbyname", ""})
//    public String list2(String name, HttpServletRequest request, HttpServletResponse response, Model model) {
//        Page<Coupon> page = couponService.findPageByName(new Page<Coupon>(request, response), name);
//        model.addAttribute("page", page);
//        return "web/coupon/couponList";
//    }

    @RequiresPermissions("web:coupon:edit")
    @RequestMapping(value = "save")
    public String save(Coupon coupon, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, coupon)){
            return form(coupon, model);
        }
        couponService.save(coupon);
        addMessage(redirectAttributes, "保存Coupon成功");
        return "redirect:"+ Global.getAdminPath()+"/web/coupon/?repage";
    }
}
