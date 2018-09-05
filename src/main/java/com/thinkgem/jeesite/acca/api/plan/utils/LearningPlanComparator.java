package com.thinkgem.jeesite.acca.api.plan.utils;

import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Young
 * @version 2016/8/31
 */
public class LearningPlanComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        AppUserLearningPlan plan1 = (AppUserLearningPlan) o1;
        AppUserLearningPlan plan2 = (AppUserLearningPlan) o2;
        if (plan1.getCourseId() > plan2.getCourseId()){
            return 1;
        } else if (plan1.getCourseId() < plan2.getCourseId()){
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 测试方法
     */
    public static void main(String[] args) {
        List<AppUserLearningPlan> list = new ArrayList<AppUserLearningPlan>();
        AppUserLearningPlan plan1 = new AppUserLearningPlan();
        plan1.setCourseId((long) 1);
        plan1.setCourseName("F1");
        list.add(plan1);
        AppUserLearningPlan plan2 = new AppUserLearningPlan();
        plan2.setCourseId((long) 2);
        plan2.setCourseName("F2");
        list.add(plan2);
        LearningPlanComparator comparator = new LearningPlanComparator();
        Collections.sort(list , comparator);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCourseId());
        }
    }
}
