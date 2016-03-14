package com.srodrigues.test.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Random;

import com.srodrigues.test.Constants;
import com.srodrigues.test.rmi.CourierTasks;

public class CourierTasksClient {
	
	public static void print(List<String> list){
		for (String task : list) {
			System.out.println(task );
		}
		
	}
	
	public static void main(String[] args) throws IOException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry(Constants.SERVER, Constants.PORT);
		final CourierTasks courier = (CourierTasks) registry.lookup(Constants.SERVICE);
		
		List<String> list = courier.get();
		print(list);
		
		courier.add( "batatas");
		list = courier.get();
		print(list);
		
		courier.add("bananas");
		list = courier.get();
		print(list);
		
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						courier.add("tbananas" + this.hashCode());
						try {
							Thread.sleep((int)(new Random().nextDouble()*100 ) );
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						List<String> list = courier.get();
						print(list);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}).run();
		}
		
		list = courier.get();
		print(list);

		
	}
}
