# EasyObserver
1,完成了基本的方法
2,加入gradle支持

## 下载地址
[![](https://jitpack.io/v/NingOpenSource/EasyObserver.svg)](https://jitpack.io/#NingOpenSource/EasyObserver)

## 例子
```
package org.ning.EasyObserver.demo;

import org.ning.EasyObserver.EasyObservable;
import org.ning.EasyObserver.EasyObserver;

public class MainDemo {
	public static void main(String[] args) {
		EasyObservable<Integer> easyObserver=new EasyObservable<Integer>() {
			private Integer money=0;
			
			@Override
			public Integer getObject() {
				// TODO Auto-generated method stub
				return money;
			}

			@Override
			protected void onUpdate(Integer arg0) {
				// TODO Auto-generated method stub
				money=arg0;
			}
		};
		new Thread(){
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("即将改变金额："+easyObserver.getObject());
				easyObserver.update(20);
			};
		}.start();
		new Thread(){
			public void run() {
				try {
					sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("即将改变金额："+easyObserver.getObject());
				easyObserver.update(50);
			};
		}.start();
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				for(int i=0;i<20;i++){
					int j=i;
					easyObserver.registObserver(getName()+j, new EasyObserver<Integer>() {

						@Override
						public void onUpdate(EasyObservable<Integer> arg0) {
							// TODO Auto-generated method stub
							System.out.println("【"+j+"】改变金额："+arg0.getObject());
						}
					});
				}
				
			}
		}.start();
	}
}

```
