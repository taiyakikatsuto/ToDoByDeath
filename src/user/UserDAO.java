package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import bean.UserBean;
import sogo.DBAccess;

public class UserDAO extends DBAccess {

	public UserDAO() {
	}

	public int searchByName(String name) {
		String sql = "select id from user where name = ?";

		int id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return id;
	}


	public int searchByEmail(String email) {
		String sql = "select id from user where email = ?";

		int id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return id;
	}


	public String getName(int id) {
		String sql = "select name from user where id = ?";

		String name = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return name;
	}



	public String getEmail(int id) {
		String sql = "select email from user where id = ?";

		String email = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				email = rs.getString("email");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return email;
	}

	public String password(int id) {
		String sql = "select password from user where id = ?";

		String password = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				password = rs.getString("password");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return password;
	}



	public int getAge(int id) {		//誕生日から年齢を計算する
		String sql = "select birthday from user where id = ?";

		int age  = -1;
		Calendar birthday = Calendar.getInstance();;
		Calendar now = Calendar.getInstance();

		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				birthday.setTime(rs.getDate("birthday"));
			}

			age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

			if (now.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
				age--;
			} else if (now.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)) {
				if (now.get(Calendar.DATE) < birthday.get(Calendar.DATE)) {
					age--;
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return age;
	}








	public void insert(String name, String email, String password) {
		String sql = "insert into user(name, email, password) values(?, ?, ?)";

		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);

			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public ArrayList<UserBean> getList() {
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		String sql = "select * from user order by id";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();

				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setId();
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	public int delete(int id) {
		String sql = "delete from user where id = ?";
		int rs = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}

	public int update(int id, String name, String email, String password) {
		String sql = "update user set name = ?, email = ?, password = ? where id = ?";
		int rs = 0;
		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setInt(4, id);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}

	public boolean check(String name, String email, String password) {
		String sql = "select id from user where name = ? and email = ? and password = ?;";
		ResultSet rs = null;
		int id = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
			if (id == 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
	}

	public boolean login(String email, String password) {
		String sql = "select name from user where email = ? and password = ?";

		String name = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}

			if (name != null) {
				return true;
			} else {
				return false;
			}

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
	}

}