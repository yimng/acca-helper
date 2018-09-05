package com.thinkgem.jeesite.acca.web.exam.utils;

import com.thinkgem.jeesite.acca.web.exam.entity.WebSignup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Young
 * @version 2016/9/5
 */
public class SignupComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        WebSignup plan1 = (WebSignup) o1;
        WebSignup plan2 = (WebSignup) o2;
        if (plan1.getExamCourseId() > plan2.getExamCourseId()){
            return 1;
        } else if (plan1.getExamCourseId() < plan2.getExamCourseId()){
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 测试方法
     */
    public static void main(String[] args) {
        List<WebSignup> list = new ArrayList<WebSignup>();
        WebSignup plan1 = new WebSignup();
        plan1.setExamCourseId(1L);
        plan1.setCourse("F1");
        list.add(plan1);
        WebSignup plan3 = new WebSignup();
        plan3.setExamCourseId(3L);
        plan3.setCourse("F3");
        list.add(plan3);
        WebSignup plan2 = new WebSignup();
        plan2.setExamCourseId(2L);
        plan2.setCourse("F2");
        list.add(plan2);

        SignupComparator comparator = new SignupComparator();
        Collections.sort(list , comparator);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCourse());
        }
    }
}
