package org.kanbankasi.bean.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kanbankasi.util.HibernateUtil;
import org.kanbankasi.util.SessionUtil;
import org.kanbankasi.veritabani.Hastane;

@SuppressWarnings("unused")
@ManagedBean(name = "hastane")
@SessionScoped
public class HastaneBean {

	private String hastaneAdi;
	private String adresi;
	private String email;
	private String tel;
	private String fax;
	private int sehirNo;
	private String sifre;
	private int selectedSehirNo = 1;
	private static Hastane selectedH;
    
	public void listToDb(List<Hastane> list) {
    	
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    		tx = session.beginTransaction();
    		
    		for(Hastane h : list) {
    			session.update(h);
    		}
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
    }
 
	public String listToDelete() {
 	
		String redirect = "sy_hastane_arama.xhtml?faces-redirect=true"; 
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("static-access")
			String deleteQuery = "delete from Hastane as h where h.email='" 
					+ this.selectedH.getEmail()+ "'";
			
			Query query = session.createQuery(deleteQuery);
			query.executeUpdate();
			

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}
    
	@SuppressWarnings("unchecked")
	public List<Hastane> List() {
	
		List<Hastane> hlist;
		hlist = new ArrayList<Hastane>();      
		
		Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    	tx = session.beginTransaction();
    	String selectQuery = "from Hastane as h ";
    	
    	hlist =  session.createQuery(selectQuery).list();
    	
		tx.commit();
		
		}catch(Exception ex) 
		{
			if(tx != null) {
				tx.rollback();			
				ex.printStackTrace();
			}
		}
		finally {
			session.close();
		}
    	
    	return hlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Hastane> ListBySehir() {
		List<Hastane> hlist = null;
		
		if(selectedSehirNo != 0) {
			hlist = new ArrayList<Hastane>();      
			
			Transaction tx = null;
	    	Session session = HibernateUtil.openSession();
	    	
	    	try {
	    	tx = session.beginTransaction();
	    	String selectQuery = "from Hastane as h where h.sehirNo ='"+selectedSehirNo+"'" ;
	    	
	    	hlist =  session.createQuery(selectQuery).list();
	    	
			tx.commit();
			
			}catch(Exception ex) 
			{
				if(tx != null) {
					tx.rollback();			
					ex.printStackTrace();
				}
			}
			finally {
				session.close();
			}
		}

		return hlist;
	}
    
    public String doRegister() {
    	
    	String redirect = null;
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	int id  = 0;

    	try {
    		tx = session.beginTransaction();
    		String idQuery = "from Hastane  h order by h.hastaneId desc limit 1" ;
        	@SuppressWarnings("unchecked")
			List<Hastane> h_list = session.createQuery(idQuery).list();
    		
    		if(h_list.size() == 0 || h_list == null){
    			id = 1;
    			redirect = "h_anasayfa.xhtml?faces-redirect=true";
    		}
    		else {
    			id = h_list.get(0).getHastaneId() + 1 ;
    			redirect = "h_anasayfa.xhtml?faces-redirect=true";
    		}
    		
    		
    		tx.commit();
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "h_giris.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	}    	
    	
    	Hastane h = new Hastane();
    	h.setHastaneAdi(this.hastaneAdi);
    	h.setAdresi(this.adresi);
    	h.setEmail(this.email);
    	h.setFax(this.fax);  	
    	h.setSehirNo(this.getSehirNo());
    	h.setSifre(this.sifre);
    	h.setTel(this.tel);
    	h.setHastaneId(id);
    	
    	try {
    		tx = session.beginTransaction();
    		session.save(h);
    		tx.commit();
    		
    		SessionUtil.setObject("online", h);
    		redirect = "h_anasayfa.xhtml?faces-redirect=true";
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "sy_hastane_kayit.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	} finally {
    		session.close();
    	}

    	return redirect;
    }
    
    public String redirect() {
    	return "index.xhtml";
    }
    
    //hastane giriþ
    @SuppressWarnings("unchecked")
	public String doLogin() {
    	String redirect = null;
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    		tx = session.beginTransaction();
    		String loginQuery = "from Hastane as h where h.email ='" + this.getEmail() + "' and h.sifre = '" + this.getSifre() + "'";
        	List<Hastane> h_list = session.createQuery(loginQuery).list();
    		
    		if(h_list.size() == 0 || h_list == null){
    			SessionUtil.setObject("online", null);
    			redirect = "h_giris.xhtml?faces-redirect=true";
    		}
    		else {
    			SessionUtil.setObject("online", h_list.get(0));
    			redirect = "h_anasayfa.xhtml?faces-redirect=true";
    		}
    		
    		tx.commit();
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "h_giris.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	} finally {
    		session.close();
    	}
    
		return redirect;
	}
    
    //hastane güncelle
    public void doFillForm() {
		//we need to get the data from session to fill the form
		Hastane h = SessionUtil.getHObject("online");
		
		//set this beans attributes to session attributes (hastane)
		
		this.setAdresi(h.getAdresi());
		this.setTel(h.getTel());
		this.setEmail(h.getEmail());
		this.setFax(h.getFax());
		
		this.setSehirNo(h.getSehirNo());
		this.setSifre(h.getSifre());	
		this.setHastaneAdi(h.getHastaneAdi());
		this.setFax(h.getFax());
	}
	
    public String doEdit() {
		String redirect = null;
		Hastane h = SessionUtil.getHObject("online");
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
    	h.setHastaneAdi(this.getHastaneAdi());
		h.setAdresi(this.getAdresi());
    	h.setEmail(this.getEmail());
    	h.setSehirNo(this.getSehirNo());
    	h.setSifre(this.getSifre());
    	h.setTel(this.getTel());
    	h.setFax(this.getFax());
		
		try {
			tx = session.beginTransaction();
			session.update(h);
			tx.commit();			
			redirect = "h_anasayfa.xhtml?faces-redirect=true";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "h_guncelle.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}		
		return redirect;
	}
    
    //hastane sil
    public String doDelete() {
		
		int deleted = 0;
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		Hastane h = SessionUtil.getHObject("online");
		
		try {
			tx = session.beginTransaction();
			String deleteQuery = "delete from Hastane as h where h.email ='" + h.getEmail()+ "'";
			
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
				redirect = "h_sil.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "h_sil.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}		
		return redirect;
	}
    
   ///hastane çýkýþ
    public String doLogout() {
		SessionUtil.closeSession();
		return "index.xhtml?faces-redirect=true";
    }  

	public String getAdresi() {
		return adresi;
	}
	
	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getSehirNo() {
		return sehirNo;
	}

	public void setSehirNo(int sehirNo) {
		this.sehirNo = sehirNo;
	}

	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getHastaneAdi() {
		return hastaneAdi;
	}

	public void setHastaneAdi(String hastaneAdi) {
		this.hastaneAdi = hastaneAdi;
	}

	public int getSelectedSehirNo() {
		return selectedSehirNo;
	}

	public void setSelectedSehirNo(int selectedSehirNo) {
		this.selectedSehirNo = selectedSehirNo;
	}

	public void sSelectedSehir(int selectedSehir){
		this.selectedSehirNo = selectedSehir;
	}
	public Hastane getSelectedH() {
		return selectedH;
	}
	
	@SuppressWarnings("static-access")
	public void setSelectedH(Hastane selectedH) {
		this.selectedH = selectedH;
	}
	
}
