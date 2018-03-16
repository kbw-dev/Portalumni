package data;
// Generated 02.03.2018 08:21:53 by Hibernate Tools 4.3.1



/**
 * Firma generated by hbm2java
 */
public class Firma  implements java.io.Serializable {

     private int idFirma;
     private Firmenverwaltung firmenverwaltung;
     private String firmenname;
     private String firmenadresse;
     private String ort;

    public Firma() {
    }

	
    public Firma(int idFirma, Firmenverwaltung firmenverwaltung, String firmenname, String firmenadresse) {
        this.idFirma = idFirma;
        this.firmenverwaltung = firmenverwaltung;
        this.firmenname = firmenname;
        this.firmenadresse = firmenadresse;
    }
    public Firma(int idFirma, Firmenverwaltung firmenverwaltung, String firmenname, String firmenadresse, String ort) {
       this.idFirma = idFirma;
       this.firmenverwaltung = firmenverwaltung;
       this.firmenname = firmenname;
       this.firmenadresse = firmenadresse;
       this.ort = ort;
    }
   
    public int getIdFirma() {
        return this.idFirma;
    }
    
    public void setIdFirma(int idFirma) {
        this.idFirma = idFirma;
    }
    public Firmenverwaltung getFirmenverwaltung() {
        return this.firmenverwaltung;
    }
    
    public void setFirmenverwaltung(Firmenverwaltung firmenverwaltung) {
        this.firmenverwaltung = firmenverwaltung;
    }
    public String getFirmenname() {
        return this.firmenname;
    }
    
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }
    public String getFirmenadresse() {
        return this.firmenadresse;
    }
    
    public void setFirmenadresse(String firmenadresse) {
        this.firmenadresse = firmenadresse;
    }
    public String getOrt() {
        return this.ort;
    }
    
    public void setOrt(String ort) {
        this.ort = ort;
    }
}

