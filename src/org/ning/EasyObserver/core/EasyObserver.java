package org.ning.EasyObserver.core;

import org.ning.EasyObserver.EasyObservable;

public interface EasyObserver<T> {
	void onUpdate(EasyObservable<T> o);
}
