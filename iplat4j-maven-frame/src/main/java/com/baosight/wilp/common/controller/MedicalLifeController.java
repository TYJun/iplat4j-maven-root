package com.baosight.wilp.common.controller;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.service.IMedicalLifeService;
import com.baosight.wilp.common.util.MTUtils;
import com.baosight.wilp.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author pan
 * @version 1.0
 * @description: 医生活移动端接口请求处理层
 * @date 2022/3/24 10:47
 */
@RestController
public class MedicalLifeController extends AppBaseController {

    @Autowired
    private IMedicalLifeService iMedicalLifeService;

    /**
     * @description: 公告接口
     * @param: null
     * @return:
     * @author pan
     * @date: 2022/3/24 10:57
     */
    @GetMapping("notice")
    @CrossOrigin
    public Object notice() {
        return iMedicalLifeService.findNotice();
    }

    /**
     * @description: 修改密码
     * @param: workNo 工号
     * @param: newNo 新的手机号
     * @return:
     * @author pan
     * @date: 2022/4/8 11:44
     */
    @PutMapping("tel-modify")
    @CrossOrigin
    public void telModify(String workNo, String newNo) {
        iMedicalLifeService.telModify(workNo, newNo);
    }

    /**
     * @description: 查询科室
     * @param: dataGroupCode 院区
     * @return: EiInfo 包含所有科室的返回对象
     * @author pan
     * @date: 2022/4/11 11:27
     */
    @GetMapping("find-dept")
    @CrossOrigin
    public EiInfo findDept(String dataGroupCode) {
        EiInfo info = new EiInfo();
        info.set(EiConstant.serviceId, "S_AC_FW_05");
        info.set("offset", "0");
        info.set("limit", "1000");
        info = XServiceManager.call(info);
        if ("0".equals(info.get("isSuccess"))) {
            throw new PlatException(info.getMsg());
        }
        return info;
    }

    /**
     * @description: 修改员工科室
     * @param: workNo 工号
     * @param: newDept 新的科室
     * @return:
     * @author pan
     * @date: 2022/4/11 13:50
     */
    @PutMapping("dept-modify")
    @CrossOrigin
    public void deptModify(String workNo, String deptNum) {
        iMedicalLifeService.deptModify(workNo, deptNum);
    }


    /**
     * MultipartFile 自动封装上传过来的文件
     *
     * @param file
     * @return
     * @throws
     */
    @Value("${mlUploadLocation}")
    String path;

    @RequestMapping("/cfupload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        EiInfo info = new EiInfo();
        info.set("workNo", "RO00001");
        EiInfo resultInfo = MTUtils.invoke(info, "ServiceAUFW01", "getUserGroupsAndDepts");
//        EiInfo resultInfo=new ServiceAUFW01().getUserGroupsAndDepts(info);
        System.out.println(resultInfo.get("result"));

//        String path = ResourceUtils.getURL("classpath:").getPath() + suffixPath.substring(10);
//        System.out.println(Path);
        if (file.isEmpty()) {
            throw new RuntimeException("请选择一个图片");
        }

        String rootDir = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(rootDir);
//        System.out.println(System.getProperty("user.dir"));
//        String uploadPath=PlatApplicationContext.getProperty("docRootDir");
//        System.out.println(uploadPath);
//        File parent=new File(ResourceUtils.getURL("classpath:").getPath()+path);
        String prefix = rootDir.substring(0, rootDir.indexOf("WEB-INF"));
        System.out.println(prefix);
        File parent = new File(prefix + path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        File dest = new File(parent, newFileName);
        file.transferTo(dest);

        String finalPath = path + "/" + newFileName;
        System.out.println(finalPath);
        return finalPath;
    }


    /**
     * MultipartFile 自动封装上传过来的文件
     *
     * @param file
     * @return
     * @throws
     */

    @Value("${mlResourceLocation}")
    String resourceLocation;

    @RequestMapping("/resourceUpload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {

//        String path = ResourceUtils.getURL("classpath:").getPath() + suffixPath.substring(10);
//        System.out.println(Path);
        if (file.isEmpty()) {
            throw new RuntimeException("请选择一个图片");
        }

        String rootDir = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(rootDir);
//        System.out.println(System.getProperty("user.dir"));
//        String uploadPath=PlatApplicationContext.getProperty("docRootDir");
//        System.out.println(uploadPath);
//        File parent=new File(ResourceUtils.getURL("classpath:").getPath()+path);
        String prefix = rootDir.substring(0, rootDir.indexOf("WEB-INF"));
        System.out.println(prefix);
        File parent = new File(prefix + resourceLocation);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        File dest = new File(parent, newFileName);
        file.transferTo(dest);

        String finalPath = resourceLocation + "/" + newFileName;
        System.out.println(finalPath);
        return finalPath;
    }


    /**
     * MultipartFile 自动封装上传过来的文件
     *
     * @param file
     * @return
     * @throws
     */

    @Value("${matPicLocation}")
    String matPicLocation;

    @RequestMapping("/materialsPicUpload")
    public EiInfo uploadMatPic(@RequestParam("file") MultipartFile file, @RequestParam("matCode") String matCode, @RequestParam("type") String type) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("请选择一个图片");
        }

        String rootDir = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(rootDir);
        String prefix = rootDir.substring(0, rootDir.indexOf("WEB-INF"));
        System.out.println(prefix);
        File parent = new File(prefix + matPicLocation);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        EiInfo info1 = new EiInfo();
        info1.set("fkey", "MatImageSizeAndFormat");
        info1.set(EiConstant.serviceId, "S_AC_FW_21");
        EiInfo outInfo1 = XServiceManager.call(info1);
        ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) outInfo1.get("result");
        String config = list.get(0).get("FVALUE").replace(" ", "");
        String maxSize = config.split(";")[0];
        int maxSizeValue=Integer.valueOf(maxSize);
        String format = config.split(";")[1];

        boolean flag=true;
        String[] formats=format.split(",");
        for(String fm:formats){
            if(("."+fm).equals(suffix)){
                flag=false;
                break;
            }
        }
        if(flag){
            outInfo1.setMsg("图片格式不正确,只支持"+format+"格式");
            outInfo1.setStatus(-1);
            return  outInfo1;
        }

        if(file.getSize()>1024*maxSizeValue){
            outInfo1.setMsg("图片大小超过最大值"+maxSize+"kb");
            outInfo1.setStatus(-1);
            return outInfo1;
        }

        String thisMatCode = matCode;
        if ("".equals(thisMatCode)) {
            EiInfo info = new EiInfo();
            info.set(EiConstant.serviceName, "ACGM0103");
            info.set(EiConstant.methodName, "getLastMatCode");
            info = XLocalManager.call(info);
            thisMatCode = (String) info.get("lastMatCode");
        }

        if("0".equals(type)){
            EiInfo info2 = new EiInfo();
            info2.set("matCode", thisMatCode);
            info2.set(EiConstant.serviceId, "S_AC_FW_17");
            EiInfo outInfo2 = XServiceManager.call(info2);
            ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) outInfo2.get("result");
            if(!list2.isEmpty()){
                outInfo2.setMsg("物资编码不能重复");
                outInfo2.setStatus(-2);
                return outInfo2;
            }
        }


        String newFileName = thisMatCode + suffix;
        File dest = new File(parent, newFileName);
        file.transferTo(dest);

        String finalPath = matPicLocation + "/" + newFileName;
        System.out.println(finalPath);
//        outInfo1.set("msg",finalPath);
        outInfo1.setMsg(finalPath);
        return outInfo1;
    }
}
