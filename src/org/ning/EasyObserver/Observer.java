package org.ning.EasyObserver;

/**
 * 
 * @author yanni
 *
 */
public interface Observer<T>{
	void onUpdate(Observable<T> o, T t);
}
