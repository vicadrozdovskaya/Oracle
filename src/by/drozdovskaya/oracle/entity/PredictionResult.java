package by.drozdovskaya.oracle.entity;

import java.util.HashMap;
import java.util.Map;

public class PredictionResult {
	
	private Map <Prediction, Answer> mapOfPredictionResult;
	
	public PredictionResult() {
		
		this.mapOfPredictionResult = new HashMap<>();
	}
	
	public void setPredictionResult (Prediction prediction, Answer answer ) {
		this.mapOfPredictionResult.put(prediction, answer);
	}

	public Map<Prediction, Answer> getMapOfPredictionResult() {
		return mapOfPredictionResult;
	}

	public void setMapOfPredictionResult(Map<Prediction, Answer> mapOfPredictionResult) {
		this.mapOfPredictionResult = mapOfPredictionResult;
	}

	@Override
	public String toString() {
		return "PredictionResult: mapOfPredictionResult=" + mapOfPredictionResult;
	}
	
	

}
