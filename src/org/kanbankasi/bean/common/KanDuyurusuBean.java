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
import org.kanbankasi.veritabani.Hastane;
import org.kanbankasi.veritabani.Kanduyurusu;

@ManagedBean(name = "KanDuyurusu")
@SessionScoped
public class KanDuyurusuBean {
	
	private String adi;	
	private String duyuruMetni;	
	private int hastaneId;
	private String kanGrubu;	
	private int sehirNo;
	private String soyadi;
	private Boolean trombosit;
	private String email;
	private static Kanduyurusu selectedKanDuyurusu;
	private List<Kanduyurusu> kdlist;
  
	public void listToDb(List<Kanduyurusu> list) {
    
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    		tx = session.beginTransaction();
    		
    		for(Kanduyurusu kd : list) {
    			session.update(kd);
    		}
    		tx.commit(); // bu for'un icine yazýlmasý gerekebilir test et !
    		
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
	public List<Kanduyurusu> List() {
	
		kdlist = new ArrayList<Kanduyurusu>();      
		
		Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	
    	try {
    	tx = session.beginTransaction();
    	String selectQuery = "from Kanduyurusu as kd ";
    	
    	kdlist =  session.createQuery(selectQuery).list();
    	
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
    	
    	return kdlist;
	}

    public String doRegister() {
    	
    	String redirect = null;
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	int id  = 0;

    	try {
    		tx = session.beginTransaction();
    		String idQuery = "from Kanduyurusu  k order by k.kanDuyurusuId desc limit 1" ;
        	@SuppressWarnings("unchecked")
			List<Kanduyurusu> kd_list = session.createQuery(idQuery).list();
    		
    		if(kd_list.size() == 0 || kd_list == null){
    			id = 1;
    			redirect = "kanduyurusu_anasayfa.xhtml?faces-redirect=true";
    		}
    		else {
    			id = kd_list.get(0).getKanDuyurusuId() + 1 ;
    			redirect = "kanduyurusu_anasayfa.xhtml?faces-redirect=true";
    		}
    		
    		
    		tx.commit();
    	} catch (Exception e) {
    		if (tx != null) {
    			tx.rollback();
    			redirect = "kullanici_kayit.xhtml?faces-redirect=true";
    			e.printStackTrace();
    		}
    	}
    	
    	
    	Kanduyurusu kd = new Kanduyurusu();
    	kd.setAdi(this.getAdi());
    	kd.setSoyadi(this.getSoyadi());
    	kd.setEmail(this.getEmail());
    	kd.setDuyuruMetni(this.getDuyuruMetni());
    	kd.setSehirNo(this.sehirNo);
    	kd.setKanGrubu(this.getKanGrubu());
    	kd.setHastaneId(this.getHastaneId());
    	kd.setHastaneId(this.getHastaneId());
    	kd.setKanDuyurusuId(id);
    	kd.setTrombosit(false); // kanduyurusu icin false
    	
    	
    	try {
    		tx = session.beginTransaction();
    		session.save(kd);
    		tx.commit();
    		
    		SessionUtil.setObject("online", kd); // bu degistirilebilir ?
    		redirect = "kanduyurusu_anasayfa.xhtml?faces-redirect=true";
    	}
    	catch(Exception ex)
    	{
    		if(tx != null) {
    			tx.rollback();
    			redirect = "kanduyurusu_kayit.xhtml?faces-redirect=true";
    			ex.printStackTrace();
    		}
    	}	
    	finally	
    	{
    		session.close();	
    	}
    		
    	return redirect;
    }
    	
    public void doFillForm() {
    
    	Kanduyurusu kd = SessionUtil.getKDObject("online"); 
    	this.setAdi(kd.getAdi());
    	this.setSoyadi(kd.getSoyadi());
    	this.setDuyuruMetni(kd.getDuyuruMetni());				
    	this.setKanGrubu(kd.getKanGrubu());
    	this.setSehirNo(kd.getSehirNo());
    	this.setHastaneId(kd.getHastaneId());
    	this.setHastaneId(kd.getHastaneId());
    	this.setEmail(kd.getEmail());			
    
    }
    
    public String doEdit() {
    
    	String redirect = null;
    	Transaction tx = null;
    	Session session = HibernateUtil.openSession();
    	Kanduyurusu kd = SessionUtil.getKDObject("online");
    	 	
    	kd.setDuyuruMetni(this.getDuyuruMetni());
    	kd.setKanGrubu(this.getKanGrubu());
    	kd.setSehirNo(this.getSehirNo());
    	kd.setEmail(this.getEmail());
    	kd.setKanGrubu(this.getKanGrubu());
    	kd.setHastaneId(this.getHastaneId());
    	
    	try {
    		tx = session.beginTransaction();
    		session.update(kd);
    		tx.commit();
    		redirect = "kanduyurusu_anasayfa.xhtml?faces-redirect=true";
    	}
    	catch(Exception ex)
    	{
    		if(tx != null) {
    			tx.rollback();
    			redirect = "kanduyurusu_guncelle.xhtml?faces-redirect=true";
    			ex.printStackTrace();
    		}
    		
    	}
    	finally 
    	{
    		session.close();
    	}
    	
    	return redirect;  	
    }
    
    public String doDelete() {
    	
		int deleted = 0;
		String redirect = null;
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		Kanduyurusu kd = SessionUtil.getKDObject("online");
		
		try {
			tx = session.beginTransaction();
			String deleteQuery = "delete from Kanduyurusu as kd where kd.KanDuyurusuId='" 
					+ kd.getKanDuyurusuId() + "'";
			
			Query query = session.createQuery(deleteQuery);
			//get the number of deleted table elements
			deleted = query.executeUpdate();
			
			if (deleted > 0) {
				//the user is found and deleted
				SessionUtil.setObject("online", null);
				SessionUtil.closeSession();
				redirect = "kanduyurusu_anasayfa.xhtml?faces-redirect=true";
			}
			else {
				//the user cannot be found
				redirect = "kanduyurusu_sil.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				redirect = "kanduyurusu_guncelle.xhtml?faces-redirect=true";
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return redirect;
	}
    
    public String redirect() {	
    	return "anasayfa.xhtml";
    }

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getDuyuruMetni() {
		return duyuruMetni;
	}

	public void setDuyuruMetni(String duyuruMetni) {
		this.duyuruMetni = duyuruMetni;
	}

	public String getKanGrubu() {
		return kanGrubu;
	}

	public void setKanGrubu(String kanGrubu) {
		this.kanGrubu = kanGrubu;
	}

	public int getSehirNo() {
		return sehirNo;
	}

	public void setSehirNo(int sehirNo) {
		this.sehirNo = sehirNo;
	}

	public String getSoyadi() {
		return soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	public Boolean getTrombosit() {
		return trombosit;
	}

	public void setTrombosit(Boolean trombosit) {
		this.trombosit = trombosit;
	}

	public int getHastaneId() {
		return hastaneId;
	}

	public void setHastaneId(int hastaneId) {
		this.hastaneId = hastaneId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public  Kanduyurusu getSelectedKanDuyurusu() {
		return selectedKanDuyurusu;
	}

	public  void setSelectedKanDuyurusu(Kanduyurusu selectedKanDuyurusu) {
		KanDuyurusuBean.selectedKanDuyurusu = selectedKanDuyurusu;
	}

	@SuppressWarnings("unchecked")
	public String getHastaneAdi() {
		List<Hastane> hlist;
		hlist = new ArrayList<Hastane>();      
		String hastaneAdi = "asd";
		if(selectedKanDuyurusu != null ) {
	
			Transaction tx = null;
	    	Session session = HibernateUtil.openSession();	
	    	
	    	try {
	    	tx = session.beginTransaction();
	    	@SuppressWarnings("static-access")
			String hastaneSelectQuery = "from Hastane as h where h.hastaneId ='" +this.selectedKanDuyurusu.getHastaneId()+ "'";
	    	
	    	hlist =  session.createQuery(hastaneSelectQuery).list();
	    	if(hlist.size() != 0 || hlist != null ) {
	    		hastaneAdi = hlist.get(0).getHastaneAdi();
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
		
    	return hastaneAdi;
	}
	
	@SuppressWarnings("static-access")
	public String getSehirAdi() {
    	SehirBean sehirBean = new SehirBean();
    	List<Sehir> sList =  sehirBean.getSehirList();
    	Sehir sehir;
    	String sehirAd = "asd";
    	
    	if(selectedKanDuyurusu != null ) {
    		sehir = sList.get(this.selectedKanDuyurusu.getSehirNo()-1);
    		sehirAd = sehir.getSehirAdi();
    	}
    	
    	return sehirAd;
	}
 
}
