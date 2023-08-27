package com.baosight.wilp.tc.cm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 科室档案信息管理.
 * <p>
 * 页面初始化方法, 查询功能, 启用科室, 停用科室, 根据科室编码查询是否该科室有员工信息, 树结构查询方法，根据科室ID更新院区编码.
 * </p>
 *
 * @Title ServiceTCCM01.java
 * @Author hcd
 * @Date 2021-11-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceTCCM01 extends ServiceBase {

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用query()方法
	 *
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * @Title modifyTheme
	 * @Author chunchen2
	 * @Description //换肤 css默认存放目录换成项目根目录的theme文件夹下面
	 * @Date 14:12 2022/1/13
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo modifyTheme(EiInfo inInfo) throws IOException {

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();

		// 获取服务器的真实根目录
		String realPath = request.getSession().getServletContext().getRealPath("/");

		// 要替换的皮肤css样式存储的路径
		String cssDirPath = realPath  + "theme";

		// 皮肤css样式最终存储的路径, 需要在项目的根目录才能生效
		String generateCssDirPath = realPath + "iplatui" + File.separator + "css";

		File generateCssDir = new File(generateCssDirPath);
		if(!generateCssDir.exists()){
			generateCssDir.mkdirs();
		}

		String theme = inInfo.getString("theme");
		String cssFileName = "iplat.ui." + theme + ".min.css";

		// 要更换的css样式文件
		File updateCssFile = new File(cssDirPath + File.separator + cssFileName);
		if(!updateCssFile.exists()){// css样式不存在，无法进行换肤的操作
			inInfo.setStatus(-1);
			inInfo.setMsg("换肤样式不存在，无法换肤！");
			return inInfo;
		}

		// iplat.ui.ant.min.css 文件，系统使用的默认样式
		File generateCssFile = new File(generateCssDirPath + File.separator + "iplat.ui.ant.min.css");

		if(generateCssFile.exists()){ // 存在样式
			// 1. 首先将样式备份
			String yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			File bakOriginCssFile = new File(generateCssDirPath + File.separator + "iplat.ui.ant.min.css_" + yyyyMMddHHmmss);
			Files.copy(generateCssFile.toPath(), bakOriginCssFile.toPath());

			// 删除原来的样式
			generateCssFile.delete();
		}

		// 2. 将新样式替换
		Files.copy(updateCssFile.toPath(), generateCssFile.toPath());

		// 3. 返回修改结果
		inInfo.setStatus(0);
		inInfo.setMsg("主题颜色修改成功,清刷新界面!");

		return inInfo;
	}
	
	/**
	 * 修改主题颜色
	 * 作者：hcd
	 * 入参：EiInfo（）
	 * 出参：EiInfo（）
	 * 处理：
	 * 1.获取目标文件和原文件路径
	 * 2.判断主题颜色
	 * 3.判断目标文件目录,没有则创建目标文件目录
	 * 4.将原文件复制到目标文件夹中。如果目标文件夹中存在，则先删除
	 * 5.返回操作结果
	 */
	public EiInfo modifyTheme1(EiInfo inInfo) throws IOException {
		
		/**
		 * 1.获取目标文件和原文件路径
		 */
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //目标文件路径
        String targetPath = request.getSession().getServletContext().getRealPath("/")+"iplatui\\css\\";
        //原文件路径
        String originalPath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\classes\\com\\baosight\\wilp\\tc\\cm\\web\\";
        
        /**
		 * 2.判断主题颜色
		 */
        String theme = inInfo.getString("theme");
        String fname = null;
        if("1".equals(theme)) {
        	fname = "iplat.ui.lanse.min.css";
        }
        if("2".equals(theme)) {
        	fname = "iplat.ui.qingse.min.css";
        }
       
        /**
		 * 3.判断目标文件目录,没有则创建目标文件目录
		 */
        File fileDir = new File(targetPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        
        /**
		 * 4.将原文件复制到目标文件夹中。如果目标文件夹中存在，则先删除
		 */
        File originalFile = new File(originalPath+fname);
        File targetFile = new File(targetPath+"iplat.ui.ant.min.css");
        if (!targetFile.exists()) {
			Files.copy(originalFile.toPath(), targetFile.toPath());
        }else {
			targetFile.delete();
			Files.copy(originalFile.toPath(), targetFile.toPath());
		}

		/**
		 * 5.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("主题颜色修改成功,清刷新界面!");
		return inInfo;
	}

	
}
