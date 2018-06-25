package by.drozdovskaya.oracle.entity;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Client implements Comparable<Client> {

	private String name;
	private int idInQueue;
	private Date lastVisitDate;
	private Map<Date, PredictionResult> historyOfPredictions;

	public Client() {

		this.name = "";
		this.idInQueue = 0;
		this.historyOfPredictions = new TreeMap<>();
		this.lastVisitDate = new Date();
	}

	public Client(String name, Map<Date, PredictionResult> historyOfPredictiond) {
		this.name = name;
		this.historyOfPredictions = historyOfPredictiond;
	}

	public Client(String name) {
		this.name = name;
		this.lastVisitDate = new Date();
		this.historyOfPredictions = new TreeMap<>();
	}

	public Map<Date, PredictionResult> getHistoryOfPredictiond() {
		return historyOfPredictions;
	}

	public void setHistoryOfPredictiond(Date date, PredictionResult predictionResult) {
		this.historyOfPredictions.put(date, predictionResult);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getIdInQueue() {
		return idInQueue;
	}

	public void setIdInQueue(int idInQueue) {
		this.idInQueue = idInQueue;
	}

	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public void showHistoryOfPredictions() {
		System.out.println("HistoryOf Predictions");
		System.out.println(historyOfPredictions);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historyOfPredictions == null) ? 0 : historyOfPredictions.hashCode());
		result = prime * result + idInQueue;
		result = prime * result + ((lastVisitDate == null) ? 0 : lastVisitDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Client other = (Client) obj;
		if (historyOfPredictions == null) {
			if (other.historyOfPredictions != null)
				return false;
		} else if (!historyOfPredictions.equals(other.historyOfPredictions))
			return false;
		if (idInQueue != other.idInQueue)
			return false;
		if (lastVisitDate == null) {
			if (other.lastVisitDate != null)
				return false;
		} else if (!lastVisitDate.equals(other.lastVisitDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client: name=" + name + ", idInQueue=" + idInQueue  + ", lastVisitDate=" + lastVisitDate + ", historyOfPredictions="
				+ historyOfPredictions;
	}

	@Override
	public int compareTo(Client client) {
		if (this.lastVisitDate.equals(client.getLastVisitDate())) {
			return 0;
		} else if (this.lastVisitDate.before(client.getLastVisitDate())) {
			return 1;
		} else {
			return -1;
		}
	}

}
