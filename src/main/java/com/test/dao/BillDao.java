package com.test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.test.common.DbUtils;
import com.test.entity.Bill;
import com.test.entity.Provider;
import com.test.entity.User;

/**
 * ʵ�ֶ�User_message����в����ķ���
 * 
 * @author �����˵�
 *
 */
public class BillDao {
	public int delete(String BillId) throws ClassNotFoundException, SQLException {
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
			String sql = "delete from bill where Bill_id=?";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setString(1,BillId);
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
	public int insert(Bill bill) throws ClassNotFoundException, SQLException {
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
			String sql = "insert into bill(bill_no,bill_proname,bill_pronum,bill_price,bill_payment,bill_prodescribe,provider_id) values(?,?,?,?,?,?,?)";
			// �������׼�����󣬲�����׼��������Ҫִ�е�insert���
			ps = conn.prepareStatement(sql);
			// �滻׼���������е��ʺ�
			ps.setInt(1, bill.getBillNo());
			ps.setString(2, bill.getBillProname());
			ps.setInt(3, bill.getBillPronum());
			ps.setDouble(4, bill.getBillPrice());
			ps.setBoolean(5, bill.getBillPayment());
			ps.setString(6, bill.getBillProdescribe());
			ps.setString(7, bill.getProviderId());
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
	public List<Bill> selectBill(String billProname,String billPayment) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select bill_id,bill_no,bill_proname,bill_pronum,bill_price,bill_payment,bill_prodescribe,bill_date,bill.provider_id,provider_name from bill,provider where bill.provider_id=provider.provider_id");
			// ���ݷ������βδ������ݣ�ƴ��select���

			if (billProname != null && !billProname.trim().equals("")) {
				sql.append(" and bill_proname like ?");
			}
			
			if (billPayment != null && !billPayment.trim().equals("")) {
				sql.append(" and bill_payment=? ");
			}
			
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			ps = conn.prepareStatement(sql.toString());
		    if(billPayment != null && !billPayment.trim().equals("")) {
		    	if(Integer.parseInt(billPayment)<=0) {
					billPayment="0";
				}
				else {
					billPayment="1";
				}
		    }
			
			if (billProname != null && !billProname.trim().equals("")) {
				ps.setString(1, "%" + billProname + "%");
				if (billPayment != null && !billPayment.trim().equals("")) {
					ps.setString(2, billPayment);
				}
			}
			else {
				if (billPayment != null && !billPayment.trim().equals("")) {
					ps.setString(1,billPayment);
				}
			}
			
			
			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			List<Bill> list = new ArrayList<>();
			// ����ʵ����������ڱ��������е�һ������
			Bill bill = null;
			// ʹ��ѭ����������е����ݷ�װ��list������
			while (rs.next()) {
				bill = new Bill();
				bill.setBillId(rs.getInt("bill_id"));
				bill.setBillNo(rs.getInt("bill_no"));
				bill.setBillProname(rs.getString("bill_proname"));
				bill.setBillPronum(rs.getInt("bill_pronum"));
				bill.setBillPrice(rs.getDouble("bill_price"));
				bill.setBillPayment(rs.getBoolean("bill_payment"));
				bill.setBillProdescribe(rs.getString("bill_prodescribe"));
				bill.setBillDate(rs.getDate("bill_date"));
				bill.setProviderId(rs.getString("provider_id"));
				bill.setProviderName(rs.getString("provider_name"));
				list.add(bill);
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
	public Bill selectContBill(String BillId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"select bill_id,bill_no,bill_proname,bill_pronum,bill_price,bill_payment,bill_prodescribe,bill_date,provider_id from bill where Bill_id=?");

			// ����׼�������󣬲�����׼��������Ҫִ�е����
			ps = conn.prepareStatement(sql.toString());

			if (BillId != null && !BillId.trim().equals("")) {
				ps.setString(1,  BillId );			
			}

			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeQuery();
			// ����list���ϣ����ڱ������������е�����
			List<Bill> list = new ArrayList<>();
			// ����ʵ����������ڱ��������е�һ������
			Bill bill = new Bill();
			// ʹ��ѭ����������е����ݷ�װ��list������
			
			while (rs.next()) {
				bill.setBillId(rs.getInt("bill_id"));
				bill.setBillNo(rs.getInt("bill_no"));
				bill.setBillProname(rs.getString("bill_proname"));
				bill.setBillPronum(rs.getInt("bill_pronum"));
				bill.setBillPrice(rs.getDouble("bill_price"));
				bill.setBillPayment(rs.getBoolean("bill_payment"));
				bill.setBillProdescribe(rs.getString("bill_prodescribe"));
				bill.setBillDate(rs.getDate("bill_date"));
				bill.setProviderId(rs.getString("provider_id"));
			}
			// ����list����
			return bill;
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
	public int updateContBill(Bill bill) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			// ������ݿ����Ӷ���
			conn = DbUtils.getConnection();
			// ����select��䣬��ѯuser������������
			StringBuilder sql = new StringBuilder(
					"update bill set bill_no=?,bill_proname=?,bill_pronum=?,bill_price=?,bill_payment=?,bill_prodescribe=?,provider_id=? where bill_id = ?");
			
			// ����׼�������󣬲�����׼��������Ҫִ�е����
			ps = conn.prepareStatement(sql.toString());

			if (bill.getBillId() != null) {
				ps.setInt(1, bill.getBillNo());		
				ps.setString(2, bill.getBillProname());		
				ps.setInt(3, bill.getBillPronum());		
				ps.setDouble(4, bill.getBillPrice());		
				ps.setBoolean(5, bill.getBillPayment());		
				ps.setString(6, bill.getBillProdescribe());		
				ps.setString(7, bill.getProviderId());		
				ps.setInt(8, bill.getBillId());			
			}
			else {
				return 0;
			}
			
			// ʹ��׼��������ִ��select��䣬������ѯ�Ľ�����뵽�������
			rs = ps.executeUpdate();
			// ����list���ϣ����ڱ������������е�����

			return rs;
		} finally {
			// �ر����ݿ����Ӷ���
			DbUtils.close(null, ps, conn);
		}
	}
}
