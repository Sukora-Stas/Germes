package org.germes.presentation.admin.security;

/**
 * Created by Sukora Stas.
 */

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.itsimulator.germes.app.model.entity.person.User;
import org.itsimulator.germes.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Authorization component that integrates with {@link UserService} to fetch user by login
 */
public class CDIRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(CDIRealm.class);

    private final UserService userService;

    public CDIRealm(UserService userService) {
        this.userService = userService;

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("SHA-256");
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        try {
            String password = Optional.ofNullable(username).flatMap(name -> userService.findByUserName(name))
                    .map(User::getPassword)
                    .orElseThrow(() -> new UnknownAccountException("No account found for user " + username));

            return new SimpleAuthenticationInfo(username, password, getName());

        } catch (Exception e) {
            String message = "There was a error while authenticating user " + username;
            LOGGER.error(message, e);

            throw new AuthenticationException(message, e);
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAccount();
    }
}