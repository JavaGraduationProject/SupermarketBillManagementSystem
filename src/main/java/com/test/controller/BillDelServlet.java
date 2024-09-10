package com.test.controller;

import java.io.IOException;

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
import com.test.service.ProviderService;
import com.test.service.UserService;

public class BillDelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����ַ���
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// ����û���������������ע����Ϣ

		String billId = req.getParameter("billId");
		if(billId==null) {
			return;
		}
		try {
			// ����usermessagedao������Ϊinset()���������û�ע������������ע���Ƿ�ɹ�
			BillDao dao = new BillDao();
			if(billId != null) {
				int delete = dao.delete(billId);
				if (delete > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('ɾ���ɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectbill"));
				} else {
					req.setAttribute("message", "<script>alert('ɾ��ʧ��!');</script>");
					req.getRequestDispatcher("/selectbill").forward(req, resp);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}

}
