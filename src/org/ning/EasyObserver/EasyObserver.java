package org.ning.EasyObserver;

public interface EasyObserver<T> {
	void onUpdate(EasyObservable<T> o);
}
