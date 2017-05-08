package org.kanbankasi.veritabani;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SistemYoneticisi database table.
 * 
 */
@Entity
public class Sistemyoneticisi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SistemYoneticisiId\"")
	private Integer sistemYoneticisiId;

	@Column(name="\"Adi\"")
	private String adi;

	@Column(name="\"CepTel\"")
	private String cepTel;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"EvTel\"")
	private String evTel;

	@Column(name="\"IsTel\"")
	private String isTel;

	@Column(name="\"Sifre\"")
	private String sifre;

	@Column(name="\"Soyadi\"")
	private String soyadi;

    public Sistemyoneticisi() {
    }

	public Integer getSistemYoneticisiId() {
		return this.sistemYoneticisiId;
	}

	public void setSistemYoneticisiId(Integer sistemYoneticisiId) {
		this.sistemYoneticisiId = sistemYoneticisiId;
	}

	public String getAdi() {
		return this.adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getCepTel() {
		return this.cepTel;
	}

	public void setCepTel(String cepTel) {
		this.cepTel = cepTel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEvTel() {
		return this.evTel;
	}

	public void setEvTel(String evTel) {
		this.evTel = evTel;
	}

	public String getIsTel() {
		return this.isTel;
	}

	public void setIsTel(String isTel) {
		this.isTel = isTel;
	}

	public String getSifre() {
		return this.sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getSoyadi() {
		return this.soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

}