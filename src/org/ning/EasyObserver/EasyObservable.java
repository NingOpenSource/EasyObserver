package org.ning.EasyObserver;


/**
 * 
 * @author yanni
 *
 * @param <T>
 */
public abstract class EasyObservable<T> extends Observable<T>{
	/**
	 * 
	 * @return 被觀察的值
	 */
	public abstract T getObject();
	/**
	 * 
	 * @param t 即將輸入的值
	 */
	protected abstract void onUpdate(T t);
	/**
	 * 
	 * @param t 即將輸入的值
	 */
	public final void update(T t){
		onUpdate(t);
		setChanged();
		notifyObservers(t);
	}
	
	
	
}
