package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.test.common.DbUtils;
import com.test.entity.Provider;
import com.test.entity.User;

/**
 * ʵ�ֶ�User_message����в����ķ���
 * 
 * @author �����˵�
 *
 */
public class ProviderDao {
	public int delete(String providerId) throws ClassNotFoundException, SQLException {
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
			String sql = "delete from provider where provider_id=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setString(1,providerId);
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
	 * ���������µ��û���Ϣ��
	 * 
	 * @param userMessage �µ��û���Ϣ���û��������룬��ϵ�绰�����䣩
	 * @return ��ӳɹ����ش���0�����������򷵻�0
	 * @throws ClassNotFoundException ���ݿ���������ʧ���쳣
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public int insert(Provider provider) throws ClassNotFoundException, SQLException {
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
			String sql = "insert into provider(provider_no,provider_name,provider_describe,provider_contacts,provider_tel,provider_loc) values(?,?,?,?,?,?)";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setInt(1, provider.getProviderNo());
			ps.setString(2, provider.getProviderName());
			ps.setString(3, provider.getProviderDescribe());
			ps.setString(4, provider.getProviderContacts());
			ps.setString(5, provider.getProviderTel());
			ps.setString(6, provider.getProviderLoc());
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
	public int update(Provider provider) throws ClassNotFoundException, SQLException {
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
			String sql = "update provider set provider_no=?,provider_name=?,provider_describe=?,provider_contacts=?,provider_tel=?,provider_loc=? where provider_id=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setInt(1, provider.getProviderNo());
			ps.setString(2, provider.getProviderName());
			ps.setString(3, provider.getProviderDescribe());
			ps.setString(4, provider.getProviderContacts());
			ps.setString(5, provider.getProviderTel());
			ps.setString(6, provider.getProviderLoc());
			ps.setInt(7, provider.getProviderId());
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
	public List<Provider> selectProvider(String providerName,String providerDescribe) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select provider_id,provider_no,provider_name,provider_describe,provider_contacts,provider_tel,provider_loc from provider where 1=1");
			// ���ݷ������βδ������ݣ�ƴ��select���
			
			
			
			if (providerName != null && !providerName.trim().equals("")) {
				sql.append(" and provider_name like ?");
			}
			
			if (providerDescribe != null && !providerDescribe.trim().equals("")) {
				sql.append(" and provider_describe like ?");
			}
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			ps = conn.prepareStatement(sql.toString());

			if (providerName != null && !providerName.trim().equals("")) {
				ps.setString(1, "%" + providerName + "%");
				if (providerDescribe != null && !providerDescribe.trim().equals("")) {
					ps.setString(2, "%" + providerDescribe + "%");
				}
			}
			else {
				if (providerDescribe != null && !providerDescribe.trim().equals("")) {
					ps.setString(1, "%" + providerDescribe + "%");
				}
			}
			
			
			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			List<Provider> list = new ArrayList<>();
			// ����ʵ����������ڱ��������е�һ������
			Provider provider = null;
			// ʹ��ѭ����������е����ݷ�װ��list������
			while (rs.next()) {
				provider = new Provider();
				provider.setProviderId(rs.getInt("provider_id"));
				provider.setProviderNo(rs.getInt("provider_no"));
				provider.setProviderName(rs.getString("provider_name"));
				provider.setProviderDescribe(rs.getString("provider_describe"));
				provider.setProviderContacts(rs.getString("provider_contacts"));
				provider.setProviderTel(rs.getString("provider_tel"));
				provider.setProviderLoc(rs.getString("provider_loc"));
				list.add(provider);
			}
			// ����list����
			return list;
		} finally {
			// �ر����ݿ����Ӷ���
			DbUtils.close(rs, ps, conn);
		}
	}
	/**
	 * ��ѯuser_message������������������
	 * 
	 * @param userMessage ��ѯ���������û���������ϵ�绰��
	 * @return ����java.util.List���͵�ʵ��
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 * @throws SQLException           ���ݿ�����쳣
	 */
	public Provider selectContProvider(String providerId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select provider_id,provider_no,provider_name,provider_describe,provider_contacts,provider_tel,provider_loc from provider where provider_id=?");
			// ���ݷ������βδ������ݣ�ƴ��select���
			
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			ps = conn.prepareStatement(sql.toString());

			if (providerId != null && !providerId.trim().equals("")) {
				ps.setString(1,providerId);
			}

			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			
			// ����ʵ����������ڱ��������е�һ������
			Provider provider = new Provider();
			// ʹ��ѭ����������е����ݷ�װ��list������
			while (rs.next()) {
				provider.setProviderId(rs.getInt("provider_id"));
				provider.setProviderNo(rs.getInt("provider_no"));
				provider.setProviderName(rs.getString("provider_name"));
				provider.setProviderDescribe(rs.getString("provider_describe"));
				provider.setProviderContacts(rs.getString("provider_contacts"));
				provider.setProviderTel(rs.getString("provider_tel"));
				provider.setProviderLoc(rs.getString("provider_loc"));
			}
			// ����list����
			return provider;
		} finally {
			// �ر����ݿ����Ӷ���
			DbUtils.close(rs, ps, conn);
		}
	}
}
