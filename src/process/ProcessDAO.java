package process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ProcessBean;
import sogo.DBAccess;

public class ProcessDAO extends DBAccess {

	public ProcessDAO() {
	}

	public int searchId(String process) {
		String sql = "select id from process where process = ?";

		int id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, process);
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

	public int getTdbd_id(String process) {
		String sql = "select tdbd_id from process where process = ?";

		int tdbd_id = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, process);
			rs = ps.executeQuery();
			while (rs.next()) {
				tdbd_id = rs.getInt("tdbd_id");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return tdbd_id;
	}



	public String getProcess(int id) {
		String sql = "select process from process where id = ?";

		String process = null;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				process = rs.getString("process");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return process;
	}


	public int getDifficulty(int id) {
		String sql = "select difficulty from process where id = ?";

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

	public int get_order(int id) {
		String sql = "select _order from process where id = ?";

		int _order = 0;
		ResultSet rs = null;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				_order = rs.getInt("_order");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return _order;
	}



	public void insert(int tdbd_id,  String process, int difficulty, int _order) {
		String sql = "insert into process(tdbd_id, process, difficulty, _order) values(?, ?, ?, ?)";

		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, tdbd_id);
			ps.setString(2, process);
			ps.setInt(3, difficulty);
			ps.setInt(4, _order);

			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	//そのユーザーのプロセスを一覧表示
	public ArrayList<ProcessBean> getList(int user_id) {
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
		String sql = "select * from process "
				+ "INNER JOIN todobydeath ON process.tdbd_id = todobydeath.id "
				+ "where user_id = ? order by priority desc, tdbd, process.difficulty asc;";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProcessBean bean = new ProcessBean();

				bean.setId(rs.getInt("id"));
				bean.setTdbd_id(rs.getInt("tdbd_id"));
				bean.setProcess(rs.getString("process"));
				bean.setDifficulty(rs.getInt("process.difficulty"));
				bean.set_order(rs.getInt("_order"));
				bean.setUser_id(rs.getInt("user_id"));
				bean.setTDBD(rs.getString("tdbd"));
				bean.setPriority(rs.getInt("priority"));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	//tdbdごとのプロセスを行動順で一覧表示
	public ArrayList<ProcessBean> getProcessList(int tdbd_id) {
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
		String sql = "select * from process "
				+ "INNER JOIN todobydeath ON process.tdbd_id = todobydeath.id "
				+ "where tdbd_id = ? order by priority desc, tdbd, process.difficulty asc;";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, tdbd_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProcessBean bean = new ProcessBean();

				bean.setId(rs.getInt("id"));
				bean.setTdbd_id(rs.getInt("tdbd_id"));
				bean.setProcess(rs.getString("process"));
				bean.setDifficulty(rs.getInt("process.difficulty"));
				bean.set_order(rs.getInt("_order"));
				bean.setUser_id(rs.getInt("user_id"));
				bean.setTDBD(rs.getString("tdbd"));
				bean.setPriority(rs.getInt("priority"));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	//行動順トップのprocessを優先度順で一覧表示
	public ArrayList<ProcessBean> getToDo(int user_id) {
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
		String sql = "select *  from process "
				+ "LEFT JOIN todobydeath ON process.tdbd_id = todobydeath.id "
				+ "Inner join (select tdbd_id, MAX(_order) as clm1 from process group by tdbd_id) as table1 "
				+ "on table1.tdbd_id = process.tdbd_id AND table1.clm1 = _order "
				+ "where user_id = ? order by priority desc, tdbd, process.difficulty asc;";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProcessBean bean = new ProcessBean();

				bean.setId(rs.getInt("id"));
				bean.setTdbd_id(rs.getInt("tdbd_id"));
				bean.setProcess(rs.getString("process"));
				bean.setDifficulty(rs.getInt("process.difficulty"));
				bean.set_order(rs.getInt("_order"));
				bean.setUser_id(rs.getInt("user_id"));
				bean.setTDBD(rs.getString("tdbd"));
				bean.setPriority(rs.getInt("priority"));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//プロセスの追加が推奨されるTDBDを取得
	public ArrayList<ProcessBean> getTDBD(int user_id) {
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
		String sql = "select *  from process "
				+ "LEFT JOIN todobydeath ON process.tdbd_id = todobydeath.id "
				+ "Inner join (select tdbd_id, MIN(difficulty) as clm1 from process group by tdbd_id) as table1 "
				+ "on table1.tdbd_id = process.tdbd_id AND table1.clm1 = process.difficulty "
				+ "where user_id = 1 AND process.difficulty > 1 "
				+ "order by priority desc, tdbd, process.difficulty asc;";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProcessBean bean = new ProcessBean();

				bean.setId(rs.getInt("id"));
				bean.setTdbd_id(rs.getInt("tdbd_id"));
				bean.setProcess(rs.getString("process"));
				bean.setDifficulty(rs.getInt("process.difficulty"));
				bean.set_order(rs.getInt("_order"));
				bean.setUser_id(rs.getInt("user_id"));
				bean.setTDBD(rs.getString("tdbd"));
				bean.setPriority(rs.getInt("priority"));

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
		String sql = "delete from process where id = ?";
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

	//TDBDを削除するときに連動する用
	public int tdbdDelete(int tdbd_id) {
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
		list = getProcessList(tdbd_id);
		String sql = "delete from process where id = ?";
		int rs = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				ps.setInt(1, list.get(i).getId());
				rs = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}



	public int update(int id, String process, int difficulty) {
		String sql = "update process set process = ?, difficulty = ? where id = ?";
		int rs = 0;
		try {
			connect();

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, process);
			ps.setInt(2, difficulty);
			ps.setInt(3, id);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rs;
	}

	public boolean check(String process, int difficulty) {
		String sql = "select id from process where process = ? and difficulty = ?;";
		ResultSet rs = null;
		int id = 0;
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, process);
			ps.setInt(2, difficulty);
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
