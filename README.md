# EasyObserver
1,完成了基本的方法
2,加入gradle支持
3,支持java1.6以上版本

## 下载地址
[![](https://jitpack.io/v/NingOpenSource/EasyObserver.svg)](https://jitpack.io/#NingOpenSource/EasyObserver)

## 例子
```
package org.ning.EasyObserver.demo;

import org.ning.EasyObserver.EasyEventHub;
import org.ning.EasyObserver.EasyObservable;
import org.ning.EasyObserver.core.EasyAcceptor.Acceptor;
import org.ning.EasyObserver.core.EasyConvertor.Convertor;
import org.ning.EasyObserver.core.EasyConvertor.Convertor1;
import org.ning.EasyObserver.core.EasyEvent;
import org.ning.EasyObserver.core.EasyObserver;

public class MainDemo {
	private static Integer money = 0;

	public static void main(String[] args) {
		EasyObservable<Integer> easyObserver = new EasyObservable<Integer>() {

			@Override
			public Integer getObject() {
				// TODO Auto-generated method stub
				return money;
			}

			@Override
			protected void onUpdate(Integer arg0) {
				// TODO Auto-generated method stub
				money = arg0;
			}
		};
		EasyEvent first = new EasyEvent() {

			@Override
			protected void onRunning(EasyEvent arg0) {
				// TODO Auto-generated method stub
				easyObserver.update(20);
			}
		};
		EasyEvent second = new EasyEvent() {

			@Override
			protected void onRunning(EasyEvent arg0) {
				// TODO Auto-generated method stub
				easyObserver.update(50);
			}
		};

		EasyEventHub.getDefault().registerEvent("0+20", first);
		EasyEventHub.getDefault().registerEvent("20+30", second);
		first.getOnBeforeRunningConvertors().put("cc", new Convertor<Boolean>() {

			@Override
			public Boolean convert() {
				// TODO Auto-generated method stub
				System.out.println();
				System.out.println();
				System.out.println("有人将会给我20块");
				return true;
			}
		});
		first.getOnAfterRunningAcceptors().put("sd", new Acceptor() {

			@Override
			public void accept() {
				// TODO Auto-generated method stub
				System.out.println("现在的钱包有￥："+easyObserver.getObject());
			}
		});
		second.getOnBeforeRunningConvertors().put("sfdf", new Convertor<Boolean>() {

			@Override
			public Boolean convert() {
				System.out.println();
				System.out.println();
				System.out.println("有人将会给我30块");
				return true;
			}
		});
		second.getOnAfterRunningAcceptors().put("ssd", new Acceptor() {

			@Override
			public void accept() {
				// TODO Auto-generated method stub
				System.out.println("现在的钱包有￥："+easyObserver.getObject());
			}
		});
		easyObserver.getBeforeUpdateConvertors().put("init", new Convertor1<Boolean, EasyObservable<Integer>>() {
			
			@Override
			public Boolean convert(EasyObservable<Integer> arg0) {
				// TODO Auto-generated method stub
				System.out.println("======现在的钱包有￥："+arg0.getObject()+"============");
				return true;
			}
		});
		easyObserver.registObserver("getget", new EasyObserver<Integer>() {

			@Override
			public void accept(EasyObservable<Integer> arg0) {
				// TODO Auto-generated method stub
				System.out.println("我收到了￥：" + arg0.getObject());
			}
		});
		/**
		 * 在其他访问不到变量的地方执行事件
		 */
		processEvent();
	}

	private static void processEvent() {
		new Thread() {
			public void run() {
				new Thread() {
					public void run() {
						try {
							sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						EasyEventHub.getDefault().start("0+20");
					};
				}.start();

				new Thread() {
					public void run() {
						try {
							sleep(7000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						EasyEventHub.getDefault().start("20+30");b 
					};
				}.start();

			};
		}.start();
	}
}


```
输出结果
```


有人将会给我20块
======现在的钱包有￥：0============
我收到了￥：20
现在的钱包有￥：20


有人将会给我30块
======现在的钱包有￥：20============
我收到了￥：50
现在的钱包有￥：50

```

