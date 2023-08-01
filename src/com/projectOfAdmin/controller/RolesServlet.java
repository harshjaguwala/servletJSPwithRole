package com.projectOfAdmin.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.projectOfAdmin.Dao.RolesDao;
import com.projectOfAdmin.model.Userinfo;

@WebServlet("/")
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolesDao rolesdao = new RolesDao();
	Userinfo userinfo;

	public RolesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/register":
			try {
				registerPage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/ShowRegisterPage":
			try {
				showRegisterPage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/Afterlogin":
			try {
				checkLogin(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/Showalluser":
			try {
				showalluser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/ShowLoginuser":
			try {
				showLoginuser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/DeleteUserinfo":
			try {
				deleteUserinfo(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/editUserinfo":
			try {
				showEditForm(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/updateUserinfo":
			try {
				updateUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/Logout":
			try {
				logout(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				showLoginPage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			session = null;
		}
		request.getRequestDispatcher("WEB-INF/views/showLoginPage.jsp").include( request, response);
	}

	private void showRegisterPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/showRegisterPage.jsp");
		rd.forward(request, response);
	}

	private void registerPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String phoneno = request.getParameter("phoneno");
		String password = request.getParameter("password");
		String isAdmincheck = request.getParameter("IsAdmincheck");
		int isAdmin = (isAdmincheck != null && !isAdmincheck.isEmpty()) ? Integer.parseInt(isAdmincheck) : 2;
		Userinfo ui = new Userinfo(isAdmin, name, phoneno, password);
		rolesdao.registerUser(ui);
		response.sendRedirect("ShowLoginPage");
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/showLoginPage.jsp");
		rd.forward(request, response);
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		System.out.println("name " + name);
		String password = request.getParameter("password");
		System.out.println("password " + password);
		Userinfo result = rolesdao.selectUserinfo(name, password);
		if (result.getUserid() == -1) {
			response.sendRedirect("ShowLoginPage");
		}
		if (result.getRoleid() == 1) {
			session.setAttribute("name", result.getName());
			session.setAttribute("password", result.getPassword());
			session.setAttribute("userid", result.getUserid());
			response.sendRedirect("Showalluser");
		} else {
			session.setAttribute("name", result.getName());
			session.setAttribute("password", result.getPassword());
			session.setAttribute("userid", result.getUserid());
			response.sendRedirect("ShowLoginuser");
		}
	}

	private void showalluser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
		List<Userinfo> listofuserinfo = rolesdao.listAllUserinfo();
		request.setAttribute("listofuserinfo", listofuserinfo);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/showAlluser.jsp");
		rd.forward(request, response);
	}

	private void showLoginuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String password = (String) session.getAttribute("password");
		int userid = (int) session.getAttribute("userid");
		Userinfo getOneUser = rolesdao.selectOneUser(name, password, userid);
		System.out.println("user = " + getOneUser);
		request.setAttribute("getOneUser", getOneUser);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/showAlluser.jsp");
		rd.forward(request, response);
	}

	private void deleteUserinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		rolesdao.deleteUserinfo(id);
		response.sendRedirect("Showalluser");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Userinfo existinguser = rolesdao.selectUserById(id);
		System.out.println("exist " + existinguser);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/showRegisterPage.jsp");
		request.setAttribute("existinguser", existinguser);
		rd.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phoneno = request.getParameter("phoneno");
		Userinfo userinfo = new Userinfo(name, phoneno, password, id);
		rolesdao.updateUserinfo(userinfo);
		response.sendRedirect("Showalluser");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
