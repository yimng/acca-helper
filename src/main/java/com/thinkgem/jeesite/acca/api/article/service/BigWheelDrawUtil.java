package com.thinkgem.jeesite.acca.api.article.service;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.acca.api.article.entity.Lottery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * wchat大转盘抽奖活动
 *
 * @author yanst 2016/4/23 9:23
 */
public class BigWheelDrawUtil {


    /**
     * 给转盘的每个角度赋初始值
     * @return
     */
    private final static List<Lottery> initDrawList = new ArrayList<Lottery>() {{
    	//Lottery( Long id,Integer num, String name, Integer v, Long activityId, Long awardCategoryId, String awardCategoryName, Long cardRuleId) 	
        add(new Lottery(1L,1, "CMA中文全科U+网课套餐", 1,2L,2L,"网课代金券","8a22ecb55b1ec7e9015b3d3ee891054a"));
        add(new Lottery(3L,2, "IPad mini 4", 2,1L,1L,"实物奖品",""));
        add(new Lottery(2L,5, "单科中文CMA U+网课套餐", 5,2L,2L,"网课代金券","8a22ecb55b1ec7e9015b3d432a94054b"));
        add(new Lottery(4L,30,"一步通关课程代金券 价值2500元", 30,2L,2L,"网课代金券","8a22ecb55b1ec7e9015b3d471497054c"));
        add(new Lottery(5L,500,"CMA U+网课代金券 价值2000元", 500,2L,2L,"网课代金券","8a22ecb55b1ec7e9015b3d4bdf97054e"));
        add(new Lottery(6L,300,"单科中文CMA串讲课 价值1900元", 300,2L,2L,"网课代金券","8a22ecb55b1ec7e9015b3d4a2f27054d"));
        add(new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"网课代金券","8a22ecb55b1ec7e9015b3be9e0bd03a6"));
    }};
    /**
     * 生成奖项
     * @return
     */
    public static Lottery generateAward() {
        List<Lottery> initData = initDrawList;
        long result = randomnum(1, 2000);
        int line = 0;
        int temp = 0;
        Lottery returnobj = null;
        int index = 0;
        for (int i = 0; i < initDrawList.size(); i++) {
            Lottery obj2 = initDrawList.get(i);
            int c = obj2.getV();
            temp = temp + c;
            line = 2000 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

    // 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
            int range = smax - smin;
            double rand = Math.random();
            return (smin + Math.round(rand * range));
    }

    public static void main(String[] args) {
    	for(int i=1;i<2000;i++){
    		System.out.println(JSON.toJSONString(generateAward()));
    	}
    }

}