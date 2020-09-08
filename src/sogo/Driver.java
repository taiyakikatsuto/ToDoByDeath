package sogo;

import user.UserDAO;

public class Driver{

	public static void main(String[] args) {
		ageTest();
	}

	public static void ageTest() {
		UserDAO dao = new UserDAO();
		System.out.println(dao.getAge(5));
	}


}