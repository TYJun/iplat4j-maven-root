package com.baosight.wilp.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.basic.struct.IgnoreCaseMap;
import com.baosight.wilp.utils.UUID;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/frame/servlet/uploadImage"})
@MultipartConfig
public class UploadImageServlet extends HttpServlet
{
    
    /**
     * 文件上传操作
     */
    @Override   
    protected void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException 
    {
        //判断文件数量
        Part pFilePart=pRequest.getPart("file");
        //保存文件
        try 
        {
            //获取文件保存路径
            String tBasePath="/image/module/frame/"+(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            String tRealPath=pRequest.getRealPath(tBasePath);
            File pBaseDirFile=new File(tRealPath);
            if(!pBaseDirFile.exists())
            {
                pBaseDirFile.mkdirs();
            }
            String tReturnPath=createNewFileName(tBasePath, pFilePart).replaceAll("\\\\","/");
            FileOutputStream pOutStream=new FileOutputStream(pRequest.getRealPath(tReturnPath));
            InputStream pInStream=pFilePart.getInputStream();
            byte[] pBuffer=new byte[4096];
            try
            {
                while(true)
                {
                    int iRead=pInStream.read(pBuffer);
                    if(iRead<=0)
                    {
                        break;
                    }
                    pOutStream.write(pBuffer,0,iRead);
                }
            }
            finally
            {
                pInStream.close();
                pOutStream.close();
            }
            if(tReturnPath.indexOf("/")==0)
            {
                tReturnPath=tReturnPath.substring(1);
            }
            pResponse.setContentType("text/pain");
            pResponse.getOutputStream().write(tReturnPath.getBytes("utf-8"));
            try
            {
                Thread.sleep(500L);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } 
        catch (Throwable e) 
        {
            e.printStackTrace();
            Logger.getRootLogger().error("处理文件上传出错", e);
            pResponse.setStatus(500);
        }
    }

    
    /**
     * GET方法重载，不支持，不支持，返回403
     */
    @Override
    protected void doGet(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException 
    {
        pResponse.setStatus(403);
    }
    
    /**
     * HEAD方法重载，不支持，返回403
     */
    @Override
    protected void doHead(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        pResponse.setStatus(403);
    }

    /**
     * PUT法重载，不支持，返回403
     */
    @Override
    protected void doPut(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        pResponse.setStatus(403);
    }

    /**
     * DELETE法重载，不支持，返回403
     */
    @Override
    protected void doDelete(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        pResponse.setStatus(403);
    }

    /**
     * TRACE法重载，不支持，返回403
     */
    @Override
    protected void doTrace(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException 
    {
        pResponse.setStatus(403);
    }

    /**
     * OPTIONS法重载，不做任可操作，返回200
     */
    @Override
    protected void doOptions(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException 
    {
        pResponse.setStatus(200);
    }

    /**
     * 初始化操作，设置ServletContext的基本路径
     */
    @Override
    public void init(ServletConfig pConfig) throws ServletException 
    {
        super.init(pConfig);
    }
    
    /**
     * 创建新的文件名对象 
     * @param tBasePath 基础路径 
     * @param pFilePart 文件对象
     * @return
     */
    public static String createNewFileName(String tBasePath,Part pFilePart)
    {
        String tType=pFilePart.getContentType();
        String tFileName=pFilePart.getSubmittedFileName();
        int    iLastPosition=tFileName.lastIndexOf(".");
        return joinFileName(tBasePath,UUID.randomUUID().toString()+tFileName.substring(iLastPosition));
    }
    
    /**
     * 合并目录名与子（文件）目录名为一个完整的全名称
     * @param tBaseDir       基础的目录名
     * @param tSubFileName   子（目录）文件名称
     * @return 串结后的完整路径名称
     */
    public static String joinFileName(String tBaseDir,String tSubFileName)
    {
        if(tSubFileName==null||tSubFileName.trim().equals(""))
        {
            return tBaseDir;
        }
        if(File.separator.equals("/"))
        {
            tBaseDir=tBaseDir.trim().replaceAll("\\\\", "/");
            tSubFileName=tSubFileName.trim().replaceAll("\\\\", "/");
        }
        else
        {
            tBaseDir=tBaseDir.trim().replaceAll("/", "\\\\");
            tSubFileName=tSubFileName.trim().replaceAll("/", "\\\\");
        }
        if(tBaseDir.charAt(tBaseDir.length()-1)==File.separatorChar)
        {
            tBaseDir=tBaseDir.substring(0,tBaseDir.length()-1);
        }
        if(tSubFileName.charAt(0)==File.separatorChar)
        {
            tSubFileName.substring(1);
        }
        return tBaseDir+File.separator+tSubFileName;
    }
}
