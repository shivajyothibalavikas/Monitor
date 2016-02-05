package com.kenscio;

public class SFTPMonitor {

	public static void main(String[] args) {
		Run run = new Run();
		Thread t1 = new Thread(run);
		t1.start();
	}

}
