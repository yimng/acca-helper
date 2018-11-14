//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.thinkgem.jeesite.acca.api.exam.service;

import com.thinkgem.jeesite.acca.api.exam.dao.*;
import com.thinkgem.jeesite.acca.api.exam.entity.*;
import com.thinkgem.jeesite.acca.api.model.request.AppOfficialOrderReq;
import com.thinkgem.jeesite.acca.api.model.request.ReSaveOrderReq;
import com.thinkgem.jeesite.acca.api.model.response.OrderInfoResponse;
import com.thinkgem.jeesite.acca.api.order.dao.AppOffiOrderDao;
import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallSignup;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.exam.dao.WebOrderDao;
import com.thinkgem.jeesite.acca.web.sysconf.dao.WebConfExamTipsDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfExamTips;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(
    readOnly = true
)
public class AppOfficialOrderService extends CrudService<AppOfficialOrderDao, AppOfficialOrder> {
    @Autowired
    private AppOfficialExamCourseDao courseDao;
    @Autowired
    private AppOfficialExamSignupDao signupDao;
    @Autowired
    private AppOfficialExamDao appOfficialExamDao;
    @Autowired
    private AppOfficialExamPlaceDao placeDao;
    
    @Autowired
	private AppOffiOrderDao appOffiOrderDao;
    
    @Autowired
    private AppExamSignupDao appExamSignupDao;
    
    @Autowired
    private WebOrderDao webOrderDao;
    
    @Autowired
    private AppAccaUserDao appAccaUserDao;
    
    @Autowired
    private WebConfExamTipsDao tipsDao;
    
    @Autowired
    private AppConfExamTipsDao appConfExamTipsDao;

    public AppOfficialOrderService() {
    }

    @Override
    public AppOfficialOrder get(String id) {
        return (AppOfficialOrder)super.get(id);
    }

    @Override
    public List<AppOfficialOrder> findList(AppOfficialOrder appOfficialOrder) {
        return super.findList(appOfficialOrder);
    }

    @Override
    public Page<AppOfficialOrder> findPage(Page<AppOfficialOrder> page, AppOfficialOrder appOfficialOrder) {
        return super.findPage(page, appOfficialOrder);
    }

    @Transactional(
        readOnly = false
    )
    public OrderInfoResponse saveOrder(AppOfficialOrder appOfficialOrder, AppOfficialOrderReq req) {
        appOfficialOrder.preInsert();
        appOfficialOrder.setDelFlag("0");
        appOfficialOrder.setOrg(req.getOrg());
        appOfficialOrder.setOrderStatus(Constants.OrderStatus.unpay);
        this.save(appOfficialOrder);
        
        AppAccaUser entity = new AppAccaUser();
		entity.setAccaUserId(req.getAccaUserId());
		entity.setRegisterName(req.getRegisterName());
		entity.setRegisterCardNumber(req.getRegisterCardNumber());
		entity.setRegisterEmail(req.getRegisterEmail());
		entity.setRegisterPhone(req.getRegisterPhone());
		entity.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
		entity.setCardType(req.getCardType());
		entity.setAccaRegisterName(req.getAccaRegisterName());
		entity.setAccaRegisterPassword(req.getAccaRegisterPassword());
        entity.setOrg(req.getOrg());
		appAccaUserDao.updateRegister(entity);
        
        double amount = 0.0D;
        
        ArrayList listWriOrders = new ArrayList();
        ArrayList listMacOrders = new ArrayList();
        
        Appointment appointmentPen = req.getPenAppInfo();
        if(appointmentPen != null){
	        Iterator response = req.getPenAppInfo().getCourseInfos().iterator();
	        while(response.hasNext()) {
	        	SmallCourseInfo penCourseInfo = (SmallCourseInfo)response.next();
	            AppOfficialExam aol = new AppOfficialExam();
	            aol.setCourseId(penCourseInfo.getCourseId());
	            aol.setExamStartTimeStr(req.getExamTimeStr());
	            aol.setExamType(Constants.ExamType.officialPen);
	            aol.setExamPlaceId(appointmentPen.getExamPlaceId());
	            AppOfficialExam c = this.appOfficialExamDao.getExamByCourseAndPlace(aol);
	            if(c.getPrice() != null) {
	                amount += c.getPrice().doubleValue();
	            }
	
	            AppOfficialExamSignup c1 = new AppOfficialExamSignup(c, appOfficialOrder);
	            c1.preInsert();
	            c1.setDelFlag("0");
                c1.setOrg(req.getOrg());
	            c1.setExamSignupStatus(Constants.OrderStatus.unpay);
	            c1.setExamCourseId(penCourseInfo.getCourseId());
	            c1.setExamVersionJson(JsonMapper.toJsonString(penCourseInfo.getExamVersion()));
	            this.signupDao.insert(c1);
	            listWriOrders.add(c1);
	        }
        }

        Appointment appointmentMac1 = req.getMacAppInfo();
        
        if(appointmentMac1 != null){
	        Iterator c2 = appointmentMac1.getCourseInfos().iterator();
	        while(c2.hasNext()) {
	        	SmallCourseInfo maxCourseInfo = (SmallCourseInfo)c2.next();
	            Long response1 = maxCourseInfo.getCourseId();
	            AppOfficialExam aol = new AppOfficialExam();
	            aol.setCourseId(maxCourseInfo.getCourseId());
	            aol.setExamStartTimeStr(req.getExamTimeStr());
	            aol.setExamType(Constants.ExamType.officialM);
	            aol.setExamPlaceId(appointmentMac1.getExamPlaceId());
	            AppOfficialExam c3 = this.appOfficialExamDao.getExamByCourseAndPlace(aol);
	            if(c3.getPrice() != null) {
	                amount += c3.getPrice().doubleValue();
	            }
	
	            AppOfficialExamSignup signup = new AppOfficialExamSignup(c3, appOfficialOrder);
	            signup.preInsert();
	            signup.setDelFlag("0");
                signup.setOrg(req.getOrg());
	            signup.setExamSignupStatus(Constants.OrderStatus.unpay);
	            signup.setExamCourseId(response1);
	            signup.setExamVersionJson(JsonMapper.toJsonString(maxCourseInfo.getExamVersion()));
	            this.signupDao.insert(signup);
	            listMacOrders.add(signup);
	        }
        }

        appOfficialOrder.setAmount(Double.valueOf(amount));
        this.save(appOfficialOrder);
        OrderInfoResponse response2 = new OrderInfoResponse(RespConstants.GLOBAL_SUCCESS);
        response2.setOrder(appOfficialOrder);
        if(appointmentPen != null){
        	response2.setWriPlace((AppOfficialExamPlace)this.placeDao.get(new AppOfficialExamPlace(req.getPenAppInfo().getExamPlaceId())));
        }
        if(appointmentMac1 != null){
        	response2.setMacPlace((AppOfficialExamPlace)this.placeDao.get(new AppOfficialExamPlace(req.getMacAppInfo().getExamPlaceId())));
        }
        response2.setListWriOrders(listWriOrders);
        response2.setListMacOrders(listMacOrders);
        List<WebConfExamTips> tips = tipsDao.findList(new WebConfExamTips());
        response2.setTips(tips.get(0).getOfficialExamProcess());
        
        AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("5"));
        String payConfTips =conf.getOfficialExamProcess();
        response2.setPayConfTips(payConfTips);
        return response2;
    }

    @Override
    @Transactional(
        readOnly = false
    )
    public void save(AppOfficialOrder appOfficialOrder) {
        if(appOfficialOrder.getOrderId() == null) {
            appOfficialOrder.preInsert();
            ((AppOfficialOrderDao)this.dao).insert(appOfficialOrder);
        } else {
            appOfficialOrder.preUpdate();
            ((AppOfficialOrderDao)this.dao).update(appOfficialOrder);
        }

    }
    
    @Transactional(
        readOnly = false
    )
    public void upd(AppOfficialOrder appOfficialOrder) {
    	dao.update(appOfficialOrder);
    }

    @Override
    @Transactional(
        readOnly = false
    )
    public void delete(AppOfficialOrder appOfficialOrder) {
        super.delete(appOfficialOrder);
    }
    
    @Transactional(readOnly = false)
    public void updAll(AppOfficialOrder appOfficialOrder){
    	appOfficialOrder.preUpdate();
    	dao.update(appOfficialOrder);
    	SmallDetailOrder orderInfo = appOffiOrderDao.getOfficialOrderInfo(appOfficialOrder);
    	for(SmallSignup signup : orderInfo.getSignupList()){
    		AppOfficialExamSignup sig = new AppOfficialExamSignup();
    		sig.setExamSignupId(signup.getExamSignupId());
    		sig.setExamSignupStatus(appOfficialOrder.getOrderStatus());
    		signupDao.update(sig);
    		int examType = signup.getExamType();
    		if(appOfficialOrder.getOrderStatus()==Constants.OrderStatus.cancel && (examType&1)==1){
    			webOrderDao.subUsedSeats(signup.getExamId());
    		}
    	}
    }

    @Transactional(readOnly = false)
	public BaseResponse saveOrderPay(Long orderId, Long orderPayImageId, String payerAccountName, AppAccaUser appUser) {
		
    	AppOfficialOrder order = this.get(new AppOfficialOrder(orderId));
    	logger.info("order:"+order);
    	
    	if(order.getOrderStatus()!=10){
    		return new BaseResponse(RespConstants.GLOBAL_PARAM_ERROR);
    	}
    	
    	AppOfficialOrder entity = new AppOfficialOrder();
    	entity.setOrderId(orderId);
    	entity.setOrderPayImgId(orderPayImageId);
    	entity.setPayerAccountName(payerAccountName);
    	entity.setOrderStatus(Constants.OrderStatus.uncheckd);
    	entity.setUpdateDate(new Date());
    	dao.update(entity);
		
    	
    	//需要将tbl_exam_signup表的exam_signup_status字段同步修改成待审核状态
    	appExamSignupDao.updateStatusByOrderId(orderId, Constants.OrderStatus.uncheckd);
    	
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

    @Transactional(readOnly = false)
	public BaseResponse resaveOrder(ReSaveOrderReq req) {
        AppOfficialOrder order = this.get(new AppOfficialOrder(req.getOrderId()));
        if (order.getOrderStatus() != Constants.OrderStatus.checkFail && order.getOrderStatus() != Constants.OrderStatus.checkSupplement ) {
            return new BaseResponse(RespConstants.GLOBAL_PARAM_ERROR);
        }
        order.setOrderPayImgId(req.getOrderPayImgId());
        order.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
        order.setRegisterName(req.getRegisterName());
        order.setAccaRegisterName(req.getAccaRegisterName());
        order.setRegisterPhone(req.getRegisterPhone());
        order.setRegisterEmail(req.getRegisterEmail());
        order.setRegisterCardNumber(req.getRegisterCardNumber());
        order.setOrderStatus(Constants.OrderStatus.uncheckd);
        dao.update(order);
        appExamSignupDao.updateStatusByOrderId(req.getOrderId(), Constants.OrderStatus.uncheckd);
        return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
    }
}
