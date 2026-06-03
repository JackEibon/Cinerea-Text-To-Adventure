package models;

public class Score {

	private int idScore;
	private int idUser;
	private int bestScore;
	private int lastScore;
	
	private String nickname;

	public Score() {

	}

	public Score(int idUser, int bestScore, int lastScore) {
		this.idUser = idUser;
		this.bestScore = bestScore;
		this.lastScore = lastScore;
	}

	public Score(int idScore, int idUser, int bestScore, int lastScore) {
		this.idScore = idScore;
		this.idUser = idUser;
		this.bestScore = bestScore;
		this.lastScore = lastScore;
	}

	public int getIdScore() {
		return idScore;
	}

	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}