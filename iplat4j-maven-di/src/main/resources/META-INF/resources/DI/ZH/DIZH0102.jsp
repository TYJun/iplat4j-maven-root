<%@ page contentType = "application/pdf;charset=UTF-8" %>
<%@ page import = "com.baosight.iplat4j.core.ei.EiConstant" %>
<%@ page import = "com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import = "com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import = "com.baosight.iplat4j.core.service.soa.XLocalManager" %>
<%@ page import="net.sf.jasperreports.engine.JasperCompileManager" %>
<%@ page import="net.sf.jasperreports.engine.JasperReport" %>
<%@ page import="net.sf.jasperreports.engine.JasperRunManager" %>
<%@ page import="org.springframework.core.io.Resource" %>
<%@ page import="org.springframework.core.io.support.PathMatchingResourcePatternResolver" %>
<%@ page import="org.springframework.core.io.support.ResourcePatternResolver" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<%@ page import="java.util.Map" %>
<%	try {

		//获取容器资源解析器
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("jasperreports/diTaskReport.jrxml");
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
		//获取参数
		EiInfo info = new EiInfo();
		info.set("taskCode", request.getParameter("taskCode"));
		info.set(EiConstant.serviceName, "DIZH0102");
		info.set(EiConstant.methodName, "queryTaskForReport");
		EiInfo outInfo = XLocalManager.call(info);
		Map<String, Object> parameters = (Map<String, Object>) outInfo.get("parameters");

		//输出PDF预览
		byte [] bytes= new byte[0];
	    bytes = JasperRunManager.runReportToPdf(report, parameters, conn);

		response.setContentType( "application/pdf" );
		response.setContentLength(bytes.length);

		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes,0,bytes.length);
		outStream.flush();
		outStream.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>