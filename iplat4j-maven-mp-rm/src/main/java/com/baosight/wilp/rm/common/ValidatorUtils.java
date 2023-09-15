package com.baosight.wilp.rm.common;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 参数校验工具类
 * @ClassName: ValidatorUtils
 * @Package com.baosight.wilp.rm.common
 * @date: 2022年09月01日 18:51
 * <p>
 * 1.校验参数Map
 * 2.校验实体参数对象
 * 3.构建错误EiInfo
 * 4.构建空EiInfo
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验参数Map
     *
     * @param map    map : 参数Map
     * @param clazz  clazz : 拥有校验规则的实体
     * @param groups groups : 待校验的组
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: validateEntity
     **/
    public static EiInfo validateEntity(Map<String, ?> map, Class<DaoEPBase> clazz, Class<?>... groups) {
        if (clazz == null) {
            return errorInfo("无法校验错误");
        }
        try {
            DaoEPBase instance = clazz.newInstance();
            instance.fromMap(map);
            return validateEntity(instance, groups);
        } catch (Exception e) {
            return errorInfo("无法校验错误");
        }
    }

    /**
     * 校验实体参数对象
     *
     * @param object object 待校验对象
     * @param groups groups 待校验的组
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: validateEntity
     **/
    public static EiInfo validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            return errorInfo(msg.toString());
        }
        return new EiInfo();
    }

    /**
     * 构建错误EiInfo
     *
     * @param inInfo inInfo : 参数EiInfo
     * @param msg    msg : 校验错误消息
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: errorInfo
     **/
    public static EiInfo errorInfo(EiInfo inInfo, String msg) {
        if (inInfo == null) {
            inInfo = new EiInfo();
        }
        inInfo.setStatus(-1);
        inInfo.setMsg(msg);
        return inInfo;
    }

    /**
     * 构建错误EiInfo
     *
     * @param msg msg : 校验错误消息
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: errorInfo
     **/
    public static EiInfo errorInfo(String msg) {
        return errorInfo(null, msg);
    }

    /**
     * 构建空EiInfo
     *
     * @param inInfo  inInfo : 参数EiInfo
     * @param blockId blockId : block块ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: blankInfo
     **/
    public static EiInfo blankInfo(EiInfo inInfo, String blockId) {
        if (inInfo == null) {
            inInfo = new EiInfo();
        }
        EiBlock block = new EiBlock(blockId);
        inInfo.addBlock(block);
        return inInfo;
    }

    /**
     * 构建空EiInfo
     *
     * @param blockId blockId : block块ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: blankInfo
     **/
    public static EiInfo blankInfo(String blockId) {
        return blankInfo(null, blockId);
    }

}
