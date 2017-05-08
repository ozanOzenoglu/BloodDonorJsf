package org.kanbankasi.veritabani;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kanduyurusu database table.
 * 
 */
@Entity
public class Kanduyurusu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"KanDuyurusuId\"")
	private Integer kanDuyurusuId;

	@Column(name="\"Adi\"")
	private String adi;

	@Column(name="\"DuyuruMetni\"")
	private String duyuruMetni;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"HastaneId\"")
	private Integer hastaneId;

	@Column(name="\"KanGrubu\"")
	private String kanGrubu;

	@Column(name="\"SehirNo\"")
	private Integer sehirNo;

	@Column(name="\"Soyadi\"")
	private String soyadi;

	@Column(name="\"Trombosit\"")
	private Boolean trombosit;

    public Kanduyurusu() {
    }

	public Integer getKanDuyurusuId() {
		return this.kanDuyurusuId;
	}

	public void setKanDuyurusuId(Integer kanDuyurusuId) {
		this.kanDuyurusuId = kanDuyurusuId;
	}

	public String getAdi() {
		return this.adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getDuyuruMetni() {
		return this.duyuruMetni;
	}

	public void setDuyuruMetni(String duyuruMetni) {
		this.duyuruMetni = duyuruMetni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getHastaneId() {
		return this.hastaneId;
	}

	public void setHastaneId(Integer hastaneId) {
		this.hastaneId = hastaneId;
	}

	public String getKanGrubu() {
		return this.kanGrubu;
	}

	public void setKanGrubu(String kanGrubu) {
		this.kanGrubu = kanGrubu;
	}

	public Integer getSehirNo() {
		return this.sehirNo;
	}

	public void setSehirNo(Integer sehirNo) {
		this.sehirNo = sehirNo;
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