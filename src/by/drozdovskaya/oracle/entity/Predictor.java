package by.drozdovskaya.oracle.entity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import by.drozdovskaya.oracle.file.WorkWithFile;
import by.drozdovskaya.oracle.entity.Answer;
import by.drozdovskaya.oracle.exception.ClientInQueueException;

public class Predictor {
	private int countId;
	private PriorityQueue<Client> queueOfClients;
	private Map<Prediction, Queue<Answer>> mapOfAnswers;
	private Map<Date, Client> mapTimeClients; // учёт клиентов (HashMap<Date, Client>), которым оказывалась магическая
												// помощь и предоставляет отчет о данных клиентах;

	public Predictor() {
		queueOfClients = new PriorityQueue<>();
		mapOfAnswers = new LinkedHashMap<>();
		mapTimeClients = new HashMap<>();
	}

	public int getCountId() {
		return countId;
	}

	public void setCountId(int countId) {
		this.countId = countId;
	}

	// просмотреть список доступных предсказаний
	public void showPredictions() {

		Set<Prediction> predictions = mapOfAnswers.keySet();
		for (Prediction p : predictions) {
			System.out.println(p);
		}

	}
	
	public Prediction getPrediction(int choice) {

		Set<Prediction> predictions = mapOfAnswers.keySet();
		Iterator<Prediction> it = predictions.iterator();
		Prediction pr = new Prediction();
		int count = 0;
		while (it.hasNext()) {
			
			if (count+1 == choice) {
				pr = it.next();
			}else {
				it.next();
				count++;
			}
			
		}
		return pr;

	}

	// клиент может обратиться к предсказателю, указать цель гадания, получить
	// ответ.
	public Answer getAnswerOfPrediction(Prediction prediction) {

		return mapOfAnswers.get(prediction).poll();
		
	}

	public void addClientToQueue(Client client) {
		setCountId(countId+1);
		client.setIdInQueue(countId);
		this.queueOfClients.add(client);
	}

	public Answer wantPrediction(Prediction pr, Client client) throws ClientInQueueException {
		if (this.queueOfClients.peek().equals(client)) {
			if (!checkClientVisitsForWeek(client, client.getLastVisitDate())) {
				if (checkClientLimitInQueue(client.getLastVisitDate())) {
					this.mapTimeClients.put(client.getLastVisitDate(), this.queueOfClients.poll());
					return getAnswerOfPrediction(pr);
				} else
					
						throw new ClientInQueueException("Превышен лимит обращений в день");
					
			} else
				
					throw new ClientInQueueException("Превышен лимит обращений в неделю");
				
		}
		else {
			throw new ClientInQueueException("Не ваша очередь");
		}
			
		
	}

	public boolean checkClientLimitInQueue(Date date) {

		Set<Date> dates = mapTimeClients.keySet();
		int clientCounter = 0;
		Iterator<Date> it = dates.iterator();
		
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		Date startDay = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());	
	
		System.out.println(startDay);

		while (it.hasNext()) {
			Date currentDate = it.next();
			if (currentDate.before(date) && currentDate.after(startDay)) {
				clientCounter++;
			}
		}
		if (clientCounter > 10) {
			return false;
		} else {
			return true;
		}

	}

	public boolean checkClientVisitsForWeek(Client client, Date date) {

		Set<Date> dates = mapTimeClients.keySet();
		Set<Date> filteredDates = new HashSet<>();
		Iterator<Date> it = dates.iterator();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -6);
		Date lastweek = calendar.getTime();

		while (it.hasNext()) {
			Date currentDate = it.next();
			if (currentDate.before(date) && currentDate.after(lastweek)) {
				filteredDates.add(currentDate);
			}
		}
		Iterator<Date> filteredDatesIt = filteredDates.iterator();
		while (filteredDatesIt.hasNext()) {
			Date currentDate = filteredDatesIt.next();
			Client clientForDate = this.mapTimeClients.get(currentDate);
			if (clientForDate.equals(client)) {
				return true;
			}
		}
		return false;

	}

	public Queue<Answer> initAnswersInQueue(Prediction type) {
		WorkWithFile f = new WorkWithFile();
		Queue<Answer> answers = new LinkedList<>();
		try {
			List<String> lines = f.readFromFile(type.getTypeOfPrediction());
			for (int i = 0; i < lines.size(); i++) {
				answers.add(new Answer(lines.get(i)));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return answers;
	}

	public void initMapOfAnswers() {
		
		mapOfAnswers.put(new Prediction("Love"), this.initAnswersInQueue(new Prediction("Love")));
		mapOfAnswers.put(new Prediction("Life"), this.initAnswersInQueue(new Prediction("Life")));
		mapOfAnswers.put(new Prediction("Bussiness"), this.initAnswersInQueue(new Prediction("Bussiness")));

	}

	public void initClientsInQueue() {
		addClientToQueue((new Client("Vica")));
		addClientToQueue((new Client("Igor")));
		addClientToQueue((new Client("Lena")));
		addClientToQueue((new Client("Vica")));
		addClientToQueue((new Client("Igor")));
		addClientToQueue((new Client("Lena")));
		addClientToQueue((new Client("Vica")));
		addClientToQueue((new Client("Igor")));
		addClientToQueue((new Client("Lena")));
	}

	public void showMapTimeClient() {
		Set<Date> dates = this.mapTimeClients.keySet();
		for (Date d : dates) {
			System.out.println("Date " + d + "  " + this.mapTimeClients.get(d));
		}
	}

	public void showQueueClients() {
		System.out.println("Queue of Clients");
		Iterator<Client> it = this.queueOfClients.iterator();
		int i = 0;
		while (it.hasNext()) {
			Client client = (Client) it.next();
			System.out.println("id client in queue = " + client.getIdInQueue() + "  " + client.getName());
			i++;
		}
	}

	public void deleteClientFromQueue(int idclient) {
		Iterator<Client> it = this.queueOfClients.iterator();
		Client deleteClient = new Client();
		while (it.hasNext()) {
			Client clientIt = (Client)it.next();
			if (clientIt.getIdInQueue() == idclient) {
				deleteClient = clientIt;

			}
		}
		this.queueOfClients.remove(deleteClient);
		}

	

}
