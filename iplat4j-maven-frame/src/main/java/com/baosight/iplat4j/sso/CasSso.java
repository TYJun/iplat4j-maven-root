package com.baosight.iplat4j.sso;

import com.baosight.iplat4j.core.security.sso.SSOCredential;

public class CasSso implements SSOCredential {
    @Override
    //crep_passworduserp_username
    public boolean validateCredential(String cre, String user) {
        //
        System.out.println(cre);
        System.out.println(user);

        return true;
    }
    @Override
    public String composeCredential(String user, String target) {
        return null;
    }
}