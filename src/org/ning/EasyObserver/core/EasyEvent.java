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
	
	public final void start(){
		for(Convertor<Boolean> convertor:onBeforeRunningConvertors.values()){
			if(!convertor.convert()){
				onStopRunning(convertor);
				return;
			}
		}
		onRunning();
		for(Acceptor acceptor:onAfterRunningAcceptors.values()){
			acceptor.accept();
		}
	}
	
	protected void onStopRunning(Convertor<Boolean> convertor){
		
	}
	
	protected abstract void onRunning();
	
	
}
