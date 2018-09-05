package com.thinkgem.jeesite.acca.api.user.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppInvite extends DataEntity<AppInvite> {
    private static final long serialVersionUID = 1L;

    private String inviterPhone;
    private String inviteePhone;
    private Date inviteTime;
    private Date successTime;
}
