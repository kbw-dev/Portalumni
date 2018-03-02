package ch.kbw.alumni;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;

/**
 *
 * @author John Yeary
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class ExceptionMessage {

    private final String linkName = "Union of Concerned Scientists";
    private HtmlCommandLink popupLink;
    private final String param1 = "Hello";
    private final String param2 = "World";

    public String getRedirectLink() {
        return "http://ucsusa.org";
    }

    public String JSPopup() {
        return "javascript:void window.open('" + getRedirectLink() + "','" + linkName + "','width=700,height=500,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=0,top=0');return false;";
    }

    public String getLinkName() {
        return linkName;
    }

    public HtmlCommandLink getPopupLink() {
        return popupLink;
    }

    public void setPopupLink(HtmlCommandLink popupLink) {
        this.popupLink = popupLink;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String redirect() {
        List<UIComponent> children = popupLink.getChildren();
        Map<String, List<String>> parameters = getParameters(children);
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            String url = fc.getExternalContext().encodeRedirectURL(getRedirectLink(), parameters);
            fc.getExternalContext().redirect(url);
        } catch (IOException e) {
            fc.addMessage(popupLink.getClientId(), new FacesMessage("The link could not be redirected.", e.getMessage()));
        }
        return null;
    }

    private Map<String, List<String>> getParameters(final List<UIComponent> components) {
        Map<String, List<String>> parameters = null;

        if (components != null) {
            parameters = new HashMap<>(components.size());

            for (UIComponent component : components) {
                if (component instanceof UIParameter) {
                    final UIParameter parameter = (UIParameter) component;
                    parameters.put(parameter.getName(), new ArrayList<String>() {
                        private static final long serialVersionUID = 3109256773218160485L;

                        {
                            add((String) parameter.getValue());
                        }
                    });
                }
            }
        }
        return parameters;
    }
}
