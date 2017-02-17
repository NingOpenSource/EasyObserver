package org.ning.EasyObserver;

import java.util.Collection;
import java.util.HashMap;

import org.ning.EasyObserver.core.EasyConvertor;
import org.ning.EasyObserver.core.EasyConvertor.Convertor1;
import org.ning.EasyObserver.core.EasyObserver;

/**
 * 
 * @author yanni
 *
 * @param <T>
 */
public abstract class EasyObservable<T> {
	/**
	 * 
	 * @return 被觀察的值
	 */
	public abstract T getObject();

	/**
	 * 
	 * @param t
	 *            即將輸入的值
	 */
	protected abstract void onUpdate(T t);

	/**
	 * 更新过程被终止的 时候会调用
	 * 
	 * @param observable
	 * @param convertor
	 */
	protected void onStopUpdate(EasyObservable<T> observable, Convertor1<Boolean, EasyObservable<T>> convertor) {

	}

	/**
	 * 
	 * @param t
	 *            即將輸入的值
	 */
	public final void update(T t) {
		/**
		 * 防止相等对象被回收
		 */
		if (getObject() != t) {
			/**
			 * b为false默认不会阻止更新操作
			 */
			boolean b = false;
			for (Convertor1<Boolean, EasyObservable<T>> convertor : beforeUpdateConvertors.values()) {
				boolean a = convertor.convert(this);
				a = !a;
				if (a)
					onStopUpdate(this, convertor);
				b |= a;
			}
			/**
			 * b为false默认不会阻止更新操作
			 */
			if (b)
				return;
			onUpdate(t);
		}
		setChanged();
		notifyObservers(t);
	}

	private boolean changed = false;
	private final HashMap<Object, EasyObserver<T>> obs = new HashMap<Object, EasyObserver<T>>();
	private final HashMap<Object, Convertor1<Boolean, EasyObservable<T>>> beforeUpdateConvertors = new HashMap<Object, EasyConvertor.Convertor1<Boolean, EasyObservable<T>>>();

	public HashMap<Object, Convertor1<Boolean, EasyObservable<T>>> getBeforeUpdateConvertors() {
		return beforeUpdateConvertors;
	}

	/**
	 * 
	 * @param key
	 * @param o
	 */
	public final synchronized void registObserver(Object key, EasyObserver<T> o) {
		if (o == null)
			throw new NullPointerException();
		if (!obs.containsKey(key)) {
			obs.put(key, o);
		}
	}

	/**
	 * 
	 * @param key
	 */
	public final synchronized void removeObserver(String key) {
		if (obs.containsKey(key)) {
			obs.remove(key);
		}
	}

	/**
	 * 
	 */
	public final synchronized void removeAllObservers() {
		obs.clear();
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	public void notifyObservers(T t) {
		/*
		 * a temporary array buffer, used as a snapshot of the state of current
		 * Observers.
		 */
		Collection<EasyObserver<T>> ts;

		synchronized (this) {
			/*
			 * We don't want the Observer doing callbacks into arbitrary code
			 * while holding its own Monitor. The code where we extract each
			 * Observable from the Vector and store the state of the Observer
			 * needs synchronization, but notifying observers does not (should
			 * not). The worst result of any potential race-condition here is
			 * that: 1) a newly-added Observer will miss a notification in
			 * progress 2) a recently unregistered Observer will be wrongly
			 * notified when it doesn't care
			 */
			if (!changed)
				return;
			ts = obs.values();
			clearChanged();
		}
		for (EasyObserver<T> observer : ts) {
			observer.accept(this);
		}
	}

	/**
	 * Marks this <tt>Observable</tt> object as having been changed; the
	 * <tt>hasChanged</tt> method will now return <tt>true</tt>.
	 */
	protected final synchronized void setChanged() {
		changed = true;
	}

	/**
	 * Indicates that this object has no longer changed, or that it has already
	 * notified all of its observers of its most recent change, so that the
	 * <tt>hasChanged</tt> method will now return <tt>false</tt>. This method is
	 * called automatically by the <code>notifyObservers</code> methods.
	 *
	 * @see java.util.Observable#notifyObservers()
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	protected final synchronized void clearChanged() {
		changed = false;
	}

	/**
	 * Tests if this object has changed.
	 *
	 * @return <code>true</code> if and only if the <code>setChanged</code>
	 *         method has been called more recently than the
	 *         <code>clearChanged</code> method on this object;
	 *         <code>false</code> otherwise.
	 * @see java.util.Observable#clearChanged()
	 * @see java.util.Observable#setChanged()
	 */
	public final synchronized boolean hasChanged() {
		return changed;
	}

	/**
	 * Returns the number of observers of this <tt>Observable</tt> object.
	 *
	 * @return the number of observers of this object.
	 */
	public final synchronized int countObservers() {
		return obs.size();
	}

}
