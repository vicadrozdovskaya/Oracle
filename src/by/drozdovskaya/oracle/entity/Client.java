package by.drozdovskaya.oracle.entity;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Client {
	

	private Map <Date, PredictionResult> historyOfPredictions;
	
	public Client() {
		
		this.historyOfPredictions = new TreeMap<>();
	}

	public Client(Map<Date, PredictionResult> historyOfPredictiond) {
		this.historyOfPredictions = historyOfPredictiond;
	}

	public Map<Date, PredictionResult> getHistoryOfPredictiond() {
		return historyOfPredictions;
	}

	public void setHistoryOfPredictiond(Date date, PredictionResult predictionResult) {
		this.historyOfPredictions.put(date, predictionResult);
	}
	
	public void showHistoryOfPredictions() {
		System.out.println("HistoryOf Predictions");
		System.out.println(historyOfPredictions);
	}
	
	
	

}
