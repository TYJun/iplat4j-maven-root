
package com.baosight.wilp.tc.cm.web;


import java.io.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
  * 上传图片
 * 
 * @Name ExcelController.java
 * @Description 
 * @Author hcd
 * @Date 2021年11月18日 上午10:13:05
 * @Version 1.0
 **/

@SuppressWarnings("serial")
@WebServlet("/pictureUpload")
@MultipartConfig
public class PictureUploadController extends HttpServlet {
	
		/**
		 * 上传图片
		 * 作者：hcd
		 * 入参：HttpServletRequest,HttpServletResponse 出参：void 处理：
		 * 1.判断类型，属于那种图片上传
		 * 2.获取文件路径
		 * 3.判断图片是否存在，如果存在将其移动到备份目录中
		 * 4.上传图片
		 * 
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        
	        /**
	         * 1.判断类型，属于那种图片上传
	         */
	        String type = request.getParameter("type");
	        String fname = null;
	        if("1".equals(type)) {
	        	fname = "bright-background";
	        }else if ("2".equals(type)) {
	        	fname = "login-form-background";
			}else if ("3".equals(type)) {
				fname = "login-logo";
			} else if("4".equals(type)){ // app的背景图片
				fname = "hospitalBg";
			}
	       
	        /**
	         * 2.获取文件路径
	         */
	        // path为工程根目录：（web工程名为test）D:\tomcat\apache-tomcat-8.0.50-windows-x64\apache-tomcat-8.0.50\webapps\test\
	        String path = this.getServletContext().getRealPath("/")+"iplatui" + File.separator +"img"+ File.separator +"login" + File.separator;
//	        String path1 = request.getSession().getServletContext().getRealPath("/")+"iplatui/img/login/test/";


	        
	        // 文件目录,没有则创建文件目录
	        File fileDir = new File(path);
	        if (!fileDir.exists()) {
	            fileDir.mkdirs();
	        }
	        
	        /**
	         * 3.判断图片是否存在，如果存在将其移动到备份目录中
	         */
	        // 如果图片已存在，将其移到备份文件夹中
	        File file = new File(path+fname+".png");
			if (file.isFile() && file.exists()) {
				// 创建备份文件目录
				String backupsPath = path+"backups" + File.separator;
		        File backupsFile = new File(backupsPath);
		        if (!backupsFile.exists()) {
		        	backupsFile.mkdirs();
		        }
				
				if (file.renameTo(new File(backupsPath+fname+".png"))) {
		            System.out.println("文件移动成功!");
		        } else {
		        	 System.out.println("文件移动失败!");
		        	 
		        }
			}

			/**
	         * 4.上传图片
	         */
	        // part代表一个文件
	        Part part = request.getPart("fileUpload");
	       
	        // h是要上传的文件的头：具体如下（上传桌面上的account.txt文件）
	        String h = part.getHeader("content-disposition");
//	  		String fname = part.getSubmittedFileName();
	        //substring是在获取文件的后缀，改名但是不改后缀
	        fname = fname + h.substring(h.lastIndexOf("."), h.length() - 1);
	        //按照路径上传图片(修改path可以改变文件在服务器中的存储位置）
	        part.write(path + File.separator + fname);
	        
	        response.getWriter().print("{\"msg\":\"success\"}");

	    }

	
}
