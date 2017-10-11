package org.germes.presentation.admin.i18n;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Created by Sukora Stas.
 */

@ManagedBean(name = "language")
@SessionScoped
/**
 * Keeps selected langauge for the current user
 */
public class LanguageBean {
    /**
     * Locale for the current user
     */
    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLanguage(String lang) {
        locale = new Locale(lang);
    }

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }
}