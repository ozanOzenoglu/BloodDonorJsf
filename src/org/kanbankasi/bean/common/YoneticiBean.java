package org.kanbankasi.bean.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kanbankasi.util.HibernateUtil;
import org.kanbankasi.util.SessionUtil;
import org.kanbankasi.veritabani.Sistemyoneticisi;

@ManagedBean(name ="yonetici")
@SessionScoped
public class YoneticiBean {
	
	private Integer SistemyoneticisiId;
	private String adi;
	private String cepTel;
	private String email;
	private String evTel;
	private String isTel;
	private String sifre;
	private String soyadi;
	
	public void listToDb(List<Sistemyoneticisi> list) {
	    	
	   	Transaction tx = null;
	   	Session session = HibernateUtil.openSession();
	    	
	   	try {
	   		tx = session.beginTransaction();
	    		
	   		for(Sistemyoneticisi sy : list) {
	   			session.update(sy);
	   		}
	   		tx.commit(); // bu for'un icine yaz�lmas� gerekebilir test et !
	    		
	   	}catch(Exception ex) {
	   		if(tx != null) {
	   			tx.rollback();
	   			ex.printStackTrace();
	   		}
	   	}
	   	finally {
	   		session.close();
	   	}
	}
	    
	@SuppressWarnings("unchecked")
	public List<Sistemyoneticisi> List() {
		
		List<Sistemyoneticisi> sylist;
		sylist = new ArrayList<Sistemyoneticisi>();      
			
		Transaction tx = null;
	   	Session session = HibernateUtil.openSession();
	    	
	   	try {
	   		tx = session.beginTransaction();
	    	String selectQuery = "from KanBagiscisi as kb ";
	    	
	    	sylist =  session.createQuery(selectQuery).list();
	    	
			tx.commit();
			
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();			
				ex.printStackTrace();
			}
		}
		finally {
			session.close();
		}
	    	
	    return sylist;
	}
	
	public String doRegister() {
		
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
		Sistemyoneticisi sy = new Sistemyoneticisi();
		
		sy.setAdi(this.getAdi());
		sy.setSoyadi(this.getSoyadi());
		sy.setEmail(this.getEmail());
		sy.setIsTel(this.getIsTel());
		sy.setEvTel(this.getEvTel());
		sy.setCepTel(this.getCepTel());
		sy.setSifre(this.getSifre());
		sy.setSistemYoneticisiId(this.getSistemyoneticisiId());
		
		try {
			tx = session.beginTransaction();
			session.save(sy);
			tx.commit();
			
			SessionUtil.setObject("online", sy);
			redirect = "sy_anasayfa.xhtml?faces-redirect=true"; 
		} catch(Exception e) {
			
			if(tx != null) {
				tx.rollback();
				redirect = "sy_kayit.xhtml?faces-redirect=true" ; 
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
		
		return redirect;
	}
	
	
	public String redirect() {
		return "index.xhtml";
	}
	
	//y�netici giri�
	@SuppressWarnings("unchecked")
	public String doLogin() {
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
		try{
			tx = session.beginTransaction();
			String loginQuery = "from Sistemyoneticisi as S where S.email = '"+this.getEmail() +"' and S.sifre  = '" + this.getSifre() + "'";                 
			List<Sistemyoneticisi> sy_List = session.createQuery(loginQuery).list();
			
			if(sy_List.size() == 0 || sy_List == null) {
			
				SessionUtil.setObject("online", null);
				redirect  = "sy_giris.xhtml?faces-redirect=true";
				
			}else {
				SessionUtil.setObject("online", sy_List.get(0));
				redirect = "sy_anasayfa.xhtml?faces-redirect=true";
				
			}
			tx.commit();
			
		}
		catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
				redirect = "sy_giris.xhtml?faces-redirect=true";
				ex.printStackTrace();
			}
		}			
		finally {
			session.close();	
		}
		
		return redirect;
	}
	
	//yönetici güncelle
	public void doFillForm() { 
		// we need to get the data from session to fill the form
		
		Sistemyoneticisi sy = SessionUtil.getSYObject("online");
		
		this.setSistemyoneticisiId(sy.getSistemYoneticisiId());
		this.setAdi(sy.getAdi());
		this.setCepTel(sy.getCepTel());
		this.setEmail(sy.getEmail());
		this.setEvTel(sy.getEvTel());
		this.setIsTel(sy.getIsTel());
		this.setSifre(sy.getSifre());
		this.setSoyadi(sy.getSoyadi());
	}
	
	public String doEdit() {
		
		String redirect = null;
		Sistemyoneticisi sy = SessionUtil.getSYObject("online");
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
		sy.setAdi(this.getAdi());
    	sy.setSoyadi(this.getSoyadi());
    	sy.setCepTel(this.getCepTel());
    	sy.setEmail(this.getEmail());
    	sy.setEvTel(this.getEvTel());
    	sy.setIsTel(this.getIsTel());
    	sy.setSifre(this.getSifre());
    	sy.setSistemYoneticisiId(this.getSistemyoneticisiId());
		
		try {
			tx = session.beginTransaction();
			session.update(sy);
			tx.commit();
			
			redirect = "sy_anasayfa.xhtml?faces-redirect=true";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "sy_guncelle.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}	
	
	//y�netici sil
	public String doDelete() {
		
		int deleted = 0;
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		Sistemyoneticisi sy = SessionUtil.getSYObject("online");
		
		try {
			tx = session.beginTransaction();
			String deleteQuery = "delete from Sistemyoneticisi as sy where sy.sistemYoneticisiId='" 
					+ sy.getSistemYoneticisiId() + "'";
			
			Query query = session.createQuery(deleteQuery);
			//get the number of deleted table elements
			deleted = query.executeUpdate();
			
			if (deleted > 0) {
				//the user is found and deleted
				SessionUtil.setObject("online", null);
				SessionUtil.closeSession();
				redirect = "index.xhtml?faces-redirect=true";
			}
			else {
				//the user cannot be found
				redirect = "sy_sil.xhtml?faces-redirect=true";
			}
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "sy_guncelle.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}
	
	//y�netici ��k��
    public String doLogout() {
		SessionUtil.closeSession();
		return "index.xhtml?faces-redirect=true";
    }
    
	public Integer getSistemyoneticisiId() {
		return SistemyoneticisiId;
	}
	
	public void setSistemyoneticisiId(Integer sistemyoneticisiId) {
		SistemyoneticisiId = sistemyoneticisiId;
	}
	
	public String getAdi() {
		return adi;
	}
	
	public void setAdi(String adi) {
		this.adi = adi;
	}
	
	public String getCepTel() {
		return cepTel;
	}
	
	public void setCepTel(String cepTel) {
		this.cepTel = cepTel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEvTel() {
		return evTel;
	}
	
	public void setEvTel(String evTel) {
		this.evTel = evTel;
	}
	
	public String getIsTel() {
		return isTel;
	}
	
	public void setIsTel(String isTel) {
		this.isTel = isTel;
	}
	
	public String getSifre() {
		return sifre;
	}
	
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	public String getSoyadi() {
		return soyadi;
	}
	
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	
}	
    