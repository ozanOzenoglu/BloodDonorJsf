package org.kanbankasi.veritabani;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Kanbagiscisi database table.
 * 
 */
@Entity
public class Kanbagiscisi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"KanBagiscisiId\"")
	private Integer kanBagiscisiId;

	@Column(name="\"Adi\"")
	private String adi;

	@Column(name="\"Adresi\"")
	private String adresi;

	@Column(name="\"CepTel\"")
	private String cepTel;

	@Column(name="\"Cinsiyeti\"")
	private String cinsiyeti;

    @Temporal( TemporalType.DATE)
	@Column(name="\"DogumTarihi\"")
	private Date dogumTarihi;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"EvTel\"")
	private String evTel;

	@Column(name="\"IsTel\"")
	private String isTel;

	@Column(name="\"KanGrubu\"")
	private String kanGrubu;

	@Column(name="\"Puan\"")
	private Integer puan;

	@Column(name="\"SehirNo\"")
	private int sehirNo;

	@Column(name="\"Sifre\"")
	private String sifre;

    @Temporal( TemporalType.DATE)
	@Column(name="\"SonKanVermeTarihi\"")
	private Date sonKanVermeTarihi;

	@Column(name="\"Soyadi\"")
	private String soyadi;

	@Column(name="\"Trombosit\"")
	private Boolean trombosit;

    public Kanbagiscisi() {
    }

	public Integer getKanBagiscisiId() {
		return this.kanBagiscisiId;
	}

	public void setKanBagiscisiId(Integer kanBagiscisiId) {
		this.kanBagiscisiId = kanBagiscisiId;
	}

	public String getAdi() {
		return this.adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getAdresi() {
		return this.adresi;
	}

	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}

	public String getCepTel() {
		return this.cepTel;
	}

	public void setCepTel(String cepTel) {
		this.cepTel = cepTel;
	}

	public String getCinsiyeti() {
		return this.cinsiyeti;
	}

	public void setCinsiyeti(String cinsiyeti) {
		this.cinsiyeti = cinsiyeti;
	}

	public Date getDogumTarihi() {
		return this.dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
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

	public String getKanGrubu() {
		return this.kanGrubu;
	}

	public void setKanGrubu(String kanGrubu) {
		this.kanGrubu = kanGrubu;
	}

	public Integer getPuan() {
		return this.puan;
	}

	public void setPuan(Integer puan) {
		this.puan = puan;
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

	public Date getSonKanVermeTarihi() {
		return this.sonKanVermeTarihi;
	}

	public void setSonKanVermeTarihi(Date sonKanVermeTarihi) {
		this.sonKanVermeTarihi = sonKanVermeTarihi;
	}

	public String getSoyadi() {
		return this.soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	public Boolean getTrombosit() {
		return this.trombosit;
	}

	public void setTrombosit(Boolean trombosit) {
		this.trombosit = trombosit;
	}

}