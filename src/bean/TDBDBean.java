package bean;

import java.io.Serializable;

import tdbd.TDBDDAO;

public class TDBDBean implements Serializable {
	private int id;
	private String tdbd;
	private int priority;
	private int difficulty;


	//ゲッター
	public int getId() {
		return this.id;
	}

	public String getTDBD() {
		return this.tdbd;
	}

	public int getPriority() {
		return this.priority;
	}

	public int getDifficulty() {
		return this.difficulty;
	}



	//セッター
	public void setId() {
		TDBDDAO dao = new TDBDDAO();
		this.id = dao.searchId(getTDBD());
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTDBD(String tdbd) {
		this.tdbd = tdbd;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
