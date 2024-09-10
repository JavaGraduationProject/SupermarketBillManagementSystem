package com.test.service;

/**
 * �û�ģ���ҵ���߼���
 * @author �����˵�
 *
 */

import java.sql.SQLException;
import java.util.List;



import com.test.dao.BillDao;
import com.test.dao.UserDao;
import com.test.entity.Bill;
import com.test.entity.Provider;
import com.test.entity.User;

public class BillService {

	// �����־û������
	private BillDao Billdao = new BillDao();

	/**
	 * ��ѯ�����������û���Ϣ
	 * 
	 * @param userMessage ��ѯ�������û���������ϵ�绰��
	 * @return ����һ��java.util.List��ʵ��
	 * @throws ClassNotFoundException ���ݿ���������ʧ��
	 * @throws SQLException           ���ݿ�����쳣
	 */
	
	public List<Bill> selectBill(String billProname,String billPayment) throws ClassNotFoundException, SQLException {
		return this.Billdao.selectBill(billProname,billPayment);
	}

	public Bill selectContBill(String BillId) throws ClassNotFoundException, SQLException {
		return this.Billdao.selectContBill(BillId);
	}
	
	public int deleteBill(String BillId) throws ClassNotFoundException, SQLException {
		return this.Billdao.delete(BillId);
	}
	
	public int updateContBill(Bill bill) throws ClassNotFoundException, SQLException {
		return this.Billdao.updateContBill(bill);
	}
	
	public int insert(Bill bill) throws ClassNotFoundException, SQLException {
		return this.Billdao.insert(bill);
	}
}
