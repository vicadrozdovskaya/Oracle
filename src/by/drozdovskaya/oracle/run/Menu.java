package by.drozdovskaya.oracle.run;

import java.util.Scanner;

public class Menu {

	public void startMenu() {
		System.out.println("1 - ����������� ������ ��������� ������������ " + "\n"
				+ "2 - ����� ���������� � �������������, ������� ���� �������, �������� ����� " + "\n"
				+ "3 - ������������� ����� � ������ ��������, ������� ����������� ���������� ������ �  " + "\n"
				+ "4 - �������� ������ ��������, ���������� � ������� �� ���� " + "\n"
				+ "5 - ������� ������� �� ������ �������� " + "\n" + "6 - ����� �� ��������� \n");
	}

	public void contineu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to contineu? Y(YES)/N(NO)" + "\n");
		String str = sc.next();
		switch (str) {
		case "N":
			System.exit(0);
		}
	}

	public void exit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you really want to exit? Y(YES)/N(NO)" + "\n");
		String str = sc.next();
		switch (str) {
		case "Y":
			System.out.println("Thank you");
			System.exit(0);
		}

	}

}
