package com.thinkgem.jeesite.acca.web.user.web;

import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.acca.web.user.service.InviteService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/user/invite")
public class InviteController extends BaseController {

    @Autowired
    private InviteService inviteService;

    @ModelAttribute
    public Invite get(@RequestParam(required=false) String id) {
        Invite entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = inviteService.get(id);
        }
        if (entity == null){
            entity = new Invite();
        }
        return entity;
    }

    @RequiresPermissions("user:invite:view")
    @RequestMapping(value = {"list", ""})
    public String list(Invite invite,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Invite> page = inviteService.findPage(new Page<Invite>(request, response), invite);

        model.addAttribute("page", page);
//        Map<String, String> result = new HashMap<String, String>();
//        for (Invite.InviteStatus s : Invite.InviteStatus.values()) {
//            result.put(s.getShortName(), s.getFullName());
//        }
        model.addAttribute("status", Invite.InviteStatus.values());
        return "web/user/webInviteList";
    }

    @RequiresPermissions("user:invite:view")
    @RequestMapping(value = {"listrank", ""})
    public String listRank(@RequestParam(value = "start", required = false) Date start,
                           @RequestParam(value = "end", required = false) Date end, Model model) {
        List<InviteRank> inviteRank = inviteService.findInviteRank(start, end);
        model.addAttribute("inviteRankList",inviteRank);
        return "web/user/webInviteRankList";
    }



}
