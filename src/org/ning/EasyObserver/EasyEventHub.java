package org.ning.EasyObserver;

import java.util.LinkedHashMap;

import org.ning.EasyObserver.core.EasyEvent;

/**
 * 事件转发器
 * 
 * @author yanni
 *
 */
public class EasyEventHub {
	private final LinkedHashMap<Object, EasyEvent> events = new LinkedHashMap<Object, EasyEvent>();

	/**
	 * 注册事件
	 * 
	 * @param object
	 * @param easyEvent
	 */
	public final void registerEvent(Object object, EasyEvent easyEvent) {
		events.put(object, easyEvent);
	}

	/**
	 * 解除事件
	 * 
	 * @param object
	 */
	public final void unRegisterEvent(Object object) {
		if (events.containsKey(object)) {
			events.remove(object);
		}
	}

	/**
	 * 接触时间
	 * 
	 * @param easyEvent
	 */
	public final void unRegisterEvent(EasyEvent easyEvent) {
		if (events.containsValue(easyEvent)) {
			events.remove(easyEvent);
		}
	}

	/**
	 * 默认的事件转接器
	 */
	private static EasyEventHub easyEventHub;

	/**
	 * 
	 * /** 默认的事件转接器
	 * 
	 * @return
	 */
	public static EasyEventHub getDefault() {
		if (easyEventHub == null) {
			easyEventHub = new EasyEventHub();
		}
		return easyEventHub;
	}

	/**
	 * 开始执行一个事件
	 * 
	 * @param object
	 */
	public void start(Object object) {
		if (events.containsKey(object)) {
			events.get(object).start();
		}
	}
	
	public int getEventsCount(){
		return events.size();
	}
}
