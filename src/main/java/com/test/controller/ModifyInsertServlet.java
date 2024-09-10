package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.BillDao;
import com.test.dao.UserDao;
import com.test.entity.Bill;
import com.test.entity.Provider;
import com.test.entity.User;
import com.test.service.BillService;
import com.test.service.ProviderService;
import com.test.service.UserService;

public class ModifyInsertServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
	
		try {
			ProviderService Pservice = new ProviderService();
			BillService Bservice = new BillService();
			Bill bill = new Bill();
			
			String billId = req.getParameter("billId");
			if(billId != null) {
				bill = Bservice.selectContBill(billId);
				req.setAttribute("bill", bill);
			}
			List<Provider> list = Pservice.selectProvider(null,null);
			req.setAttribute("providerList", list);
			req.getRequestDispatcher("/pages/modify.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����ַ���
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
	
		// ����û���������������ע����Ϣ
		String billId = req.getParameter("billId");
		String billNo = req.getParameter("billNum");
		String billPrice = req.getParameter("money");
		String billPronum = req.getParameter("sum");
		String billProname = req.getParameter("title");
		String billProdescribe = req.getParameter("discription");
		String billPayment = req.getParameter("isPay");
		String providerId = req.getParameter("providerId");
		// ����������֤
		// ��֤�û��������û����룬ȷ�����룬��ϵ�绰�������Ƿ�Ϊ��
		// ��֤�û�������Ϊ��
		if (billNo == null || billNo.trim().equals("")) {
			req.setAttribute("money", billPrice);
			req.setAttribute("discription", billProdescribe);
			req.setAttribute("isPay", billPayment);
			req.setAttribute("message", "<script>alert('��Ų���Ϊ��!');</script>");
			req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
			return;
		}
		
		// ��֤�û����벻Ϊ��
		if (billPrice == null || billPrice.trim().equals("")) {
			req.setAttribute("billNum", billNo);
			req.setAttribute("discription", billProdescribe);
			req.setAttribute("isPay", billPayment);
			req.setAttribute("message", "<script>alert('����Ϊ��!');</script>");
			req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
			return;
		}
		
		// �����ݷ�װ��user messageʵ����Ķ�����
		Bill bill = new Bill();
		if(billId!=null && !"".equals(billId)){
			bill.setBillId(Integer.parseInt(billId));
		}

		bill.setBillNo(Integer.parseInt(billNo));
		bill.setBillPronum(Integer.parseInt(billPronum)); 
		bill.setBillProname(billProname);
		bill.setBillPrice(Double.parseDouble(billPrice));
		bill.setBillProdescribe(billProdescribe);
		if(Integer.parseInt(billPayment)<=0) {
			bill.setBillPayment(false);
		}
		else {
			bill.setBillPayment(true);
		}
		bill.setProviderId(providerId);
		
		try {
			// ����usermessagedao������Ϊinset()���������û�ע������������ע���Ƿ�ɹ�
			BillDao dao = new BillDao();
			if(billId != null && !"".equals(billId)) {
				int update = dao.updateContBill(bill);
				if (update > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('�޸ĳɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectbill"));
				} else {
					req.setAttribute("message", "<script>alert('�޸�ʧ��!');</script>");
					req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
				}
			}
			else {
				int insert = dao.insert(bill);
				if (insert > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('��ӳɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectbill"));
				} else {
					req.setAttribute("message", "<script>alert('���ʧ��!');</script>");
					req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}
}
