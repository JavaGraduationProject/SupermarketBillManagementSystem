package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.test.dao.BillDao;
import com.test.dao.ProviderDao;
import com.test.dao.UserDao;
import com.test.entity.Bill;
import com.test.entity.Provider;
import com.test.entity.User;
import com.test.service.BillService;
import com.test.service.ProviderService;

public class ProviderInsertServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		try {
			
			ProviderService Pservice = new ProviderService();
			String providerId = req.getParameter("providerId");
			Provider provider = new Provider();
			if(providerId != null) {
				provider = Pservice.selectContProvider(providerId);
				req.setAttribute("provider", provider);
			}
			req.getRequestDispatcher("/pages/providerAdd.jsp").forward(req, resp);
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
		String providerId = req.getParameter("providerId");
		String providerNo = req.getParameter("proId");
		String providerName = req.getParameter("proName");
		String providerDescribe = req.getParameter("proDesc");
		String providerContacts = req.getParameter("contact");
		String providerYel = req.getParameter("phone");
		String providerLoc = req.getParameter("address");

		
		// ����������֤
		// ��֤�û��������û����룬ȷ�����룬��ϵ�绰�������Ƿ�Ϊ��
		// ��֤�û�������Ϊ��
		if (providerNo == null || providerNo.trim().equals("")) {
			req.setAttribute("message", "<script>alert('��Ų���Ϊ��!');</script>");
			req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
			return;
		}
		
		// ��֤�û����벻Ϊ��
		if (providerName == null || providerName.trim().equals("")) {
			req.setAttribute("message", "<script>alert('���Ʋ���Ϊ��!');</script>");
			req.getRequestDispatcher("/supermarket/pages/modify.jsp").forward(req, resp);
			return;
		}
		
		// �����ݷ�װ��user messageʵ����Ķ�����
		Provider provider = new Provider();
		if(providerId != null &&!"".equals(providerId)) {
			provider.setProviderId(Integer.parseInt(providerId));
		}

		provider.setProviderNo(Integer.parseInt(providerNo));
		provider.setProviderName(providerName); // ����1
		provider.setProviderDescribe(providerDescribe);
		provider.setProviderContacts(providerContacts);
		provider.setProviderTel(providerYel);
		provider.setProviderLoc(providerLoc);
		
		try {
			// ����usermessagedao������Ϊinset()���������û�ע������������ע���Ƿ�ɹ�
			if(providerId != null &&!"".equals(providerId)) {
				ProviderDao dao = new ProviderDao();
				int update = dao.update(provider);
				if (update > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('�޸ĳɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectprovider"));
				} else {
					req.setAttribute("message", "<script>alert('�޸�ʧ��!');</script>");
					req.getRequestDispatcher("/supermarket/provideradd").forward(req, resp);
				}
			}else {
				ProviderDao dao = new ProviderDao();
				int insert = dao.insert(provider);
				if (insert > 0) {
					HttpSession session=req.getSession(true);
					req.setAttribute("message", "<script>alert('��ӳɹ�!');</script>");
					resp.sendRedirect(resp.encodeRedirectURL("/supermarket/selectprovider"));
				} else {
					req.setAttribute("message", "<script>alert('���ʧ��!');</script>");
					req.getRequestDispatcher("/supermarket/provideradd").forward(req, resp);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}

}
