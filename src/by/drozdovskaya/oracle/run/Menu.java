package by.drozdovskaya.oracle.run;

import java.util.Scanner;

public class Menu {

	public void startMenu() {
		System.out.println("1 - просмотреть список доступных предсказаний " + "\n"
				+ "2 - может обратиться к предсказателю, указать цель гадания, получить ответ " + "\n"
				+ "3 - предоставляет отчет о данных клиентах, которым оказывалась магическая помощь и  " + "\n"
				+ "4 - просмотр списка клиентов, записанных в очередь на приём " + "\n"
				+ "5 - удалить клиента из списка ожидания " + "\n" + "6 - Выход из программы \n");
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
