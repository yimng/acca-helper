package com.thinkgem.jeesite.acca.api.user.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Lukun
 * @version 2018-09-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppInvite extends DataEntity<AppInvite> {
    private static final long serialVersionUID = 1L;

    private String inviterPhone;
    private String inviteePhone;
    private Date inviteTime;
    private Date successTime;
}
