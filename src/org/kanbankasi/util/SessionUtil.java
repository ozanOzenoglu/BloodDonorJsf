package org.kanbankasi.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.kanbankasi.veritabani.Hastane;
import org.kanbankasi.veritabani.Kanbagiscisi;
import org.kanbankasi.veritabani.Sistemyoneticisi;
import org.kanbankasi.veritabani.Kanduyurusu;

public class SessionUtil {
	
	public static HttpSession getSessionFactory() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession httpsession = (HttpSession) fc.getExternalContext().getSession(false);
		
		if(httpsession == null)
			httpsession = (HttpSession) fc.getExternalContext().getSession(true);
		
		return httpsession;
	}
	
	public static void closeSession() {
		getSessionFactory().invalidate();
	}
	
	public static void setObject(String attribute, Object object) {
		getSessionFactory().setAttribute(attribute, object);
	}
	
	public static Kanbagiscisi getKBObject(String attribute) {
		return (Kanbagiscisi) getSessionFactory().getAttribute(attribute);
	}
	
	public static Hastane getHObject(String attribute) {
		return (Hastane) getSessionFactory().getAttribute(attribute);
	}
	
	public static Sistemyoneticisi getSYObject(String attribute) {
		return (Sistemyoneticisi) getSessionFactory().getAttribute(attribute);
	}
	
	public static Kanduyurusu getKDObject(String attribute) {
		return (Kanduyurusu) getSessionFactory().getAttribute(attribute);
	}

}
