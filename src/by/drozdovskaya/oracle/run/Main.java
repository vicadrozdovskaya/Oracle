package by.drozdovskaya.oracle.run;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

import by.drozdovskaya.oracle.entity.Answer;
import by.drozdovskaya.oracle.entity.Client;
import by.drozdovskaya.oracle.entity.Prediction;
import by.drozdovskaya.oracle.entity.PredictionResult;
import by.drozdovskaya.oracle.entity.Predictor;
import by.drozdovskaya.oracle.exception.ClientInQueueException;

public class Main {

	public static void main(String[] args) throws ClientInQueueException {

		Menu menu = new Menu();
		Read read = new Read();
		Date date = new Date();
		Predictor predictor = new Predictor();
		predictor.initMapOfAnswers();
		predictor.initClientsInQueue();
		Prediction prediction = new Prediction();
		Client client = new Client();
		PredictionResult predictionResult = new PredictionResult();
		do {

			menu.startMenu();

			int choice = read.readNumber();
			switch (choice) {

			case 1:
				predictor.showPredictions();
				menu.contineu();
				break;
			case 2:
				System.out.println("Введите имя");
				String name = read.readString();
				System.out.println("Выбирите тип предсказания");
				predictor.showPredictions();
				int choice2 = read.readNumber();
				prediction = predictor.getPrediction(choice2);
				client.setName(name);
				client.setLastVisitDate(new Date());
				predictor.addClientToQueue(client);
				try {
					Answer answer = predictor.wantPrediction(prediction, client);

					predictionResult.setPredictionResult(prediction, answer);

					client.setHistoryOfPredictiond(date, predictionResult);
					client.showHistoryOfPredictions();
				} catch (ClientInQueueException e) {
					System.out.println(e.getMessage());
				}

				menu.contineu();
				break;
			case 3:

				System.out.println("отчет о данных клиентах, которым оказывалась магическая помощь ");
				predictor.showMapTimeClient();
				menu.contineu();

				break;
			case 4:

				System.out.println("списка клиентов, записанных в очередь на приём ");
				predictor.showQueueClients();
				menu.contineu();
				break;
			case 5:
				System.out.println("Удаление клиента из очереди по id");
				predictor.showQueueClients();
				System.out.println("Введите id клиента, которого нужно удалить");
				predictor.deleteClientFromQueue(read.readNumber());
				menu.contineu();
			case 6:
				menu.exit();

			}
		} while (true);

	}

}
