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

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService bsvc;
	private RequestDispatcher dispatcher;
	private String destPage;
	private int isOk;
	
	private BoardVO bvo;
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private int readcount;
       
    public BoardController() {
    	bsvc = new BoardServiceImpl();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setCharacterEncoding("UTF-8");
    	
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		
		switch (path) {
		case "list":
			List<BoardVO> listBvo = new ArrayList<BoardVO>();
			listBvo = bsvc.selectAllList();
			System.out.println(">>> BoardController > listBvo : " + listBvo);
			
			req.setAttribute("listBvo", listBvo);
			
			destPage = "/board/list.jsp";
			break;
		case "write_s1":
			destPage = "/board/write.jsp";
			break;
		case "write_s2":
			title = req.getParameter("title");
			writer = req.getParameter("writer");
			content = req.getParameter("content");
			
			if (title == null || title == "") {
				req.setAttribute("msg", "제목을 입력해주세요.");
			} else {
				bvo = new BoardVO(title, writer, content);
				isOk = bsvc.insertList(bvo);
			}

			destPage = "list";
			break;
		case "detail":
			bno = Integer.parseInt(req.getParameter("bno"));
			
			bvo = new BoardVO(bno);
			bvo = bsvc.selectList(bvo);
			
			req.setAttribute("bvo", bvo);
			
			destPage = "/board/detail.jsp";
			
			break;
		case "modify_s1":
			bno = Integer.parseInt(req.getParameter("bno"));
			
			bvo = new BoardVO(bno);
			bvo = bsvc.selectList(bvo);
			
			req.setAttribute("bvo", bvo);
			
			destPage = "/board/modify.jsp";
			
			break;
		case "modify_s2":
			bno = Integer.parseInt(req.getParameter("bno"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			
			bvo = new BoardVO(bno, title, content);
			isOk = bsvc.updateList(bvo);				
			
			destPage = "list";
			
			break;
		}
		
		dispatcher = req.getRequestDispatcher(destPage);
		dispatcher.forward(req, resp);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		//doGet(request, response);
	}
}
