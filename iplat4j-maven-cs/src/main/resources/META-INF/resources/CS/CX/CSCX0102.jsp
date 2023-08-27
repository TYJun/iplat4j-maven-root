<%@ page contentType = "application/pdf;charset=UTF-8" %>
<%@ page import = "com.baosight.iplat4j.core.ei.EiConstant" %>
<%@ page import = "com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import = "com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import = "com.baosight.iplat4j.core.service.soa.XLocalManager" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.export.JRPdfExporter" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.core.io.Resource" %>
<%@ page import="org.springframework.core.io.support.PathMatchingResourcePatternResolver" %>
<%@ page import="org.springframework.core.io.support.ResourcePatternResolver" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%	try {

		//获取容器资源解析器
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("jasperreports/csTaskReport.jrxml");
		if(resources == null || resources.length == 0){
			throw new Exception("获取报表文件失败");
		}
		InputStream stream = resources[0].getInputStream();
		// 编辑报表,生成的 .jasper 文件
		JasperReport report = JasperCompileManager.compileReport(stream);

		//配置数据库连接
		String url= PlatApplicationContext.getProperty("jdbc.url") ;
		String username= PlatApplicationContext.getProperty("jdbc.username") ;
		String password= PlatApplicationContext.getProperty("jdbc.password") ;
		Class.forName(PlatApplicationContext.getProperty("jdbc.driverClassName"));
		Connection conn = DriverManager.getConnection(url, username , password );

		// 创建一个报表列表
		List jasperPrintList = new ArrayList();
		// 创建一个工单号列表，用来存储所有前端传来的工单号
		List taskNoAll = new ArrayList();
		//获取参数
		EiInfo info = new EiInfo();
		String taskNo = request.getParameter("taskNo");
		// 当为多选的情况是遍历所有选择工单
		if (StringUtils.isNotBlank(taskNo) && taskNo.split(",").length > 1) {
			String[] taskNoList = taskNo.split(",");
			for (int i = 0; i < taskNoList.length; i++) {
				taskNoAll.add(taskNoList[i]);
				info.set("taskNo", taskNoList[i]);
				info.set(EiConstant.serviceName, "CSCX0102");
				info.set(EiConstant.methodName, "queryTaskForReport");
				EiInfo outInfo = XLocalManager.call(info);
				Map<String, Object> parameters = (Map<String, Object>) outInfo.get("parameters");
				// 生成打印报表
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
				// 添加到报表列表
				jasperPrintList.add(jasperPrint);
			}
			// 当所选项为单选时无需遍历
		}else if(StringUtils.isNotBlank(taskNo)){
			taskNoAll.add(taskNo);
			info.set("taskNo", taskNo);
			info.set(EiConstant.serviceName, "CSCX0102");
			info.set(EiConstant.methodName, "queryTaskForReport");
			EiInfo outInfo = XLocalManager.call(info);
			Map<String, Object> parameters = (Map<String, Object>) outInfo.get("parameters");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
			jasperPrintList.add(jasperPrint);
		}

		// 字节数组输出流在内存中创建一个字节数组缓冲区
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 创建pdf输出对象
		JRPdfExporter exporter = new JRPdfExporter();
		// 对输出对象进行属性设置
		// 通过一个list来存储多个JasperPrint对象，从而实现多个报表模板的打印
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		// 进行对象输出
		exporter.exportReport();
		byte[] outputFile = baos.toByteArray();
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(outputFile,0,outputFile.length);
		// 生成pdf后默认视为已经将该工单打印。
		info.set("taskNoAll",taskNoAll);
		info.set(EiConstant.serviceName, "CSCX0102");
		info.set(EiConstant.methodName, "changePrintStatusCode");
		XLocalManager.call(info);
		outStream.flush();
		outStream.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>