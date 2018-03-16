package data;
// Generated 02.03.2018 08:21:53 by Hibernate Tools 4.3.1



/**
 * BenutzerId generated by hbm2java
 */
public class BenutzerId  implements java.io.Serializable {

     private int idBenutzer;
     private int firmenVerwaltungIdFirmenVerwaltung;

    public BenutzerId() {
    }

    public BenutzerId(int idBenutzer, int firmenVerwaltungIdFirmenVerwaltung) {
       this.idBenutzer = idBenutzer;
       this.firmenVerwaltungIdFirmenVerwaltung = firmenVerwaltungIdFirmenVerwaltung;
    }
   
    public int getIdBenutzer() {
        return this.idBenutzer;
    }
    
    public void setIdBenutzer(int idBenutzer) {
        this.idBenutzer = idBenutzer;
    }
    public int getFirmenVerwaltungIdFirmenVerwaltung() {
        return this.firmenVerwaltungIdFirmenVerwaltung;
    }
    
    public void setFirmenVerwaltungIdFirmenVerwaltung(int firmenVerwaltungIdFirmenVerwaltung) {
        this.firmenVerwaltungIdFirmenVerwaltung = firmenVerwaltungIdFirmenVerwaltung;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BenutzerId) ) return false;
		 BenutzerId castOther = ( BenutzerId ) other; 
         
		 return (this.getIdBenutzer()==castOther.getIdBenutzer())
 && (this.getFirmenVerwaltungIdFirmenVerwaltung()==castOther.getFirmenVerwaltungIdFirmenVerwaltung());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdBenutzer();
         result = 37 * result + this.getFirmenVerwaltungIdFirmenVerwaltung();
         return result;
   }   
}

