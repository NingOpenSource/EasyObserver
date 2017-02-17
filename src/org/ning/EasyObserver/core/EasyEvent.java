package org.ning.EasyObserver.core;

import java.util.LinkedHashMap;

import org.ning.EasyObserver.core.EasyAcceptor.Acceptor;
import org.ning.EasyObserver.core.EasyConvertor.Convertor;

/**
 * 
 * @author yanni
 *
 */
public abstract class EasyEvent {

	private Object tag;
	/**
	 * 描述
	 */
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 在运行之前的监听
	 */
	private final LinkedHashMap<Object,Convertor<Boolean>> onBeforeRunningConvertors=new LinkedHashMap<Object, Convertor<Boolean>>();
	/**
	 * 运行之后的监听
	 */
	private final LinkedHashMap<Object,Acceptor> onAfterRunningAcceptors=new LinkedHashMap<Object, EasyAcceptor.Acceptor>();
	public final LinkedHashMap<Object, Acceptor> getOnAfterRunningAcceptors() {
		return onAfterRunningAcceptors;
	}
	
	public final LinkedHashMap<Object, Convertor<Boolean>> getOnBeforeRunningConvertors() {
		return onBeforeRunningConvertors;
	}
	/**
	 * 开始执行事件
	 */
	public final void start(){
		for(Convertor<Boolean> convertor:onBeforeRunningConvertors.values()){
			if(!convertor.convert()){
				onStopRunning(this,convertor);
				return;
			}
		}
		onRunning(this);
		for(Acceptor acceptor:onAfterRunningAcceptors.values()){
			acceptor.accept();
		}
	}
	
	protected void onStopRunning(EasyEvent easyEvent,Convertor<Boolean> convertor){
		
	}
	/**
	 * 事件执行的主体
	 * @param easyEvent
	 */
	protected abstract void onRunning(EasyEvent easyEvent);
	
	
}
