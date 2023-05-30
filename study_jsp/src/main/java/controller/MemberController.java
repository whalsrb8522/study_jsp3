package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService msvc;
	private RequestDispatcher dispatcher;
	private String destPage;
	
	private HttpSession ses;

	private int isOk;
	private MemberVO mvo;
	private MemberVO resultMvo;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String regdate;
	private String lastlogin;
	private boolean auth;

	public MemberController() {
		msvc = new MemberServiceImpl();
    }
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println(">>> MemberController > uri : " + uri);
		System.out.println(">>> MemberController > path : " + path);
		
		switch (path) {
		case "signIn":
			id = req.getParameter("id");
			password = req.getParameter("password");
			
			if (password == null || password == "") {
				req.setAttribute("msg", "비밀번호를 입력해주세요.");
			} else {
				mvo = new MemberVO(id, password);
				resultMvo = msvc.selectMember(mvo);
				ses = req.getSession();
				ses.setAttribute("ses", resultMvo);
				ses.setMaxInactiveInterval(10 * 60);
			}
			
			destPage = "/index.jsp";
			break;
		case "signUp_s1":
			destPage = "/member/signup.jsp";
			break;
		case "signUp_s2":
			id = req.getParameter("id");
			password = req.getParameter("password");
			name = req.getParameter("name");
			email = req.getParameter("email");
			phone = req.getParameter("phone");
			mvo = new MemberVO(id, password, name, email, phone);
			
			isOk = msvc.insertMember(mvo);
			
			System.out.println(">>> MemberController > insertMember() : " + (isOk > 0 ? "success" : "fail"));
			
			destPage = "/index.jsp";
			break;
		case "signOut":
			ses = req.getSession();
			ses.invalidate();
			
			destPage = "/index.jsp";
			break;
		case "modify_s1":
			id = req.getParameter("id");
			
			if (id == null) {
				ses = req.getSession();
				id = ((MemberVO) ses.getAttribute("ses")).getId();
			}
			
			mvo = new MemberVO(id);
			mvo = msvc.selectMember(mvo);
			req.setAttribute("mvo", mvo);
			
			destPage = "/member/modify.jsp";
			break;
		case "modify_s2":
			id = req.getParameter("id");
			password = req.getParameter("password");
			name = req.getParameter("name");
			email = req.getParameter("email");
			phone = req.getParameter("phone");
			mvo = new MemberVO(id, password, name, email, phone);
			
			if (password == null || password == "") {
				req.setAttribute("msg", "비밀번호를 입력해주세요.");
				destPage = "modify_s1";
			} else {
				isOk = msvc.updateMember(mvo);
				
				resultMvo = msvc.selectMember(mvo);
				
				ses = req.getSession();
				ses.setAttribute("ses", resultMvo);
				ses.setMaxInactiveInterval(10 * 60);
				
				destPage = "/index.jsp";
			}
			break;
		case "admin":
			List<MemberVO> listMvo = new ArrayList<MemberVO>(); 
			listMvo = msvc.selectAllMember();
			
			req.setAttribute("listMvo", listMvo);
			
			destPage = "/member/admin.jsp";
			break;
		case "withdrawal":
			id = req.getParameter("id");
			break;
		}
		
		dispatcher = req.getRequestDispatcher(destPage);
		dispatcher.forward(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		// doGet(request, response);
	}
}
