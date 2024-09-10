package com.test.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.UserDao;
import com.test.entity.User;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����ַ���
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// ����û��������������ı�ź�����
		String tempUser = req.getParameter("userUser");
		String userPassWord = req.getParameter("userpwd");
		// ��֤��Ų���Ϊ�գ�1-5������
		if (!tempUser.matches("^[0-9]{1,16}$")) {
			// ����1��System.out.println("���벻��Ϊ�գ���ֻ����1-5λ����������");
			// ����2���ӵ�ǰservlet��ת��login.jspҳ�棬��������Ϣ���ݵ�login.jspҳ��
			req.setAttribute("message", "<script>alert('�˺Ų�Ϊ�գ���')</script>");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		// ��֤���벻��Ϊ��
		if (userPassWord == null || userPassWord.trim().equals("")) {
			req.setAttribute("userUser", Integer.parseInt(tempUser));
			req.setAttribute("message", "<script>alert('���벻��Ϊ��')</script>");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		try {
			// ��֤�û��Ƿ��¼�ɹ�
			//throw new Exception();
			//����usermessagedao��login������֤�û��Ƿ�ɹ�
			HttpSession session2 = req.getSession(true);
			UserDao dao = new UserDao();
			User user = dao.login(Integer.parseInt(tempUser), userPassWord);
			if (user!=null) {
				//��¼�ɹ�
				session2.setAttribute("loginUserName", user.getUserName());
				session2.setAttribute("loginUserRole", user.getUserRole());
				resp.sendRedirect(resp.encodeRedirectURL("pages/admin_index.jsp"));
			} else {
				//��¼ʧ�ܣ��ӵ�ǰservlet��ת��login.jspҳ�棬����login.jspҳ�洫�ݴ�����Ϣ
				req.setAttribute("message", "<script>alert('�����쳣')</script>");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("login.jsp");
		}
	}
}
