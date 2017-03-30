package org.ning.EasyObserver;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.ning.EasyObserver.core.EasyAcceptor.Acceptor1;
/**
 * 
 * @author yanni
 *
 * @param <T>
 */
public abstract class EasyObservableList<T> extends EasyObservable<List<T>> implements List<T> {

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
	public final boolean contains(Object o) {
		// TODO Auto-generated method stub
		return getObject().contains(o);
	}

	@Override
	public final Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return getObject().iterator();
	}

	@Override
	public final Object[] toArray() {
		// TODO Auto-generated method stub
		return getObject().toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public final <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return getObject().toArray(a);
	}

	@Override
	public final boolean add(T e) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().add(e);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final boolean remove(Object o) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().remove(o);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return getObject().containsAll(c);
	}

	@Override
	public final boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().addAll(c);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().addAll(index, c);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().removeAll(c);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean b = false;

		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return b;
		b = getObject().retainAll(c);
		setChanged();
		notifyObservers();
		return b;
	}

	@Override
	public final void clear() {
		// TODO Auto-generated method stub
		update(new Acceptor1<List<T>>() {

			@Override
			public void accept(List<T> a) {
				// TODO Auto-generated method stub
				getObject().clear();
			}
		});
	}

	@Override
	public final T get(int index) {
		// TODO Auto-generated method stub
		return getObject().get(index);
	}

	@Override
	public final T set(int index, T element) {
		// TODO Auto-generated method stub
		T t;
		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return null;
		t = getObject().set(index, element);
		setChanged();
		notifyObservers();
		return t;
	}

	@Override
	public final void add(final int index, final T element) {
		// TODO Auto-generated method stub
		update(new Acceptor1<List<T>>() {

			@Override
			public void accept(List<T> a) {
				// TODO Auto-generated method stub
				getObject().add(index, element);
			}
		});
	}

	@Override
	public final T remove(int index) {
		// TODO Auto-generated method stub
		T t;
		/**
		 * beforeUpdate为false默认不会阻止更新操作
		 */
		if (beforeUpdate(getObject()))
			return null;
		t = getObject().remove(index);
		setChanged();
		notifyObservers();
		return t;
	}

	@Override
	public final int indexOf(Object o) {
		// TODO Auto-generated method stub
		return getObject().indexOf(o);
	}

	@Override
	public final int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return getObject().lastIndexOf(o);
	}

	@Override
	public final ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return getObject().listIterator();
	}

	@Override
	public final ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return getObject().listIterator(index);
	}

	@Override
	public final List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return getObject().subList(fromIndex, toIndex);
	}

}
