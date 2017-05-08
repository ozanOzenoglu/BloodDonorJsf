package org.kanbankasi.bean.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sehir")
@SessionScoped
public class SehirBean {

	private String sehirAdi;
	private List<Sehir> sehirList;

    public String getSehirAdi() {
		return sehirAdi;
	}

	public void setSehirAdi(String sehirAdi) {
		this.sehirAdi = sehirAdi;
	}

	@SuppressWarnings("serial")
	public  List<Sehir> getSehirList() {
		sehirList = new ArrayList<Sehir>() {{
			add(	new Sehir("ADANA",1));
			add(	new Sehir("ADIYAMAN",2));
			add(	new Sehir("AFYONKARAHÝSAR",3));
			add(	new Sehir("AÐRI",4));
			add(	new Sehir("AKSARAY",5));
			add(	new Sehir("AMASYA",6));
			add(	new Sehir("ANKARA",7));
			add(	new Sehir("ANTALYA",8));
			add(	new Sehir("ARDAHAN",9));
			add(	new Sehir("ARTVÝN",10));
			add(	new Sehir("AYDIN",11));
			add(	new Sehir("BALIKESÝR",12));
			add(	new Sehir("BARTIN",13));
			add(	new Sehir("BATMAN",14));
			add(	new Sehir("BAYBURT",15));
			add(	new Sehir("BÝLECÝK",16));
			add(	new Sehir("BÝNGÖL",17));
			add(	new Sehir("BÝTLÝS",18));
			add(	new Sehir("BOLU",19));
			add(	new Sehir("BURDUR",20));
			add(	new Sehir("BURSA",21));
			add(	new Sehir("ÇANAKKALE",22));
			add(	new Sehir("ÇANKIRI",23));
			add(	new Sehir("ÇORUM",24));
			add(	new Sehir("DENÝZLÝ",25));
			add(	new Sehir("DÝYARBAKIR",26));
			add(	new Sehir("DÜZCE",27));
			add(	new Sehir("EDÝRNE",28));
			add(	new Sehir("ELAZIÐ",29));
			add(	new Sehir("ERZÝNCAN",30));
			add(	new Sehir("ERZURUM",31));
			add(	new Sehir("ESKÝÞEHÝR",32));
			add(	new Sehir("GAZÝANTEP",33));
			add(	new Sehir("GÝRESUN",34));
			add(	new Sehir("GÜMÜÞHANE",35));
			add(	new Sehir("HAKKARÝ",36));
			add(	new Sehir("HATAY",37));
			add(	new Sehir("IÐDIR",38));
			add(	new Sehir("ISPARTA",39));
			add(	new Sehir("ÝSTANBUL",40));
			add(	new Sehir("ÝZMÝR",41));
			add(	new Sehir("KAHRAMANMARAÞ",42));
			add(	new Sehir("KARABÜK",43));
			add(	new Sehir("KARAMAN",44));
			add(	new Sehir("KARS",45));
			add(	new Sehir("KASTAMONU",46));
			add(	new Sehir("KAYSERÝ",47));
			add(	new Sehir("KÝLÝS",48));
			add(	new Sehir("KIRIKKALE",49));
			add(	new Sehir("KIRKLARELÝ",50));
			add(	new Sehir("KIRÞEHÝR",51));
			add(	new Sehir("KOCAELÝ",52));
			add(	new Sehir("KONYA",53));
			add(	new Sehir("KÜTAHYA",54));
			add(	new Sehir("MALATYA",55));
			add(	new Sehir("MANÝSA",56));
			add(	new Sehir("MARDÝN",57));
			add(	new Sehir("MERSÝN",58));
			add(	new Sehir("MUÐLA",59));
			add(	new Sehir("MUÞ",60));
			add(	new Sehir("NEVÞEHÝR",61));
			add(	new Sehir("NÝÐDE",62));
			add(	new Sehir("ORDU",63));
			add(	new Sehir("OSMANÝYE",64));
			add(	new Sehir("RÝZE",65));
			add(	new Sehir("SAKARYA",66));
			add(	new Sehir("SAMSUN",67));
			add(	new Sehir("SÝÝRT",68));
			add(	new Sehir("SÝNOP",69));
			add(	new Sehir("SÝVAS",70));
			add(	new Sehir("ÞANLIURFA",71));
			add(	new Sehir("ÞIRNAK",72));
			add(	new Sehir("TEKÝRDAÐ",73));
			add(	new Sehir("TOKAT",74));
			add(	new Sehir("TRABZON",75));
			add(	new Sehir("TUNCELÝ",76));
			add(	new Sehir("UÞAK",77));
			add(	new Sehir("VAN",78));
			add(	new Sehir("YALOVA",79));
			add(	new Sehir("YOZGAT",80));
			add(	new Sehir("ZONGULDAK",81)); 		
			}};
			
			return sehirList;		
	}

}
