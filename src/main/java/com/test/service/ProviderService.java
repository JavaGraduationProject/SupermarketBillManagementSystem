package com.test.service;

/**
 * �û�ģ���ҵ���߼���
 * @author �����˵�
 *
 */

import java.sql.SQLException;
import java.util.List;



import com.test.dao.ProviderDao;
import com.test.dao.UserDao;
import com.test.entity.Provider;
import com.test.entity.User;

public class ProviderService {

	// �����־û������
	private ProviderDao providerdao = new ProviderDao();

	/**
	 * ��ѯ�����������û���Ϣ
	 * 
	 * @param userMessage ��ѯ�������û���������ϵ�绰��
	 * @return ����һ��java.util.List��ʵ��
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public List<Provider> selectProvider(String providerName,String providerDescribe) throws ClassNotFoundException, SQLException {
		return this.providerdao.selectProvider(providerName,providerDescribe);
	}
	
	public Provider selectContProvider(String providerId) throws ClassNotFoundException, SQLException {
		return this.providerdao.selectContProvider(providerId);
	}
	
	public int deleteProvider(String ProviderId) throws ClassNotFoundException, SQLException {
		return this.providerdao.delete(ProviderId);
	}
	
	public int insert(Provider provider) throws ClassNotFoundException, SQLException {
		return this.providerdao.insert(provider);
	}

}
