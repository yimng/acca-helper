package com.thinkgem.jeesite.acca.web.user.web;

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
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/user/invite")
public class InviteController extends BaseController {

    @Autowired
    private InviteService inviteService;

    @RequiresPermissions("user:invite:view")
    @RequestMapping(value = {"list", ""})
    public String list(Invite invite, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Invite> page = inviteService.findPage(new Page<Invite>(request, response), invite);

        model.addAttribute("page", page);
        return "web/user/inviteList";
    }

    @RequestMapping(value = {"listrank", ""})
    public String listRank(Date start, Date end, Model model) {
        List<InviteRank> inviteRank = inviteService.findInviteRank(start, end);
        model.addAllAttributes(inviteRank);
        return "web/user/inviteRankList";
    }



}
