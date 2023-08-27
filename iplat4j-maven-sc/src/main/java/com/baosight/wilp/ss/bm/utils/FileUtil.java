package com.baosight.wilp.ss.bm.utils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import org.apache.commons.net.util.Base64;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil {

	/**
	 * Todo(复制图片，指定目录有同名文件就不管，没有就复制)
	 *
	 * @Title: copyFile
	 * @author xiajunqing
	 * @date: 2022/2/9 13:47
	 * @Param: filePath目标地址
	 * @Param: src源文件
	 */
	public static String copyPic(String filePath,File src) throws Exception {
		//判断源文件是否存在
		if (!src.exists()) {
			return "";
		}
		//判断目标文件是否存在
		File desc = new File(filePath);
		if (!desc.exists()) {
			//不存在复制文件
			copyFile(src,desc);
		}else {
			if (!desc.isFile()) {
				//不是文件，先删了再复制
				desc.delete();
				copyFile(src,desc);
			}
		}
		return filePath;
	}

	/**
	 * Todo(IO流复制文件)
	 *
	 * @Title: copyFile
	 * @author xiajunqing
	 * @date: 2022/2/9 14:48
	 * @Param:src来源
	 * @Param:desc目标
	 */
	public static void copyFile(File src,File desc) {
		//不存在复制文件
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(desc);
			byte [] bys = new byte[8192];
			int len;
			while(( len = fis.read(bys))!= -1){
				fos.write(bys, 0, len);//不管是读还是写,操作的都是同一个数组.
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				fis = null;
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				fos = null;
			}
		}

	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: createFile创建文件
	 * @Param
	 * @return:
	 */
	public static File createFile(String filePath) {
		File file = null;
		try {
			System.out.println(filePath);
			file = new File(filePath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	/**
	 * 创建文件夹
	 * */
	public static File createDir(String dirPath) throws Exception {
		File file = new File(dirPath);
		if (!file.exists()) {
			//不存在创建目录
			file.mkdirs();
		}else {
			if(!file.isDirectory()) {
				//不是目录删除文件
				file.delete();
				//创建目录
				file.mkdirs();
			}else {
				//是目录删除目录
				deleteDirectory(dirPath);
				//创建目录
				file.mkdirs();
			}
		}
		return file;
	}

	public static String GetImageStr(String picUrl) {
		if (StringUtil.isEmpty(picUrl)) {
			return "";
		}
		String fileName = picUrl.substring(picUrl.lastIndexOf("/") + 1);
		// System.out.println(fileName);
		InputStream in = null;

		byte[] data = null;

		try {
			File file = new File(picUrl);
			if (file.exists()) {
				in = new FileInputStream(picUrl);
				data = new byte[in.available()];
				in.read(data);
				in.close();

				byte[] encode = Base64.encodeBase64(data);
				return new String(encode);
			}
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * base64图片转字节数组
	 * 
	 * @param img
	 * @return byte[]
	 */
	public static byte[] castImg(String img) {
		// base64转化为字节数组
		return Base64.decodeBase64(img);
	}

	/**
	 * deleteFile:删除文件
	 */
	public static boolean deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				if (file.delete()) {
					System.out.println("删除文件" + filePath + "成功！");
					return true;
				} else {
					System.out.println("删除文件" + filePath + "失败！");
					return false;
				}
			} else {
				System.out.println("删除文件失败：" + filePath + "不存在！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir 要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileUtil.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else if (files[i].isDirectory()) {
				// 删除子目录
				flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * base64转文件
	 * 
	 * @param fileStr:base64字符串(完整)
	 * @param imgFilePath:文件保存路径
	 * @return boolean
	 */
	public static boolean basa64ToFile(String fileStr, String imgFilePath) {
		if (StringUtils.isBlank(fileStr)) {
			return false;
		} else {
			Base64 decoder = new Base64();
			OutputStream out = null;
			try {
				fileStr = fileStr.split(",")[1];
				byte[] decode = decoder.decode(fileStr);
				for (int i = 0; i < decode.length; i++) {
					if (decode[i] < 0) {
						decode[i] += 256;
					}
				}
				out = new FileOutputStream(imgFilePath);
				out.write(decode);
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Todo(文件转base64)
	 *
	 * @Title: fileToBase64
	 * @Param path
	 * @return: String
	 */
	public static String fileToBase64(String path)  {
		//路径为空
		if(StringUtil.isEmpty(path)){
			return null;
		}
		File file = new File(path);
		//文件不存在
		if(!file.exists()){
			return null;
		}
		FileInputStream in = null;
		String imgStr = null;
		try {
			in = new FileInputStream(file);
			byte[] buffer = new byte[(int)file.length()];
			in.read(buffer);
			imgStr = "data:image/jpeg;base64,"+ new BASE64Encoder().encode(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imgStr;
	}

	/**
	 * 图片保存
	 * 
	 * @param savePath:保存路径req.getSession().getServletContext().getRealPath("")+"picUpload/"
	 * @param picList:图片list
	 */
	public static ArrayList<HashMap<String,Object>> savePic(String savePath, ArrayList<String> picList) {
		ArrayList<HashMap<String,Object>> fileIdList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < picList.size(); i++) {
			String impStr = picList.get(i);
			if (StringUtils.isBlank(impStr)) {
				continue;
			}

			File files = new File(savePath);
			if (!files.isDirectory()) {
				System.out.println("没有该路径,创建目录"+savePath);
				files.mkdirs();
			}
			String picId = UUIDUtils.getUUID32();
			basa64ToFile(impStr, savePath + picId + ".jpg");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("picId",picId);
			map.put("impStr",impStr);
			fileIdList.add(map);
		}
		return fileIdList;
	}

	/**
	 * Todo(获取附件目录)
	 *	docRootDir+attachment
	 * @Title: getAttachmentPath
	 * @Param attachment
	 * @return: String
	 */
	public static String getAttachmentPath(String attachment){
		String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String destPath = docRootDir+ attachment;
		return destPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		getAttachmentPath("/sc/img/");
//		System.out.println(getAttachmentPath("/sc/img/"));
//
//		String s = fileToBase64("C:/Users/admin/Pictures/Wallpaper/Theme2/img6.jpg");
//		System.out.println(s);

		File files = new File("D:/aa/bb");
		if (!files.isDirectory()) {
			System.out.println("没有该路径");
			files.mkdirs();
		}
	}

}
