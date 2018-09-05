package com.thinkgem.jeesite.freetek.util.jpush;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author Michael
 * @version 2016/8/15
 * @des 构建PushPayload,这是进行推送的关键
 */
public class PayloadBuild {

	/**
	 * 推送平台是android和ios,通知内容为ALERT,android标题为Title,并且附加字段,根绝别名推送
	 * 
	 * @return
	 */
	public static PushPayload buildPushObject_android_and_ios_withAlias_and_extras(JpushEntity je) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(je.getAlias()))
				.setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
				.setNotification(Notification.newBuilder().setAlert(je.getAlert())
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(je.getTitle()).setBuilderId(3).addExtras(je.getExtra()).build())
						.addPlatformNotification(
								IosNotification.newBuilder().setBadge(1).addExtras(je.getExtra()).build())
						.build())
				.build();
	}
	
	/**
	 * 推送平台是android和ios,通知内容为ALERT,android标题为Title,并且附加字段。
	 * 
	 * @return
	 */
	public static PushPayload buildPushObject_android_and_ios_withAlias_and_extras2(JpushEntity je) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.all())
				.setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
				.setNotification(Notification.newBuilder().setAlert(je.getAlert())
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(je.getTitle()).setBuilderId(3).addExtras(je.getExtra()).build())
						.addPlatformNotification(
								IosNotification.newBuilder().setBadge(1).addExtras(je.getExtra()).build())
						.build())
				.build();
	}	

}
