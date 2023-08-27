<%@ page contentType = "application/pdf;charset=UTF-8" %>
<%@ page import = "net.sf.jasperreports.engine.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>
<%@ page import="org.springframework.core.io.support.ResourcePatternResolver" %>
<%@ page import="org.springframework.core.io.support.PathMatchingResourcePatternResolver" %>
<%@ page import="org.springframework.core.io.Resource" %>
<%@ page import="com.baosight.iplat4j.core.data.ibatis.dao.Dao" %>
<%@ page import="com.baosight.wilp.si.common.SiUtils" %>
<%@ page import="com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="net.sf.jasperreports.engine.export.JRPdfExporter" %>
<%
	Connection conn = null;
	out.clear();      //清空缓存的内容
	out=pageContext.pushBody();  //更新PageContext的out属性的内容
	try {
		//获取容器资源解析器
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("jasperReport/outBill.jrxml");
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
		conn = DriverManager.getConnection(url, username , password );
		//获取参数
		List<JasperPrint> jasperList = new ArrayList<JasperPrint>();
		String[] outBillNos = request.getParameter("outBillNos").split(",");
		for (String outBillNo : outBillNos) {
			EiInfo invoke = SiUtils.invoke(new EiInfo(), "SICK0104", "queryOutBill", new String[]{"outBillNo"}, outBillNo);
			Map parameters = (Map) invoke.get("parameters");
			String requestURL = request.getRequestURL().toString();
			String baseURI = StringUtils.substringBefore(requestURL, request.getContextPath()) + request.getContextPath();
			if(StringUtils.isNotBlank(SiUtils.isEmpty(parameters.get("claimMan")))) {
				parameters.put("claimMan", baseURI + parameters.get("claimMan"));
			}
			if(StringUtils.isNotBlank(SiUtils.isEmpty(parameters.get("stockManager")))) {
				parameters.put("stockManager", baseURI + parameters.get("stockManager"));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, conn);
			jasperList.add(jasperPrint);
		}

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		//设置导出哪个模板
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperList);
		//设置导出流模板(把模板转换成字节流)  这里用内存字节流，保存在内存中，若用其他如outputStream,浏览器器默认下载
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteOut);
		exporter.exportReport();//导出文件

		//输出PDF预览
		byte [] bytes= new byte[0];
		bytes =byteOut.toByteArray();
		response.setContentType( "application/pdf" );
		response.setContentLength(bytes.length);

		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes,0,bytes.length);
		outStream.flush();
		outStream.close();

		//将打印标记改为已打印
		SiUtils.invoke(null, "SICK01", "updatePrintFlag", new String[]{"outBillNos"}, request.getParameter("outBillNos") );
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
%>