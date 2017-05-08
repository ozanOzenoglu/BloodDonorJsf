package org.kanbankasi.bean.common;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
//import javax.mail.Message.RecipientType;
//import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
//import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.kanbankasi.veritabani.Kanbagiscisi;

@ManagedBean(name= "mail")
@SessionScoped
public class mailBean {

	private String ad;
	private String soyad;
	private String email;
	private String telNo;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String send(Kanbagiscisi bagisci){
		String redirect = "sy_kullanici_arama.xhtml?faces-redirect=true";
	    String host = "smtp.gmail.com";
	    String from = "kanbuluruz@gmail.com";
	    String pass = "kanbuluruz123456";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    InternetAddress  toAddress = new InternetAddress();


	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage message = new MimeMessage(session);
	   
	    
	    try {
	    	message.setFrom(new InternetAddress(from));

	    	// To get the array of addresses
	    	toAddress = new InternetAddress(bagisci.getEmail());
	    
	    	System.out.println(Message.RecipientType.TO);

	        message.addRecipient(Message.RecipientType.TO, toAddress);
	        message.setText(this.getAd() + " " + this.getSoyad() + " kan ihtiyacý için sizinle iletiþime geçmek istiyor. Geri dönüþ yapmak isterseniz\nKiþinin  telefon numarasý:"+this.getTelNo() +" email: "+this.getEmail() + "\nBu bir sistem mesajý olup bilgileriniz hiç bir þekilde 3. sahýþlara verilmemektedir.\nGönüllü kan baðýþcýsý olduðunuz için teþekkür ederiz.\nwww.kanbuluruz.biz" );
	    	message.setSubject(this.getAd() + " " + this.getSoyad()+ " adlý kiþinin kan ihtiyacý var!");
	    	Transport transport = session.getTransport("smtp");
	    	transport.connect(host, from, pass);
	    	transport.sendMessage(message, message.getAllRecipients());
	    	transport.close();
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    }

	    return redirect;
	}

	
}