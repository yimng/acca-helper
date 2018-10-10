package com.thinkgem.jeesite.acca.web.user.web;

import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.acca.web.user.service.InviteService;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/user/invite")
public class InviteController extends BaseController {

    @Autowired
    private InviteService inviteService;

    @ModelAttribute
    public Invite get(@RequestParam(required = false) Long id) {
        Invite entity = null;
        if (id != null) {
            entity = inviteService.get(id);
        }
        if (entity == null) {
            entity = new Invite();
        }
        return entity;
    }

    @RequiresPermissions("user:invite:view")
    @RequestMapping(value = {"list", ""})
    public String list(Invite invite, String status, Model model, @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "30") int pageSize) {
        PageInfo<Invite> page = inviteService.findPage(invite, status, pageNo, pageSize);

        model.addAttribute("page", page);
        model.addAttribute("invite", invite);
        model.addAttribute("status", status);
        return "web/user/webInviteList";
    }

    @RequiresPermissions("user:invite:view")
    @RequestMapping(value = {"listrank", ""})
    public String listRank(Model model,
                           @RequestParam(value = "start", required = false) String start,
                           @RequestParam(value = "end", required = false) String end,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(required = false, defaultValue = "1") int pageNo,
                           @RequestParam(required = false, defaultValue = "30") int pageSize) {
        List<InviteRank> inviteRank = inviteService.findInviteRank(start, end, sort, pageNo, pageSize);
        model.addAttribute("page", new PageInfo<InviteRank>(inviteRank));
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("sort", sort);
        return "web/user/webInviteRankList";
    }


}
