package com.thinkgem.jeesite.acca.web.user.service;

import com.thinkgem.jeesite.acca.web.user.dao.InviteDao;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.acca.web.user.entity.MobileDeviceContact;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class InviteService extends CrudService<InviteDao, Invite> {
    @Autowired
    private InviteDao inviteDao;

    @Override
    public Invite get(String id) {
        return super.get(id);
    }

    @Override
    public List<Invite> findList(Invite invite) {
        return super.findList(invite);
    }

    @Override
    public Page<Invite> findPage(Page<Invite> page, Invite invite) {
        return super.findPage(page, invite);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Invite invite) {
        super.save(invite);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Invite invite) {
        super.delete(invite);
    }

    public List<Invite> findListByTimeAndPhone(Date start, Date end, String phone) {
        return inviteDao.findListByTimeAndPhone(start, end, phone);
    }

    public List<InviteRank> findInviteRank(Date start, Date end) {
        return inviteDao.findInviteRank(start, end);
    }
}
