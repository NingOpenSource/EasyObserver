package org.ning.EasyObserver.core;
/**
 * 接收器
 * @author yanni
 *
 */
public class EasyAcceptor {
	public interface Acceptor{
		public void accept();
	}
	public interface Acceptor1<A>{
		public void accept(A a);
	}
	public interface Acceptor2<A,B>{
		public void accept(A a,B b);
	}
	public interface Acceptor3<A,B,C>{
		public void accept(A a,B b,C c);
	}
	public interface Acceptor4<A,B,C,D>{
		public void accept(A a,B b,C c,D d);
	}
	public interface Acceptor5<A,B,C,D,E>{
		public void accept(A a,B b,C c,D d,E e);
	}
	public interface Acceptor6<A,B,C,D,E,F>{
		public void accept(A a,B b,C c,D d,E e,F f);
	}
}
