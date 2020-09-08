package completed;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CompletedBean;
import sogo.DBAccess;

public class CompletedDAO extends DBAccess {

	public CompletedDAO() {
	}

	public int searchId(String dream) {
		String sql = "select id from completed where dream = ?";

		int id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, dream);
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


	public String getDream(int id) {
		String sql = "select dream from completed where id = ?";

		String dream = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				dream = rs.getString("dream");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dream;
	}



	public int getAge(int id) {
		String sql = "select age from completed where id = ?";

		int age = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				age = rs.getInt("age");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return age;
	}


	public int getUser_id(int id) {
		String sql = "select user_id from completed where id = ?";

		int user_id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return user_id;
	}



	public void insert(String dream, int age, int user_id) {
		String sql = "insert into completed(dream, age, user_id) values(?, ?, ?)";

		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, dream);
			ps.setInt(2, age);
			ps.setInt(3, user_id);

			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public ArrayList<CompletedBean> getList(int user_id) {
		ArrayList<CompletedBean> list = new ArrayList<CompletedBean>();
		String sql = "select * from completed where user_id = ? order by age asc";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CompletedBean bean = new CompletedBean();

				bean.setDream(rs.getString("dream"));
				bean.setAge(rs.getInt("age"));
				bean.setUser_id(rs.getInt("user_id"));
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
		String sql = "delete from completed where id = ?";
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

	public int update(int id, String dream, int age, int user_id) {
		String sql = "update completed set dream = ?, age = ?, user_id = ? where id = ?";
		int rs = 0;
		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, dream);
			ps.setInt(2, age);
			ps.setInt(3, user_id);
			ps.setInt(4, id);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}

	public boolean check(String dream, int age, int user_id) {
		String sql = "select id from completed where dream = ? and age = ? and user_id = ?;";
		ResultSet rs = null;
		int id = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, dream);
			ps.setInt(2, age);
			ps.setInt(3, user_id);
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
}