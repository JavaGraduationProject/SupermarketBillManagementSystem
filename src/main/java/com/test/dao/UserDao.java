package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.test.common.DbUtils;
import com.test.entity.User;

/**
 * ʵ�ֶ�User_message����в����ķ���
 * 
 * @author �����˵�
 *
 */
public class UserDao {

	/**
	 * ��֤�û��Ƿ��¼�ɹ�
	 * 
	 * @param userId       �û����
	 * @param userPassword ����
	 * @return ��¼�ɹ�����com.test.entity��ʵ�������򷵻�null
	 * @throws SQLException           ���ݿ�����쳣
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 */
	public User login(Integer userOn, String userPassword) throws SQLException, ClassNotFoundException {
		// �������Ӷ���
		Connection conn = null;
		// ����������
		PreparedStatement ps = null;
		// ���������
		ResultSet rs = null;
		User user = new User();
		try {

			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬�ж��û���¼��Ϣ�Ƿ���ȷ
			String sql = "select user_id,user_on,user_name,user_password,user_sex,user_age,user_tel,user_loc,user_role from user where user_on = ? and user_password=?";
			// ����׼�������󣬲�����׼������Ҫִ�е�select���
			ps = conn.prepareStatement(sql);
			// �滻׼������е��ʺ�
			ps.setInt(1, userOn);
			ps.setString(2, userPassword);
			// ʹ��׼��������ִ��select��䣬������ѯ�������������
			rs = ps.executeQuery();
			// ͨ��������ж��û��Ƿ��¼�ɹ�
			if (rs.next()) {
				// ����û���¼�ɹ�������Ϣ��װ��UserMessageʵ���������
				user.setUserId(rs.getInt("user_id"));
				user.setUserOn(rs.getInt("user_on"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserSex(rs.getString("user_sex"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserTel(rs.getString("user_tel"));
				user.setUserLoc(rs.getString("user_loc"));
				user.setUserRole(rs.getString("user_role"));
				return user;
			}
		} finally {
			DbUtils.close(rs, ps, conn);
		}
		return null;
	}

	/**
	 * ���������µ��û���Ϣ��
	 * 
	 * @param userMessage �µ��û���Ϣ���û��������룬��ϵ�绰�����䣩
	 * @return ��ӳɹ����ش���0�����������򷵻�0
	 * @throws ClassNotFoundException ���ݿ���������ʧ���쳣
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public int insert(User userMessage) throws ClassNotFoundException, SQLException {
		// �������Ӷ���
		Connection conn = null;
		// ����������
		PreparedStatement ps = null;
		// ���������
		ResultSet rs = null;
		// ������ݿ����Ӷ���
		try {
			conn = DbUtils.getConnection();
			// ����insert��䣬���������µ�����
			String sql = "insert into user(user_on,user_name,user_password,user_sex,user_age,user_tel,user_loc,user_role) values(?,?,?,?,?,?,?,?)";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setInt(1, userMessage.getUserOn());
			ps.setString(2, userMessage.getUserName());
			ps.setString(3, userMessage.getUserPassword());
			ps.setString(4, userMessage.getUserSex());
			ps.setInt(5, userMessage.getUserAge());
			ps.setString(6, userMessage.getUserTel());
			ps.setString(7, userMessage.getUserLoc());
			ps.setString(8, userMessage.getUserRole());
			// ʹ��׼��������ִ��insert��䣬�����insert���ִ��Ӱ����е���������
			int i = ps.executeUpdate();
			// �����ӳɹ����ύ����
			if (i > 0) {
				conn.commit();
				return i;
			} else {
				// ������ʧ�ܣ���������
				conn.rollback();
			}
		} finally {
			// �ر����ݿ���ض���
			DbUtils.close(rs, ps, conn);
		}

		return 0;
	}
	public int update(User user) throws ClassNotFoundException, SQLException {
		// �������Ӷ���
		Connection conn = null;
		// ����������
		PreparedStatement ps = null;
		// ���������
		ResultSet rs = null;
		// ������ݿ����Ӷ���
		try {
			conn = DbUtils.getConnection();
			// ����insert��䣬���������µ�����
			String sql = "update user set user_on=?,user_name=?,user_sex=?,user_age=?,user_tel=?,user_loc=?,user_role=? where user_id=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setInt(1, user.getUserOn());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserSex());
			ps.setInt(4, user.getUserAge());
			ps.setString(5, user.getUserTel());
			ps.setString(6, user.getUserLoc());
			ps.setString(7, user.getUserRole());
			ps.setInt(8, user.getUserId());
			// ʹ��׼��������ִ��insert��䣬�����insert���ִ��Ӱ����е���������
			int i = ps.executeUpdate();
			// �����ӳɹ����ύ����
			if (i > 0) {
				conn.commit();
				return i;
			} else {
				// ������ʧ�ܣ���������
				conn.rollback();
			}
		} finally {
			// �ر����ݿ���ض���
			DbUtils.close(rs, ps, conn);
		}

		return 0;
	}
	public int passupdate(String userId,String pass,String pass1) throws ClassNotFoundException, SQLException {
		// �������Ӷ���
		Connection conn = null;
		// ����������
		PreparedStatement ps = null;
		// ���������
		ResultSet rs = null;
		// ������ݿ����Ӷ���
		try {
			conn = DbUtils.getConnection();
			// ����insert��䣬���������µ�����
			String sql = "update user set user_password=? where user_id=? and user_password=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setString(1, pass1);
			ps.setString(2, userId);
			ps.setString(3, pass);
			
			// ʹ��׼��������ִ��insert��䣬�����insert���ִ��Ӱ����е���������
			int i = ps.executeUpdate();
			// �����ӳɹ����ύ����
			if (i > 0) {
				conn.commit();
				return i;
			} else {
				// ������ʧ�ܣ���������
				conn.rollback();
			}
		} finally {
			// �ر����ݿ���ض���
			DbUtils.close(rs, ps, conn);
		}

		return 0;
	}
	public int delete(String userId) throws ClassNotFoundException, SQLException {
		// �������Ӷ���
		Connection conn = null;
		// ����������
		PreparedStatement ps = null;
		// ���������
		ResultSet rs = null;
		// ������ݿ����Ӷ���
		try {
			conn = DbUtils.getConnection();
			// ����insert��䣬���������µ�����
			String sql = "delete from user where user_id=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setString(1,userId);
			// ʹ��׼��������ִ��insert��䣬�����insert���ִ��Ӱ����е���������
			int i = ps.executeUpdate();
			// �����ӳɹ����ύ����
			if (i > 0) {
				conn.commit();
				return i;
			} else {
				// ������ʧ�ܣ���������
				conn.rollback();
			}
		} finally {
			// �ر����ݿ���ض���
			DbUtils.close(rs, ps, conn);
		}
		return 0;
	}
	/**
	 * ��ѯuser_message������������������
	 * 
	 * @param userMessage ��ѯ���������û���������ϵ�绰��
	 * @return ����java.util.List���͵�ʵ��
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public List<User> selectUser(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select user_id,user_on,user_name,user_password,user_sex,user_age,user_tel,user_loc,user_role from user where 1=1 ");
			// ���ݷ������βδ������ݣ�ƴ��select���
			
			if (userName != null && !userName.trim().equals("")) {
				sql.append(" and user_name like ?");
			}
			ps = conn.prepareStatement(sql.toString());
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			if (userName != null && !userName.trim().equals("")) {
				ps.setString(1, "%" + userName + "%");
			}

			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			List<User> list = new ArrayList<>();
			// ����ʵ����������ڱ��������е�һ������
			User user = null;
			// ʹ��ѭ����������е����ݷ�װ��list������
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserOn(rs.getInt("user_on"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserSex(rs.getString("user_sex"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserTel(rs.getString("user_tel"));
				user.setUserLoc(rs.getString("user_loc"));
				String tempString = rs.getString("user_role").trim();
				if(Integer.parseInt(rs.getString("user_role"))== 1) {
					user.setUserRole("����Ա");
				}
				else {
					user.setUserRole("��ͨ�û�");
				}
				list.add(user);
			}
			// ����list����
			return list;
		} finally {
			// �ر����ݿ����Ӷ���
			DbUtils.close(rs, ps, conn);
		}
	}
	public User selectContUser(String userId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select user_id,user_on,user_name,user_password,user_sex,user_age,user_tel,user_loc,user_role from user where user_id=?");
			// ���ݷ������βδ������ݣ�ƴ��select���
			
			ps = conn.prepareStatement(sql.toString());
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			if (userId != null && !userId.trim().equals("")) {
				ps.setString(1, userId );
			}

			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			
			// ����ʵ����������ڱ��������е�һ������
			User user = new User();
			// ʹ��ѭ����������е����ݷ�װ��list������
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserOn(rs.getInt("user_on"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserSex(rs.getString("user_sex"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserTel(rs.getString("user_tel"));
				user.setUserLoc(rs.getString("user_loc"));
				String tempString = rs.getString("user_role").trim();
				if(Integer.parseInt(rs.getString("user_role"))== 1) {
					user.setUserRole("����Ա");
				}
				else {
					user.setUserRole("��ͨ�û�");
				}

			}
			// ����list����
			return user;
		} finally {
			// �ر����ݿ����Ӷ���
			DbUtils.close(rs, ps, conn);
		}
	}
}
