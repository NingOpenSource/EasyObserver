package org.ning.EasyObserver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yanni
 *
 */
public class Observable<T> {

	private boolean changed = false;
	private final Map<String, Observer<T>> obs = new HashMap<>();

	/**
	 * 
	 * @param key
	 * @param o
	 */
	public final synchronized void registObserver(String key, Observer<T> o) {
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
		Collection<Observer<T>> ts;

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
		for (Observer<T> observer : ts) {
			observer.onUpdate(this, t);
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
