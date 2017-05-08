package org.kanbankasi.bean.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kanbankasi.util.HibernateUtil;
import org.kanbankasi.util.SessionUtil;
import org.kanbankasi.veritabani.Kanbagiscisi;

@ManagedBean(name = "kullanici")
@SessionScoped
public class KullaniciBean {

	private String adi;
	private String adresi;
	private String cepTel;
	private String cinsiyeti;
	private Date dogumTarihi;
	private String email;
	private String evTel;
	private boolean trombosit = false;
	private String isTel;
	private String kanGrubu;
	private Integer puan;
	private int sehirNo;
	private String sifre;
	private Date sonKanVermeTarihi;
	private String soyadi;
	private List<Kanbagiscisi> kblist;
	private List<Kanbagiscisi> ulist;
	private static Kanbagiscisi selectedKb;

    public String doRegister() {
    	String redirect = null;
    	Transaction tx = null;
    	//FacesMessage message = null;
    	Session session = HibernateUtil.openSession();
    	
    	int id  = 0;

    	try {
    		tx = session.beginTransaction();
    		String idQuery = "from Kanbagiscisi  k order by k.kanBagiscisiId desc limit 1" ;
        	@SuppressWarnings("unchecked")
			List<Kanbagiscisi> kb_list = session.createQuery(idQuery).list();
    		
    		if(kb_list.size() == 0 || kb_list == null){
    			id = 1;
    			redirect = "kb_anasayfa.xhtml?faces-redirect=true";
    		}
    		else {
    			id = kb_list.get(0).getKanBagiscisiId() + 1 ;
    			redirect = "kb_anasayfa.xhtml?faces-redirect=true";
    		}
    		
    		
    		tx.commit();
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "kb_kayit.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	} 
    	
	    	
	    	Kanbagiscisi kb = new Kanbagiscisi();
	    	kb.setAdi(this.getAdi());
	    	kb.setAdresi(this.getAdresi());
	    	kb.setCepTel(this.getCepTel());
	    	kb.setCinsiyeti(this.getCinsiyeti());
	    	kb.setDogumTarihi(this.getDogumTarihi());
	    	kb.setEmail(this.getEmail());
	    	kb.setEvTel(this.getEvTel());
	    	kb.setIsTel(this.getIsTel());
	    	kb.setKanGrubu(this.getKanGrubu());
	    	kb.setSehirNo(this.getSehirNo());
	    	kb.setPuan(0); 
	    	kb.setSehirNo(this.getSehirNo());
	    	kb.setSifre(this.getSifre());
	    	kb.setSonKanVermeTarihi(this.getSonKanVermeTarihi());
	    	kb.setSoyadi(this.getSoyadi());
	    	kb.setTrombosit(this.isTrombosit());
	    	kb.setKanBagiscisiId(id);
    	

    	try {
    		tx = session.beginTransaction();
    		session.save(kb);
    		tx.commit();
    		
    		SessionUtil.setObject("online", kb);
    		redirect = "kb_anasayfa.xhtml?faces-redirect=true";
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "kb_kayit.xhtml?faces-redirect=true";
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
    
    @SuppressWarnings("unchecked")
	public String doLogin() {
    	String redirect = null;
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    		tx = session.beginTransaction();
    		String loginQuery = "from Kanbagiscisi as kb where kb.email='" + this.getEmail() + "' and kb.sifre = '" + this.getSifre() + "'";
        	List<Kanbagiscisi> kblist = session.createQuery(loginQuery).list();
    		
    		if(kblist.size() == 0 || kblist == null){
    			SessionUtil.setObject("online", null);
    			redirect = "kb_giris.xhtml?faces-redirect=true";
    		}
    		else {
    			SessionUtil.setObject("online", kblist.get(0));
    			
    			redirect = "kb_anasayfa.xhtml?faces-redirect=true";
    		}
    		
    		tx.commit();
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "kb_giris.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	} 
    	finally {
    		session.close();
    	}

		return redirect;
	}
    
    //kullanýcý güncelle
    public void doFillForm() {
		//we need to get the data from session to fill the form
		Kanbagiscisi kb = SessionUtil.getKBObject("online");
		
		//set this beans attributes to session attributes (user)
		this.setAdi(kb.getAdi());
		this.setAdresi(kb.getAdresi());
		this.setCepTel(kb.getCepTel());
		this.setCinsiyeti(kb.getCinsiyeti());
		this.setDogumTarihi(kb.getDogumTarihi());
		this.setEmail(kb.getEmail());
		this.setEvTel(kb.getEvTel());
		this.setIsTel(kb.getIsTel());
		this.setKanGrubu(kb.getKanGrubu());
		this.setSehirNo(kb.getSehirNo());
		this.setSifre(kb.getSifre());
		this.setSonKanVermeTarihi(kb.getSonKanVermeTarihi());
		this.setSoyadi(kb.getSoyadi());
		this.setTrombasit(kb.getTrombosit());

	}
    
    public String doEdit() {
		String redirect = null;
		Kanbagiscisi kb = SessionUtil.getKBObject("online");
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
    	kb.setAdi(this.getAdi());
    	kb.setAdresi(this.getAdresi());
    	kb.setCepTel(this.getCepTel());
    	kb.setCinsiyeti(this.getCinsiyeti());
    	kb.setDogumTarihi(this.getDogumTarihi());
    	kb.setEmail(this.getEmail());
    	kb.setEvTel(this.getEvTel());
    	kb.setIsTel(this.getIsTel());
    	kb.setKanGrubu(this.getKanGrubu());
    	kb.setSehirNo(this.getSehirNo());
    	kb.setSehirNo(this.getSehirNo());
    	kb.setSifre(this.getSifre());
    	kb.setSonKanVermeTarihi(this.getSonKanVermeTarihi());
    	kb.setSoyadi(this.getSoyadi());
    	kb.setTrombosit(this.isTrombosit());
		
		try {
			tx = session.beginTransaction();
			session.update(kb);
			tx.commit();
			
			redirect = "kb_anasayfa.xhtml?faces-redirect=true";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "kb_guncelle.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}	

    public String doDelete() {
		int deleted = 0;
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		Kanbagiscisi kb = SessionUtil.getKBObject("online");
		
		try {
			tx = session.beginTransaction();
			String deleteQuery = "delete from Kanbagiscisi as kb where kb.email='" 
					+ kb.getEmail() + "'";
			
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
				redirect = "kb_sil.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "kb_sil.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}
    
    public String listToDelete() {
    	
    	String redirect = "sy_kullanici_arama.xhtml?faces-redirect=true"; 
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("static-access")
			String deleteQuery = "delete from Kanbagiscisi as kb where kb.email='" 
					+ this.selectedKb.getEmail()+ "'";
			
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
      
    public void listToDb() {
    	
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    		tx = session.beginTransaction();
    		
    		for(Kanbagiscisi kb : kblist) {
    			session.update(kb);
    		}
    		tx.commit(); // 
    		
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

    public String cinsiyetConverter(char cinsiyet){
    	String result = null;
    	if(cinsiyet == 'E') {
    		result = "Erkek";
    	}
    	else if(cinsiyet=='K') {
    		result = "Kadýn";
    	}
    	
    	return result;   	
    }
    
	@SuppressWarnings("unchecked")
	public List<Kanbagiscisi> List() {
	
		if(kblist == null) {
			kblist = new ArrayList<Kanbagiscisi>();     
			
			
			Transaction tx = null;
	    	Session session = HibernateUtil.openSession();
	    	
	    	try {
	    	tx = session.beginTransaction();
	    	String selectQuery = "from Kanbagiscisi as kb ";
	    	
	    	kblist =  session.createQuery(selectQuery).list();
	    	
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
		
    	return kblist;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Kanbagiscisi> uygunOlanlarList() {
		
		Boolean result  = ulist == null ? true:false;
		if ((result) ) {
			ulist = new ArrayList<Kanbagiscisi>();     
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-3);
			Date legalDonor = cal.getTime();
		
			cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-18); // donor's age must be over 18
			Date minumumLegalAge  = cal.getTime();
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-47); // donor's age must be lover 65
			Date maximumLegalAge = cal.getTime();
			
			Transaction tx = null;
	    	Session session = HibernateUtil.openSession();
	    	
	    	try {
	    	tx = session.beginTransaction();
	    	String selectQuery = "from Kanbagiscisi as kb   where kb.sonKanVermeTarihi < '" + legalDonor + "' and kb.dogumTarihi < '" + minumumLegalAge + "' and kb.dogumTarihi > '" +maximumLegalAge +"'" ;                      
	    	
	    	ulist =  session.createQuery(selectQuery).list();
	    	
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
		
    	return ulist;	
	}
	
    public String doLogout() {
		SessionUtil.closeSession();
		return "index.xhtml?faces-redirect=true";
    }

	public String getAdi() {
		return adi;
	}
	
	public void setAdi(String adi) {
		this.adi = adi;
	}
	
	public String getAdresi() {
		return adresi;
	}
	
	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}
	
	public String getCepTel() {
		return cepTel;
	}
	
	public void setCepTel(String cepTel) {
		this.cepTel = cepTel;
	}
	
	public String getCinsiyeti() {
		return cinsiyeti;
	}
	
	public void setCinsiyeti(String cinsiyeti) {
		this.cinsiyeti = cinsiyeti;
	}
	
	public Date getDogumTarihi() {
		return dogumTarihi;
	}
	
	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
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
	
	public String getKanGrubu() {
		return kanGrubu;
	}
	
	public void setKanGrubu(String kanGrubu) {
		this.kanGrubu = kanGrubu;
	}
	
	public Integer getPuan() {
		return puan;
	}
	
	public void setPuan(Integer puan) {
		this.puan = puan;
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
	
	public Date getSonKanVermeTarihi() {
		return sonKanVermeTarihi;
	}
	
	public void setSonKanVermeTarihi(Date sonKanVermeTarihi) {
		this.sonKanVermeTarihi = sonKanVermeTarihi;
	}
	
	public String getSoyadi() {
		return soyadi;
	}
	
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	
    public Kanbagiscisi getSelectedKb() {
		return selectedKb;
	}

	@SuppressWarnings("static-access")
	public void setSelectedKb(Kanbagiscisi selectedKb) {
		this.selectedKb = selectedKb;
	}

	public boolean isTrombosit() {
		return trombosit;
	}

	public void setTrombasit(boolean trombosit) {
		this.trombosit = trombosit;
	}

}
