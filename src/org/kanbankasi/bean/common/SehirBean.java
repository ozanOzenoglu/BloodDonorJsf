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
			add(	new Sehir("AFYONKARAH�SAR",3));
			add(	new Sehir("A�RI",4));
			add(	new Sehir("AKSARAY",5));
			add(	new Sehir("AMASYA",6));
			add(	new Sehir("ANKARA",7));
			add(	new Sehir("ANTALYA",8));
			add(	new Sehir("ARDAHAN",9));
			add(	new Sehir("ARTV�N",10));
			add(	new Sehir("AYDIN",11));
			add(	new Sehir("BALIKES�R",12));
			add(	new Sehir("BARTIN",13));
			add(	new Sehir("BATMAN",14));
			add(	new Sehir("BAYBURT",15));
			add(	new Sehir("B�LEC�K",16));
			add(	new Sehir("B�NG�L",17));
			add(	new Sehir("B�TL�S",18));
			add(	new Sehir("BOLU",19));
			add(	new Sehir("BURDUR",20));
			add(	new Sehir("BURSA",21));
			add(	new Sehir("�ANAKKALE",22));
			add(	new Sehir("�ANKIRI",23));
			add(	new Sehir("�ORUM",24));
			add(	new Sehir("DEN�ZL�",25));
			add(	new Sehir("D�YARBAKIR",26));
			add(	new Sehir("D�ZCE",27));
			add(	new Sehir("ED�RNE",28));
			add(	new Sehir("ELAZI�",29));
			add(	new Sehir("ERZ�NCAN",30));
			add(	new Sehir("ERZURUM",31));
			add(	new Sehir("ESK��EH�R",32));
			add(	new Sehir("GAZ�ANTEP",33));
			add(	new Sehir("G�RESUN",34));
			add(	new Sehir("G�M��HANE",35));
			add(	new Sehir("HAKKAR�",36));
			add(	new Sehir("HATAY",37));
			add(	new Sehir("I�DIR",38));
			add(	new Sehir("ISPARTA",39));
			add(	new Sehir("�STANBUL",40));
			add(	new Sehir("�ZM�R",41));
			add(	new Sehir("KAHRAMANMARA�",42));
			add(	new Sehir("KARAB�K",43));
			add(	new Sehir("KARAMAN",44));
			add(	new Sehir("KARS",45));
			add(	new Sehir("KASTAMONU",46));
			add(	new Sehir("KAYSER�",47));
			add(	new Sehir("K�L�S",48));
			add(	new Sehir("KIRIKKALE",49));
			add(	new Sehir("KIRKLAREL�",50));
			add(	new Sehir("KIR�EH�R",51));
			add(	new Sehir("KOCAEL�",52));
			add(	new Sehir("KONYA",53));
			add(	new Sehir("K�TAHYA",54));
			add(	new Sehir("MALATYA",55));
			add(	new Sehir("MAN�SA",56));
			add(	new Sehir("MARD�N",57));
			add(	new Sehir("MERS�N",58));
			add(	new Sehir("MU�LA",59));
			add(	new Sehir("MU�",60));
			add(	new Sehir("NEV�EH�R",61));
			add(	new Sehir("N��DE",62));
			add(	new Sehir("ORDU",63));
			add(	new Sehir("OSMAN�YE",64));
			add(	new Sehir("R�ZE",65));
			add(	new Sehir("SAKARYA",66));
			add(	new Sehir("SAMSUN",67));
			add(	new Sehir("S��RT",68));
			add(	new Sehir("S�NOP",69));
			add(	new Sehir("S�VAS",70));
			add(	new Sehir("�ANLIURFA",71));
			add(	new Sehir("�IRNAK",72));
			add(	new Sehir("TEK�RDA�",73));
			add(	new Sehir("TOKAT",74));
			add(	new Sehir("TRABZON",75));
			add(	new Sehir("TUNCEL�",76));
			add(	new Sehir("U�AK",77));
			add(	new Sehir("VAN",78));
			add(	new Sehir("YALOVA",79));
			add(	new Sehir("YOZGAT",80));
			add(	new Sehir("ZONGULDAK",81)); 		
			}};
			
			return sehirList;		
	}

}
