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

public class UserPassUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		try {
			UserService Uservice = new UserService();
			
			User user = new User();
			String userId = req.getParameter("userId");
			
			if(userId != null) {
				req.setAttribute("userId", userId);
			}
			
			req.getRequestDispatcher("/pages/userPassUpdate.jsp").forward(req, resp);
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
		String userId = req.getParameter("userId");
		String userPass = req.getParameter("pass");
		String userPassword = req.getParameter("password");
		String userPassword1 = req.getParameter("password1");
		
		// ����������֤
		// ��֤�û��������û����룬ȷ�����룬��ϵ�绰�������Ƿ�Ϊ��
		// ��֤�û�������Ϊ��

	
			if (userPassword.trim().equals("") != userPassword1.trim().equals("")) {
				req.setAttribute("message", "<script>alert('������ȷ��������Ҫ��ͬ!');</script>");
				req.getRequestDispatcher("/userpassupdate").forward(req, resp);
				return;
			}
			if (userPass == null || userPass.trim().equals("")) {
				req.setAttribute("message", "<script>alert('�ɵ����벻��Ϊ��!');</script>");
				req.getRequestDispatcher("/userpassupdate").forward(req, resp);
				return;
			}
			if (userPassword == null || userPassword.trim().equals("")) {
				req.setAttribute("message", "<script>alert('�µ����벻��Ϊ��!');</script>");
				req.getRequestDispatcher("/userpassupdate").forward(req, resp);
				return;
			}
			if (userPassword1 == null || userPassword1.trim().equals("")) {
				req.setAttribute("message", "<script>alert('ȷ�����벻��Ϊ��!');</script>");
				req.getRequestDispatcher("/userpassupdate").forward(req, resp);
				return;
			}
	
		try {
			// ����usermessagedao������Ϊinset()���������û�ע������������ע���Ƿ�ɹ�
			UserDao dao = new UserDao();
			
				int update = dao.passupdate(userId,userPass,userPassword);
				if (update > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('�޸ĳɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectUser"));
				} else {
					req.setAttribute("message", "<script>alert('�޸�ʧ��!');</script>");
					req.getRequestDispatcher("/userpassupdate").forward(req, resp);
				}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}

}
