package bean;

import java.io.Serializable;

import tdbd.TDBDDAO;

	public class ProcessBean implements Serializable {
		private int id;
		private int tdbd_id;
		private String process;
		private int difficulty;
		private int _order;
		private String tdbd;
		private int priority;
		private int user_id;


		//ゲッター

		public int getId() {
			return this.id;
		}

		public int getTdbd_id() {
			return this.tdbd_id;
		}

		public String getProcess() {
			return this.process;
		}

		public int get_order() {
			return this._order;
		}

		public int getDifficulty() {
			return this.difficulty;
		}

		public String getTDBD() {
			return this.tdbd;
		}

		public int getPriority() {
			return this.priority;
		}

		public int getUser_id() {
			return this.user_id;
		}

		//セッター

		public void setId() {
			TDBDDAO dao = new TDBDDAO();
			this.id = dao.searchId(getTDBD());
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setTdbd_id(int tdbd_id) {
			this.tdbd_id = tdbd_id;
		}

		public void setProcess(String process) {
			this.process = process;
		}

		public void setDifficulty(int difficulty){
			this.difficulty = difficulty;
		}

		public void set_order(int _order) {
			this._order = _order;
		}

		public void setTDBD(String tdbd) {
			this.tdbd = tdbd;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

	}

