package com.baosight.wilp.common.hc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;

public class MatCode {

    
    /**
     * 注入dao
     */
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    
    /**
     * 
     * 生成问题编号
     *
     * @Title: dangerCode 
     * @return 
     * @return: String
     */
    public static String matCode() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(new Date());
        String replace = format.replace("-", "");
        String substring = replace.substring(2, replace.length());
        //查询当天是否生成过编号
        List query = dao.query("HCLY0101.matCodeCount", substring);
        String count = query.get(0).toString();
        Integer valueOf = Integer.valueOf(count);
        Integer par = valueOf + 1;
        String string = par.toString();
        String code = substring + "00" + string;
        return code;
    }
}
