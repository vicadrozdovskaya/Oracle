package by.drozdovskaya.oracle.run;

import java.util.Date;

import by.drozdovskaya.oracle.entity.Client;
import by.drozdovskaya.oracle.entity.Prediction;
import by.drozdovskaya.oracle.entity.PredictionResult;
import by.drozdovskaya.oracle.entity.Predictor;

public class Main {

	public static void main(String[] args) {
		
		Predictor predictor = new Predictor();
		predictor.initMapOfAnswers();
		predictor.showPredictions();
		Client client = new Client();
		PredictionResult predictionResult = predictor.getAnswerOfPrediction(new Prediction("Love"));
		client.setHistoryOfPredictiond(new Date(), predictionResult);
		client.showHistoryOfPredictions();
		
		

	}

}
