package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int isOk;
	private CommentService csv;
	
	// 목적지 주소로 보내는 destPage 쓰지 않음
	public CommentController() {
		csv = new CommentServiceImpl();
    }
    
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");
    	
    	// /cmt/post/1, /cmt/modify/1		=> RestAPI 방식    	
    	String uri = req.getRequestURI();
    	String pathUri = uri.substring("/cmt/".length()); 
    	String path = pathUri;
    	String pathVar = "";
    	if (pathUri.contains("/")) {
			path = pathUri.substring(0, pathUri.lastIndexOf("/"));
			pathVar = pathUri.substring(pathUri.lastIndexOf("/") + 1);
		}
    	
    	switch (path) {
		case "post":
			try {
				// JS에서 보낸 데이터를 StringBuffer로 읽어들이는 작업
				StringBuffer sb = new StringBuffer();
				BufferedReader br = req.getReader();	// 댓글 객체
				String line = "";
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				// 객체로 생성
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer= jsonObj.get("writer").toString();
				String content= jsonObj.get("content").toString();
				CommentVO cvo = new CommentVO(bno, writer, content);
				isOk = csv.post(cvo);
				
				// 결과 전송
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				int bno = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bno);
				
				// JSON 형태로 변환해서 printWriter
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				JSONArray jsonObjList = new JSONArray();
				
				for (int i = 0; i < list.size(); i++) {
					jsonObjArr[i] = new JSONObject();		// key:value
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("regdate", list.get(i).getRegdate());
					jsonObjList.add(jsonObjArr[i]);
				}
				String jsonData = jsonObjList.toJSONString();
				
				PrintWriter out = res.getWriter();
				out.print(jsonData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case "remove":
			try {
				int cno = Integer.parseInt(req.getParameter("cnoVal"));		// 쿼리 스트링 방식
				
				isOk = csv.remove(cno);
				
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case "modify":
			try {
				StringBuffer sb = new StringBuffer();
				BufferedReader br = req.getReader();
				String line = "";
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String content = jsonObj.get("content").toString();
				CommentVO cvo = new CommentVO(cno, content);
				
				isOk = csv.modify(cvo);
				
				// 결과 전송
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
    	service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		service(request, response);
	}
}
