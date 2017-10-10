package org.germes.presentation.admin.security;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;
import org.itsimulator.germes.app.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

/**
 * Created by Sukora Stas.
 */


@WebListener
/**
 * Initializes authentication realm that delegates processing to CDIRealm
 */
public class CdiEnvironmentLoaderListener extends EnvironmentLoaderListener {
    @Inject
    private UserService userService;

    @Override
    protected WebEnvironment createEnvironment(ServletContext context) {
        WebEnvironment environment = super.createEnvironment(context);

        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();

        rsm.setRealm(new CDIRealm(userService));

        return environment;
    }
}
