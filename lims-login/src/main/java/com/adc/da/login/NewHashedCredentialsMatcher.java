package com.adc.da.login;

import com.adc.da.login.security.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.StringUtils;

public class NewHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public NewHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        if(LoginType.NOPASSWD.equals(upt.getType())){
            return true;
        }
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        return equals(tokenHashedCredentials, accountCredentials);
    }
}
