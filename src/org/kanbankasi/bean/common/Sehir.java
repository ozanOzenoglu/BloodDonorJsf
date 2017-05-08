package org.kanbankasi.bean.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sehirClass")
@SessionScoped
public class Sehir extends Object{

	private String sehirAdi;
	private int no;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSehirAdi() {
		return sehirAdi;	
    }

	public void setSehirAdi(String sehirAdi ) {
		this.sehirAdi = sehirAdi;	
	}

	public Sehir(String sehirAdi,int no) {
		this.sehirAdi = sehirAdi;
		this.no = no;
    }
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Sehir)) return false;
		else return true;
	}

}
