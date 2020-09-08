package tdbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.TDBDBean;
import process.ProcessDAO;
import sogo.DBAccess;

public class TDBDDAO extends DBAccess {

	public TDBDDAO() {
	}

	public int searchId(String tdbd) {
		String sql = "select id from todobydeath where tdbd = ?";

		int id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, tdbd);
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


	public String getTDBD(int id) {
		String sql = "select tdbd from todobydeath where id = ?";

		String tdbd = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tdbd = rs.getString("tdbd");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return tdbd;
	}


	public int getPriority(int id) {
		String sql = "select priority from todobydeath where id = ?";

		int priority = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				priority = rs.getInt("priority");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return priority;
	}

	public int getDifficulty(int id) {
		String sql = "select difficulty from todobydeath where id = ?";

		int difficulty = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				difficulty = rs.getInt("difficulty");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return difficulty;
	}



	public void insert(String tdbd, int priority, int difficulty, int user_id) {
		String sql = "insert into todobydeath(tdbd, priority, difficulty, user_id) values(?, ?, ?, ?)";

		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, tdbd);
			ps.setInt(2, priority);
			ps.setInt(3, difficulty);
			ps.setInt(4, user_id);

			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public ArrayList<TDBDBean> getList(int user_id) {
		ArrayList<TDBDBean> list = new ArrayList<TDBDBean>();
		String sql = "select * from todobydeath where user_id = ? order by priority desc, difficulty asc";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TDBDBean bean = new TDBDBean();

				bean.setTDBD(rs.getString("tdbd"));
				bean.setPriority(rs.getInt("priority"));
				bean.setDifficulty(rs.getInt("difficulty"));
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
		ProcessDAO dao = new ProcessDAO();
		dao.tdbdDelete(id);

		String sql = "delete from todobydeath where id = ?";
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

	public int update(int id, String tdbd, int priority, int difficulty) {
		String sql = "update todobydeath set tdbd = ?, priority = ?, difficulty = ? where id = ?";
		int rs = 0;
		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, tdbd);
			ps.setInt(2, priority);
			ps.setInt(3, difficulty);
			ps.setInt(4, id);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}

	public boolean check(String tdbd, int priority, int difficulty) {
		String sql = "select id from todobydeath where tdbd = ? and priority = ? and difficulty = ?;";
		ResultSet rs = null;
		int id = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, tdbd);
			ps.setInt(2, priority);
			ps.setInt(3, difficulty);
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
