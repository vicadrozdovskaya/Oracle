package by.drozdovskaya.oracle.entity;

public class Prediction {

	private String typeOfPrediction;

	public Prediction() {

	}

	public Prediction(String typeOfPrediction) {
		this.typeOfPrediction = typeOfPrediction;
	}

	public String getTypeOfPrediction() {
		return typeOfPrediction;
	}

	public void setTypeOfPrediction(String typeOfPrediction) {
		this.typeOfPrediction = typeOfPrediction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeOfPrediction == null) ? 0 : typeOfPrediction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prediction other = (Prediction) obj;
		if (typeOfPrediction == null) {
			if (other.typeOfPrediction != null)
				return false;
		} else if (!typeOfPrediction.equals(other.typeOfPrediction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prediction: typeOfPrediction=" + typeOfPrediction;
	}

}
