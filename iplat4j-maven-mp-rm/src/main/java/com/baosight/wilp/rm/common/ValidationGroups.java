package com.baosight.wilp.rm.common;

import javax.validation.groups.Default;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 参数校验组
 * @ClassName: ValidationGroups
 * @Package com.baosight.wilp.rm.common
 * @date: 2022年09月02日 9:50
 */
public class ValidationGroups {
    public interface Add extends Default {
    }

    public interface Update extends Default {
    }

    public interface Customize<String> extends Default {
    }
}
