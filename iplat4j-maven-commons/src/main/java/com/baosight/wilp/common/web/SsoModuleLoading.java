package com.baosight.wilp.common.web;

import org.springframework.stereotype.Component;

import com.baosight.wilp.basic.service .ModuleDynamicAssemblyI;



@Component
public class SsoModuleLoading implements ModuleDynamicAssemblyI
{

    @Override
    public String getModuleId()
    {
        return "COMMON";
    }

    @Override
    public String getModuleName()
    {
        return "基础支持";
    }

    @Override
    public String getModuleInitDefineFile()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getWilpDatabaseDefineFile()
    {
        return "com/baosight/wilp/common/web/data.common.xml";
    }

    @Override
    public String getPlatDatabaseDefineFile()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFrameDatabaseDefineFile()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUserInterfaceDefineFile()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
