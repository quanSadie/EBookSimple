package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.BookDAO;
import Model.Book;

@WebServlet("/uploadBook")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SAVE_DIRECTORY = "uploadDir";

	public UploadBookServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("addbook.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String b_author = request.getParameter("book_author");
			String b_title = request.getParameter("nameP");
			String b_price = request.getParameter("bprice");
			String b_category = request.getParameter("categoryID");
			String b_isbn = request.getParameter("b_isbn");
			String b_description = request.getParameter("bdescription");
			String personID = request.getParameter("this_id");

			BookDAO dao = new BookDAO();
			// Đường dẫn tuyệt đối tới thư mục gốc của web app.
			String appPath = "C:\\Users\\admin\\eclipse-workspace\\BookStore\\src\\main\\webapp";
			appPath = appPath.replace('\\', '/');

			// Thư mục để save file tải lên.
			String fullSavePath = null;
			if (appPath.endsWith("/")) {
				fullSavePath = appPath + SAVE_DIRECTORY;
			} else {
				fullSavePath = appPath + "/" + SAVE_DIRECTORY;
			}

			// Tạo thư mục nếu nó không tồn tại.
			File fileSaveDir = new File(fullSavePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			String imgPath = "";
			String pdfPath = "";
			String filePath = "";
			// Danh mục các phần đã upload lên (Có thể là nhiều file).
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName.length() > 0) {
					filePath = fullSavePath + File.separator + fileName;
					System.out.println("Write attachment to file: " + filePath);
					if (filePath.endsWith("pdf")) {
						pdfPath = filePath;
					} else {
						imgPath = filePath;
					}
					// Ghi vào file.
					part.write(filePath);
				}
			}

			Book b = new Book(b_title, imgPath, b_author, b_category, b_price, b_description, b_isbn, pdfPath);
			dao.AddBook(b);
			// Upload thành công.
			response.sendRedirect("StaffBookServlet");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error: " + e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("addbook.jsp");
			dispatcher.forward(request, response);
		}
	}

	private String extractFileName(Part part) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				// file1.zip
				// file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

}
