package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService bsvc;
	private RequestDispatcher dispatcher;
	private String destPage;
	private int isOk;
	private String savePath;
	
	private BoardVO bvo;
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private String image; 
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
//			List<BoardVO> listBvo = new ArrayList<BoardVO>();
//			listBvo = bsvc.selectAllList();
//			System.out.println(">>> BoardController > listBvo : " + listBvo);
//			
//			req.setAttribute("listBvo", listBvo);
//			
//			destPage = "/board/list.jsp";
			
			int pageNo = 1;
			int qty = 10;
			
			String type = req.getParameter("type");
			String keyword = req.getParameter("keyword");
			
			if(req.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(req.getParameter("pageNo"));
				qty = Integer.parseInt(req.getParameter("qty"));
			}
			
			PagingVO pgvo = new PagingVO(pageNo, qty);
			pgvo.setType(type);
			pgvo.setKeyword(keyword);
			
			int totCount = bsvc.selectAllCount(pgvo);
			
			List<BoardVO> listBvo = bsvc.selectPageList(pgvo); 
			PagingHandler ph = new PagingHandler(pgvo, totCount);
			req.setAttribute("pgh", ph);
			req.setAttribute("listBvo", listBvo);
			
			destPage="/board/list.jsp";
			
			break;
		case "write_s1":
			destPage = "/board/write.jsp";
			break;
		case "write_s2":
//			title = req.getParameter("title");
//			writer = req.getParameter("writer");
//			content = req.getParameter("content");
//			
//			if (title == null || title == "") {
//				req.setAttribute("msg", "제목을 입력해주세요.");
//			} else {
//				bvo = new BoardVO(title, writer, content);
//				isOk = bsvc.insertList(bvo);
//			}
//
//			destPage = "list";
			
			try {
				// 파일을 업로드할 물리적인 경로
				savePath = getServletContext().getRealPath("_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				// 파일의 저장위치를 담고있는 객체를 저장
				fileItemFactory.setRepository(fileDir);		
				// 파일 저장을 위한 임시 메모리 용량 설정 : byte 단위
				fileItemFactory.setSizeThreshold(2 * 1024 * 1024);		
				
				bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("UTF-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("UTF-8"));
						break;
					case "content":
						bvo.setContent(item.getString("UTF-8"));
						break;
					case "image":
						// 이미지 유무 체크
						if (item.getSize() > 0) {		// 데이터의 크기를 이용하여 유무 결정
							// 경로를 포함한 파일 이름
							String fileName = item.getName().substring(item.getName().lastIndexOf("/") + 1);
							fileName = System.currentTimeMillis() + "_" + fileName;
							
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							System.out.println(uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath);
								bvo.setImage(fileName);
								
								// 섬네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75,  75).toFile(new File(fileDir + File.separator + "th_" + fileName));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						break;
					}
				}
				
				isOk = bsvc.insertList(bvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			destPage = "list";
			
			break;
		case "detail":
			bno = Integer.parseInt(req.getParameter("bno"));
			
			bvo = new BoardVO(bno);
			bvo = bsvc.selectDetail(bvo);
			
			req.setAttribute("bvo", bvo);
			
			destPage = "/board/detail.jsp";
			
			break;
		case "modify_s1":
			bno = Integer.parseInt(req.getParameter("bno"));
			
			bvo = new BoardVO(bno);
			bvo = bsvc.selectDetail(bvo);
			
			req.setAttribute("bvo", bvo);
			
			destPage = "/board/modify.jsp";
			
			break;
		case "modify_s2":
//			bno = Integer.parseInt(req.getParameter("bno"));
//			title = req.getParameter("title");
//			content = req.getParameter("content");
//			
//			bvo = new BoardVO(bno, title, content);
//			isOk = bsvc.updateList(bvo);				
//			
//			destPage = "list";
			
			try {
				savePath = getServletContext().getRealPath("_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2 * 1024 * 1024);
				
				bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				 
				String old_file = null;
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("UTF-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("UTF-8"));
						break;
					case "content":
						bvo.setContent(item.getString("UTF-8"));
						break;
					case "image":
						old_file = item.getString("UTF-8");
						break;
					case "newImage":
						if(item.getSize() > 0) {		// 새로운 파일이 등록됨
							if(old_file != null) {
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator) + 1);
							
							//실제 저장 이름
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							
							try {
								item.write(uploadFilePath);
								bvo.setImage(fileName);
								
								Thumbnails.of(uploadFilePath).size(75,  75).toFile(new File(fileDir + File.separator + "th_" + fileName));
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {		// 새로운 파일을 넣지 않았다면
							// 기존 파일을 다시 bvo 객체에 저장
							bvo.setImage(old_file);
						}
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			isOk = bsvc.updateList(bvo);
			
			destPage = "list";
			
			break;
		case "delete":
//			bno = Integer.parseInt(req.getParameter("bno"));
//			
//			isOk = bsvc.deleteBoard(bno);
//			
//			destPage = "/brd/list";

			bno = Integer.parseInt(req.getParameter("bno"));
			image = bsvc.getFileName(bno);
			
			if (bvo.getImage() != null) {
				savePath = getServletContext().getRealPath("_fileUpload");
				File fileDir = new File(savePath);
				
				File deleteFilePath = new File(fileDir + File.separator + image);
				File deleteThumbFilePath = new File(fileDir + File.separator + "th_" + image);
				
				deleteFilePath.delete();
				deleteThumbFilePath.delete();
			} 
			
			isOk = bsvc.deleteBoard(bno);
			
			destPage = "/brd/list";
			 
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
