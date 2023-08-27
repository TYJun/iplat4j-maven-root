<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="{pageContext.request.contextPath}" />

<title>宿舍类型与人员类型对照</title>
<EF:EFPage title="宿舍类型与人员类型对照">
 <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="no" rowNo="true">
        	<EF:EFColumn ename="id" cname="主键" width="200" hidden="true" align="center"/>
            <EF:EFComboColumn ename="dormProperties" cname="宿舍属性" width="120" readonly="true" align="center" >
                <EF:EFOption label="学生宿舍" value="学生宿舍"/>
                <EF:EFOption label="职工宿舍" value="职工宿舍"/>
            </EF:EFComboColumn>
            <EF:EFMultiSelectColumn ename="employmentNature" cname="员工类型" align="center"
                                    width="600">
                <EF:EFOption label="本院职工" value="本院职工"/>
                <EF:EFOption label="外协单位员工" value="外协单位员工"/>
                <EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
                <EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
                <EF:EFOption label="全日制学生" value="全日制学生"/>
                <EF:EFOption label="进修医生" value="进修医生"/>
                <EF:EFOption label="进修护士" value="进修护士"/>
                <EF:EFOption label="实习医生" value="实习医生"/>
                <EF:EFOption label="实习护士" value="实习护士"/>
                <EF:EFOption label="政策类研究生" value="政策类研究生"/>
                <EF:EFOption label="临时医技人员" value="临时医技人员"/>
                <EF:EFOption label="规范会培训生" value="规范会培训生"/>
                <EF:EFOption label="院聘临时工" value="院聘临时工"/>
                <EF:EFOption label="科聘临时工" value="科聘临时工"/>
            </EF:EFMultiSelectColumn>
        	<EF:EFColumn ename="recCreateName" cname="创建人" width="80"  align="center"/>
        	<EF:EFColumn ename="recCreateTime" cname="创建时间" width="120"  align="center"/>
            <EF:EFColumn ename="recReviseName" cname="修改人" width="80"  align="center"/>
            <EF:EFColumn ename="recReviseTime" cname="修改时间" width="120"  align="center"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
