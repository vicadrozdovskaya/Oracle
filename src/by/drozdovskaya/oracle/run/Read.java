package by.drozdovskaya.oracle.run;

import java.util.Scanner;

public class Read {

	Scanner sc;

	Read() {
		this.sc = new Scanner(System.in);
	}

	public int readNumber() {
		System.out.println("Enter number " + "\n");
		return sc.nextInt();
	}

	public String readString() {

		return sc.next();
	}

}
