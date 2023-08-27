package com.baosight.wilp.cm.common;

import com.baosight.iplat4j.ed.util.SequenceGenerator;

/**
 * 
 * 生成合同流水号
 * 
 * @Title: ContractNum.java
 * @ClassName: ContractNum
 * @Package：com.baosight.wilp.cm.common
 * @author: chenjie
 * @date: 2021年8月30日 下午5:55:54
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractNum {

    //序列号编码，该值需要在 EDMDM2 定义过
    String seqTypeId = "code1";
    //帐套编号
    long accsetNo= 1;
    //当前日期字符串，格式为"yyyy-MM-dd"
    String dateStr = "2017-11-30";
    //用于构建序列号前缀字符串（包含计数字符串，与非计数字符串） 参数录入顺序根据设定的序列号
    //分段顺序
    String[] args = {"s1","s2","s3"};
    //获取带账套、日期的序列号
    String sequence1 = SequenceGenerator.getNextSequence(seqTypeId, accsetNo,dateStr,args);
    //获取带日期的序列号
    String sequence2 = SequenceGenerator.getNextSequence(seqTypeId,dateStr,args);
    //获取序列号
    String sequence3 = SequenceGenerator.getNextSequence(seqTypeId,args);

}
