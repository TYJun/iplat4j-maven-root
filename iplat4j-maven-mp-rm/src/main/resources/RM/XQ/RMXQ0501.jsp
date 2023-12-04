<%@ page contentType = "application/pdf;charset=UTF-8" %>
<%@ page import = "com.baosight.iplat4j.core.ei.EiInfo" %>
<%@ page import = "com.baosight.wilp.rm.common.RmUtils" %>
<%@ page import = "net.sf.jasperreports.engine.*" %>
<%@ page import = "net.sf.jasperreports.engine.export.JRPdfExporter" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.core.io.Resource" %>
<%@ page import="org.springframework.core.io.support.PathMatchingResourcePatternResolver" %>
<%@ page import="org.springframework.core.io.support.ResourcePatternResolver" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
	out.clear();      //清空缓存的内容
	out=pageContext.pushBody();  //更新PageContext的out属性的内容
	try {
		//获取容器资源解析器
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("jasperReport/requireApply.jrxml");
		if(resources == null || resources.length == 0){
			throw new Exception("获取报表文件失败");
		}
		InputStream stream = resources[0].getInputStream();
		// 编辑报表,生成的 .jasper 文件
		JasperReport report = JasperCompileManager.compileReport(stream);

		//获取参数
		List<JasperPrint> jasperList = new ArrayList<JasperPrint>();
		String[] planNos = request.getParameter("requirePlanNos").split(",");
		for (String planNo : planNos) {
			EiInfo invoke = RmUtils.invoke("RMXQ0501", "queryRequireFlow", Arrays.asList("planNo"), planNo);
			Map parameters = (Map) invoke.get("parameters");
			String requestURL = request.getRequestURL().toString();
			String baseURI = StringUtils.substringBefore(requestURL, request.getContextPath()) + request.getContextPath();
			if (StringUtils.isNotBlank(RmUtils.toString(parameters.get("applySign")))) {
				parameters.put("applySign", baseURI + parameters.get("applySign"));
			}
			if (StringUtils.isNotBlank(RmUtils.toString("approvalSign"))) {
				parameters.put("approvalSign", baseURI + parameters.get("approvalSign"));
			}
			if (StringUtils.isNotBlank(RmUtils.toString("zcSign"))) {
				parameters.put("zcSign", baseURI + parameters.get("zcSign"));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, new JREmptyDataSource());
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
		//RmUtils.invoke("SIRK01", "updatePrintFlag", new String[]{"enterBillNos"}, request.getParameter("enterBillNos") );
	} catch (Exception e) {
		e.printStackTrace();
	}
%>