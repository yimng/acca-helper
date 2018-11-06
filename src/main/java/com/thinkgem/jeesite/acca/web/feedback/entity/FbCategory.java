package com.thinkgem.jeesite.acca.web.feedback.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tbl_fb_category")
public class FbCategory implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}