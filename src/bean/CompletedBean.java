package bean;

import java.io.Serializable;

import completed.CompletedDAO;

	public class CompletedBean implements Serializable {
		private int id;
		private int user_id;
		private String dream;
		private int age;

		public int getId() {
			setId();
			return this.id;
		}

		public int getUser_id() {
			return this.user_id;
		}

		public String getDream() {
			return this.dream;
		}

		public int getAge() {
			return this.age;
		}

		public void setId() {
			CompletedDAO dao = new CompletedDAO();
			this.id = dao.searchId(getDream());
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public void setDream(String dream) {
			this.dream = dream;
		}

		public void setAge(int age){
			this.age = age;
		}
	}


