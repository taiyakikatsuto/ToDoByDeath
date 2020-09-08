package bean;

import java.io.Serializable;

import user.UserDAO;

	public class UserBean implements Serializable {
		private int id;
		private String name;
		private String email;
		private String password;
		private int age;

		public int getId() {
			setId();
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		public String getEmail() {
			return this.email;
		}

		public String getPassword() {
			return this.password;
		}

		public int getAge() {
			UserDAO dao = new UserDAO();
			return dao.getAge(this.id);
		}


		public void setId() {
			UserDAO dao = new UserDAO();
			this.id = dao.searchByName(this.name);
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setPassword(String password){
			this.password = password;
		}
	}

}
