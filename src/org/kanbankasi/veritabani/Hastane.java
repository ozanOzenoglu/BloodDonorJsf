package org.kanbankasi.veritabani;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the hastane database table.
 * 
 */
@Entity
@Table(name="hastane")
public class Hastane implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="\"HastaneId\"")
	private Integer hastaneId;

	@Column(name="\"Adresi\"")
	private String adresi;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"Fax\"")
	private String fax;

	@Column(name="\"HastaneAdi\"")
	private String hastaneAdi;

	@Column(name="\"SehirNo\"")
	private int sehirNo;

	@Column(name="\"Sifre\"")
	private String sifre;

	@Column(name="\"Tel\"")
	private String tel;

    public Hastane() {
    }

	public Integer getHastaneId() {
		return this.hastaneId;
	}

	public void setHastaneId(Integer hastaneId) {
		this.hastaneId = hastaneId;
	}

	public String getAdresi() {
		return this.adresi;
	}

	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHastaneAdi() {
		return this.hastaneAdi;
	}

	public void setHastaneAdi(String hastaneAdi) {
		this.hastaneAdi = hastaneAdi;
	}

	public int getSehirNo() {
		return this.sehirNo;
	}

	public void setSehirNo(int sehirNo) {
		this.sehirNo = sehirNo;
	}

	public String getSifre() {
		return this.sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
	public boolean equals(Object obj) {
		return true;
		
		
	}
}