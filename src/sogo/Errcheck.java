package sogo;

import java.util.ArrayList;

import bean.ProcessBean;
import tdbd.TDBDDAO;
import user.UserDAO;

public class Errcheck {



	//TDBDテスト

	public boolean Test(String tdbd, int priority, int difficulty) {
		if (notNull(tdbd, priority,difficulty) && notTooLong(tdbd)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean notNull(String tdbd, int priority, int difficulty) {
		if(tdbd != "" && priority != 0 && difficulty != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean notTooLong(String tdbd) {
		if(tdbd.length() <= 30) {
			return true;
		} else {
			return false;
		}
	}




//Userテスト

	public boolean Test(String name, String email, String password) {
		if (userNotNull(name, email, password) && userNotTooLong(name, email, password) && uniqueUser(name) && uniqueEmail(email)) {
			return true;
		} else {
			return false;
		}
	}


	public boolean userNotNull(String name, String email, String password) {
		if(name != "" && email != "" && password != "") {
			return true;
		} else {
			return false;
		}
	}

	public boolean userNotTooLong(String name, String email, String password) {
		if(name.length() <= 20 && password.length() <= 20) {
			return true;
		} else {
			return false;
		}
	}

	public boolean uniqueUser(String name) {
		UserDAO dao = new UserDAO();
		if(dao.searchByName(name) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean uniqueEmail(String email) {
		UserDAO dao = new UserDAO();
		if(dao.searchByEmail(email) == 0) {
			return true;
		} else {
			return false;
		}
	}


	//Processテスト

	public boolean Test(String process, int difficulty, int tdbd_id, ArrayList<ProcessBean> list) {
		if (processNotNull(process, difficulty) && processNotTooLong(process) && difficultyTest(difficulty, tdbd_id ,list)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean udTest(String process, int difficulty,int befor_difficulty) {
		if (processNotNull(process, difficulty) && processNotTooLong(process) && difficultyTest(difficulty, befor_difficulty)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean processNotNull(String process, int difficulty) {
		if(process != "" && difficulty != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean processNotTooLong(String process) {
		if(process.length() <= 30) {
			return true;
		} else {
			return false;
		}
	}


	//difficultyが前のデータ以下になるように制御する
	public boolean difficultyTest(int difficulty, int tdbd_id ,ArrayList<ProcessBean> list) {
		if (!list.isEmpty()) {
			int befor_difficulty = list.get(0).getDifficulty();

			if (difficulty <= befor_difficulty) {
				return true;
			} else {
				return false;
			}
		} else {
			TDBDDAO dao = new TDBDDAO();
			if (difficulty <= dao.getDifficulty(tdbd_id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean difficultyTest(int difficulty, int befor_difficulty) {
			if (difficulty <= befor_difficulty) {
				return true;
			} else {
				return false;
			}
	}


	//Completedテスト


	public boolean Test(String dream, int age) {
		if (CompletedNotNull(dream, age) && CompletedNotTooLong(dream) ) {
			return true;
		} else {
			return false;
		}
	}


	public boolean CompletedNotNull(String dream, int age) {
		if(dream != "" && age != -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CompletedNotTooLong(String dream) {
		if(dream.length() <= 30) {
			return true;
		} else {
			return false;
		}
	}

	//今の年齢以上

}
