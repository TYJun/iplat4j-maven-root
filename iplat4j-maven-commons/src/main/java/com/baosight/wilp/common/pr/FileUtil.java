package com.baosight.wilp.common.pr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.net.util.Base64;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.bpm.util.StringUtil;

public class FileUtil {

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
		if (StringUtil.isEmpty(fileStr)) {
			return false;
		} else {
			Base64 decoder = new Base64();
			OutputStream out = null;
			byte[] decode = null;
			try {
			    if(fileStr.contains(",")) {
			        fileStr = fileStr.split(",")[1];
			        decode = decoder.decode(fileStr);
			        for (int i = 0; i < decode.length; i++) {
			            if (decode[i] < 0) {
			                decode[i] += 256;
			            }
			        }
			    }else {
			        decode = decoder.decode(fileStr);
                    for (int i = 0; i < decode.length; i++) {
                        if (decode[i] < 0) {
                            decode[i] += 256;
                        }
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
	 * 图片保存
	 * 
	 * @param savePath:保存路径req.getSession().getServletContext().getRealPath("")+"picUpload/"
	 * @param picList:图片list
	 */
	public static ArrayList<String> savePic(String savePath, String picId, ArrayList<String> picList) {
		ArrayList<String> fileIdList = new ArrayList<String>();
		if(picList.size() > 1) {
		    for (int i = 0; i < picList.size(); i++) {
	            String impStr = picList.get(i);
	            if (StringUtils.isBlank(impStr)) {
	                continue;
	            }
	            
	            File files = new File(savePath);
	            if (!files.isDirectory()) {
	                files.mkdir();
	            }
	            String picIds = UUIDUtils.getUUID32();
	            basa64ToFile(impStr, savePath + picIds + ".jpg");
	            fileIdList.add(picIds);
	        }
		    return fileIdList;
		}
		for (int i = 0; i < picList.size(); i++) {
		    String impStr = picList.get(i);
		    if (StringUtils.isBlank(impStr)) {
		        continue;
		    }
		    
		    File files = new File(savePath);
		    if (!files.isDirectory()) {
		        files.mkdirs();
		    }
		    //String picId = UUIDUtils.getUUID32();
		    basa64ToFile(impStr, savePath + picId + ".jpg");
		    fileIdList.add(picId);
		}
		return fileIdList;
	}


}
