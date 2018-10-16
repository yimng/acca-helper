package com.thinkgem.jeesite.acca.web.user.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class InviteRank {
    private static final long serialVersionUID = 1L;
    private String phone;
    private Integer successCount;
}
