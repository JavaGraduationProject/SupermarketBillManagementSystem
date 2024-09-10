package com.test.service;

/**
 * �û�ģ���ҵ���߼���
 * @author �����˵�
 *
 */

import java.sql.SQLException;
import java.util.List;



import com.test.dao.UserDao;
import com.test.entity.Bill;
import com.test.entity.User;

public class UserService {

	// �����־û������
	private UserDao userMessageDao = new UserDao();

	/**
	 * ��ѯ�����������û���Ϣ
	 * 
	 * @param userMessage ��ѯ�������û���������ϵ�绰��
	 * @return ����һ��java.util.List��ʵ��
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public List<User> selectUser(String userName) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.selectUser(userName);
	}
	
	public User selecContUser(String userId) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.selectContUser(userId);
	}
	
	public int deleteUser(String userId) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.delete(userId);
	}
	
	public int passupdate(String userId,String pass,String pass1) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.passupdate(userId,pass,pass1);
	}
	
	public int update(User user) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.update(user);
	}
	public int insert(User user) throws ClassNotFoundException, SQLException {
		return this.userMessageDao.insert(user);
	}

}
