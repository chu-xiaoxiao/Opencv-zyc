package com.zyc.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import com.zyc.opencv.DB;
import com.zyc.opencv.DetectFaceDemo;
import com.zyc.opencv.Sift;

import Decoder.BASE64Decoder;

/**
 * Servlet implementation class UpLoad
 */
@WebServlet("/UploadServlet")
@MultipartConfig(location = "c:/temp")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.loadLibrary("opencv_java2411");
		response.setContentType("text/html;charset=UTF-8");		
		String root = request.getServletContext().getRealPath("/upload");
/*		Part part = request.getPart("file");
		String root = "/upload";
		File file = new File(root);
		if(!file.exists()){
			file.mkdirs();
		}
		String headname = part.getHeader("content-disposition");
		String ext = headname.substring(headname.lastIndexOf("."),headname.length()-1);*/
		String imgData =null;
		if(request.getParameter("data")!=null){
			imgData = request.getParameter("data");
		}else{
			request.getRequestDispatcher("/error.html").forward(request,response);
		}
		//System.out.println(imgData);
	//	String fileName =root+"/temp"+".jpg";
		ServletContext application = this.getServletContext();
		int counter =(int) application.getAttribute("counter");
		counter=(++counter)%100;
		application.setAttribute("counter", counter);
		String fileName2="temp2/"+application.getAttribute("counter")+"temp.jpg";
		//part.write(filename);
		String basePath = request.getSession().getServletContext().getRealPath("/images/");
		System.out.println(basePath);
		String filePath = basePath;
		BASE64Decoder decoder = new BASE64Decoder();
		try { 
			byte[] bytes1 = decoder.decodeBuffer(imgData);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			BufferedImage image = ImageIO.read(bais);
			if (image != null)
			{
				if(filePath!=null&&fileName2!=null){
					ImageIO.write(image, "png", new File(filePath , fileName2));
				}
			}

			} catch (IOException e) {
			e.printStackTrace();
			}
		
		DetectFaceDemo detectFaceDemo = new DetectFaceDemo();
	//	Mat image = Highgui.imread("c:\\temp\\upload\\temp.jpg");
		System.out.println(filePath+fileName2);
		Mat image = Highgui.imread(filePath+fileName2);
		System.out.println(image);
		image = detectFaceDemo.run(image);
		if(image == null){
			System.out.println("截取错误");
			request.getRequestDispatcher("/error.html").forward(request,response);
			request.getSession().setAttribute("flag", "0");
			return;
		}
		String string = UUID.randomUUID().toString();	
		String name =request.getParameter("name");
		String minName;
		//minName  = "C:\\apache-tomcat-9.0.0.M15\\webapps\\TestServelt\\images\\"+string+"Min.jpg";
		minName  = filePath+string+"Min.jpg";
		System.out.println(minName);
		Highgui.imwrite(minName, image);
		Sift sift = new Sift(); 
		image=sift.getSift(image);
	//	minName  = "C:\\apache-tomcat-9.0.0/.M15\\webapps\\TestServelt\\images\\"+string+"SiftMin.jpg";
		minName  = filePath+string+"SiftMin.jpg";
		//Highgui.imwrite(minName, image);
		String sql = "INSERT INTO image VALUES (?,?,?)";
		DB db = new DB(sql);
		try {
			db.pst.setString(1,request.getParameter("name"));
			System.out.println(name);
			db.pst.setString(2, "/images/"+string+"Min.jpg");
			db.pst.setString(3, image.dump());
			db.pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		//Highgui.imwrite(minName, image);
		request.setAttribute("name1", name);
		 request.getRequestDispatcher("/zhucechenggong.jsp").forward(request,response);
	}
}
