package org.ning.EasyObserver;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.ning.EasyObserver.core.EasyAcceptor.Acceptor1;
/**
 * 
 * @author yanni
 *
 * @param <K>
 * @param <V>
 */
public abstract class EasyObservableMap<K, V> extends EasyObservable<Map<K,V>> implements Map<K, V>{

	
	@Override
	public final int size() {
		// TODO Auto-generated method stub
		return getObject().size();
	}

	@Override
	public final boolean isEmpty() {
		// TODO Auto-generated method stub
		return getObject().isEmpty();
	}

	@Override
	public final boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return getObject().containsKey(key);
	}

	@Override
	public final boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return getObject().containsValue(value);
	}

	@Override
	public final V get(Object key) {
		// TODO Auto-generated method stub
		return getObject().get(key);
	}

	@Override
	public final V put(final K key,final V value) {
		// TODO Auto-generated method stub
		final V v;
		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return null;
		v=getObject().put(key, value);
		setChanged();
		notifyObservers();
		return v;
	}

	
	
	@Override
	public final V remove(Object key) {
		// TODO Auto-generated method stub
		final V v;
		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return null;
		v=getObject().remove(key);
		setChanged();
		notifyObservers();
		return v;
	}

	@Override
	public final void putAll(final Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		update(new Acceptor1<Map<K,V>>() {

			@Override
			public void accept(Map<K, V> a) {
				// TODO Auto-generated method stub
				getObject().putAll(m);
			}
		});
	}

	@Override
	public final void clear() {
		// TODO Auto-generated method stub
		update(new Acceptor1<Map<K,V>>() {

			@Override
			public void accept(Map<K, V> a) {
				// TODO Auto-generated method stub
				getObject().clear();
			}
		});
	}

	@Override
	public final Set<K> keySet() {
		// TODO Auto-generated method stub
		return getObject().keySet();
	}

	@Override
	public final Collection<V> values() {
		// TODO Auto-generated method stub
		return getObject().values();
	}

	@Override
	public final Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return getObject().entrySet();
	}
	
}
