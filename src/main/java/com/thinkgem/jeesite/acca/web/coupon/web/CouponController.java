package com.thinkgem.jeesite.acca.web.coupon.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.service.AppAccaUserService;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.coupon.service.CouponService;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.acca.web.user.service.UserCouponService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/web/coupon")
public class CouponController extends BaseController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private AppAccaUserService accaUserService;

    @ModelAttribute
    public Coupon get(@RequestParam(required = false) Long id) {
        Coupon entity = null;
        if (id != null) {
            entity = couponService.get(id);
        }
        if (entity == null) {
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
    public String list(Coupon coupon,Model model, @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "3") int pageSize) {
        PageInfo<Coupon> pageinfo = couponService.findPage(coupon, pageNo, pageSize);
        model.addAttribute("page", pageinfo);
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
        if (!beanValidator(model, coupon)) {
            return form(coupon, model);
        }
        couponService.save(coupon);
        addMessage(redirectAttributes, "保存Coupon成功");
        return "redirect:" + Global.getAdminPath() + "/web/coupon/?repage";
    }

    @RequiresPermissions("web:coupon:edit")
    @RequestMapping(value = "saveimport")
    public String saveimport(Coupon coupon, Model model, @Param(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, coupon)) {
            return form(coupon, model);
        }
        couponService.save(coupon);
        if (coupon.getAssign()) {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                if (!fileName.endsWith("xlsx") && !fileName.endsWith("xls")) {
                    addMessage(model, "上传文件格式有误");
                    return "web/coupon/couponForm";
                }
                Workbook wb = null;
                if (fileName.endsWith("xlsx")) {
                    wb = new XSSFWorkbook(file.getInputStream());
                } else if (fileName.endsWith("xls")) {
                    wb = new HSSFWorkbook(file.getInputStream());
                }
                List<String> phones = Lists.newArrayList();
                    //获取第一个sheet，可以利用循环获取每个sheet的内容
                    Sheet sheet = wb.getSheetAt(0);

                    //第一行是标题，跳过
                    for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            //获取第一列的内容
                            Cell cell = row.getCell(0);
                            String phone;
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                phone = cell.getStringCellValue();
                            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                DecimalFormat df = new DecimalFormat("###########");
                                phone = df.format(cell.getNumericCellValue());
                            } else {
                                throw new RuntimeException("Invalid value in excel");
                            }
                            phones.add(phone);
                        }
                    }
                List<UserCoupon> userCoupons = Lists.newArrayList();
                for (String phone : phones) {
                    UserCoupon userCoupon = new UserCoupon();
                    AppAccaUser accaUser = accaUserService.getAccaUserByPhone(phone);
                    if (accaUser == null) {
                        continue;
                    }

                    userCoupon.setUserId(accaUser.getAccaUserId());
                    userCoupon.setCouponId(coupon.getId());
                    userCoupon.setDelFlag("0");
                    userCoupons.add(userCoupon);
                }
                if (userCoupons.size() > 0) {
                    for (UserCoupon userCoupon: userCoupons) {
                        userCouponService.saveOrUpdate(userCoupon);
                    }
                    coupon.setReceived(userCoupons.size());
                    couponService.update(coupon);
                }
            }
        }
        addMessage(redirectAttributes, "保存Coupon成功");
        return "redirect:" + Global.getAdminPath() + "/web/coupon/?repage";
    }
}
