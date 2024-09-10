package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.entity.User;

import com.test.service.UserService;

public class SelectUsertServlet extends HttpServlet {

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
		//String userId = req.getParameter("userId");
		//String userName = req.getParameter("userName");
		//String userSex = req.getParameter("userSex");
		//String userAge = req.getParameter("userAge");
		//String userTel = req.getParameter("userTel");
		//String userLoc = req.getParameter("userLoc");
		//String userRole = req.getParameter("userRole");
		
		// ����ѯ������װ��UserMessageʵ���������
		//User userMessage = new User();
		//userMessage.setUserId(userId);
		//userMessage.setUserName(userName);
		//userMessage.setUserSex(userSex);
		//userMessage.setUserAge(userAge);
		//userMessage.setUserTel(userTel);
		//userMessage.setUserLoc(userLoc);
		//userMessage.setUserRole(userRole);

		try {
			// �ڵ�ǰ�����UserMessageService������ΪselectUserMessages()��������ѯ������������Ϣ������ò�ѯ���
			UserService service = new UserService();
			List<User> list = service.selectUser(req.getParameter("userName"));
			HttpSession session = req.getSession(true);
			
			//session.setAttribute("userId",userId);
			//session.setAttribute("userName",userName);
			//session.setAttribute("userSex",userSex);
			//session.setAttribute("userAge",userAge);
			//session.setAttribute("userTel",userTel);
			//session.setAttribute("userLoc",userLoc);
			//session.setAttribute("userRole",userRole);
			// ����ѯ�����List�����ִ���request
			req.setAttribute("userList", list);
			// ��ת��pages/image.jspҳ�棬��ʾ��ѯ���
			req.getRequestDispatcher("pages/userAdmin.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(resp.encodeRedirectURL("/supermarket/error.html"));
		}

	}

}
