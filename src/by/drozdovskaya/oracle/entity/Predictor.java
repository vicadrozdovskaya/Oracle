package by.drozdovskaya.oracle.entity;

import java.io.IOException;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import by.drozdovskaya.oracle.file.WorkWithFile;
import by.drozdovskaya.oracle.entity.Answer;

public class Predictor {

	private Queue<Client> queueOfClients;
	private Map<Prediction, Queue<Answer>> mapOfAnswers;
	private Map<Date, Client> mapTimeClients;

	public Predictor() {
		queueOfClients = new PriorityQueue<>();
		mapOfAnswers = new LinkedHashMap<>();
		mapTimeClients = new HashMap<>();
	}

	// просмотреть список доступных предсказаний
	public void showPredictions() {

		Set<Prediction> predictions = mapOfAnswers.keySet();
		for (Prediction p : predictions) {
			System.out.println(p);
		}

	}

	// клиент может обратиться к предсказателю, указать цель гадания, получить
	// ответ.
	public PredictionResult getAnswerOfPrediction(Prediction prediction) {

		// Date date = new Date();
		// Set<Date> dates = mapTimeClients.keySet();
		// dates.contains(o)
		//
		// mapTimeClients.put(new Date, value);
		PredictionResult predictionResult = new PredictionResult();
		Answer answer = new Answer();
		answer = mapOfAnswers.get(prediction).poll();
		predictionResult.setPredictionResult(prediction, answer);
		return predictionResult;
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

		Random random = new Random();
		int randomType = random.nextInt(10) + 1;
		// System.out.println(randomType);

		//if (randomType < 5) {

			mapOfAnswers.put(new Prediction("Love"), this.initAnswersInQueue(new Prediction("Love")));

	//	} else if (randomType >= 5 && randomType < 8) {
			mapOfAnswers.put(new Prediction("Life"), this.initAnswersInQueue(new Prediction("Life")));

		//} else if (randomType >= 8 && randomType < 11) {
			mapOfAnswers.put(new Prediction("Bussiness"), this.initAnswersInQueue(new Prediction("Bussiness")));

		//}
	}

}
