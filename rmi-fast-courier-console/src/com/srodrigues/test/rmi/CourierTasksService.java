package com.srodrigues.test.rmi;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.srodrigues.test.Constants;
import com.srodrigues.test.rmi.CourierTasks;

public class CourierTasksService extends UnicastRemoteObject implements CourierTasks {

	protected CourierTasksService() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	private List<String> tasks = new ArrayList<String>();

	@Override
	public boolean add(String task) {
		System.err.println("add: " + task);
		return tasks.add(task);
	}

	@Override
	public boolean delete(String task) {
		System.err.println("remove: " + task);
		return tasks.remove(task);
	}

	@Override
	public List<String> get() {
		return tasks;
	}

	public static void main(String[] args) throws IOException, AlreadyBoundException {
		Registry registry = LocateRegistry.createRegistry(Constants.PORT);
		registry.bind(Constants.SERVICE, new CourierTasksService());
	}

}
