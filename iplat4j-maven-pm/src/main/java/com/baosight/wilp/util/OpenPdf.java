package com.baosight.wilp.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
public class OpenPdf {
	@RequestMapping("/pmpdf")
	public void openFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		String fileName = (String) request.getParameter("fileName");
		String path = System.getProperty("user.dir");
		String classPath = path + File.separator + "upload/project/" + fileName;
		System.out.println(classPath);
		FileInputStream input = new FileInputStream(classPath);

		byte[] data = new byte[input.available()];

		input.read(data);

		response.getOutputStream().write(data);

		input.close();
	}
}
