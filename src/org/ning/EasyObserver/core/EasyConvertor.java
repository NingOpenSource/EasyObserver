package org.ning.EasyObserver.core;
/**
 * 转换器
 * @author yanni
 *
 */
public class EasyConvertor {
	public static interface Convertor<O>{
		public O convert();
	}
	public static interface Convertor1<O,A>{
		public O convert(A a);
	}
	public static interface Convertor2<O,A,B>{
		public O convert(A a,B b);
	}
	public static interface Convertor3<O,A,B,C>{
		public O convert(A a,B b,C c);
	}
	public static interface Convertor4<O,A,B,C,D>{
		public O convert(A a,B b,C c,D d);
	}
	public static interface Convertor5<O,A,B,C,D,E>{
		public O convert(A a,B b,C c,D d,E e);
	}
	public static interface Convertor6<O,A,B,C,D,E,F>{
		public O convert(A a,B b,C c,D d,E e,F f);
	}
}
