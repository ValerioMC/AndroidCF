package it.androidcf.codicefiscale;

import android.util.Log;
import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.util.UtilsParole;

/**
 * La classe che rappresenta il codice fiscale.
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a>
 *
 */
public class CodiceFiscale {
	
	private String nome;
	private String cognome;
	private int giorno;
	private int mese;
	private int anno;
	private String sesso;
	private String comuneDiNascita;
	private String carattereDiControllo;
	
	/**
	 * Costruttore della classe
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param sesso
	 * @param comuneDiNascita
	 */
	public CodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comuneDiNascita){
		this.nome = nome;
		this.cognome = cognome;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

	/**
	 * @return the giorno
	 */
	public int getGiorno() {
		return giorno;
	}

	/**
	 * @param giorno the giorno to set
	 */
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	/**
	 * @return the mese
	 */
	public int getMese() {
		return mese;
	}

	/**
	 * @param mese the mese to set
	 */
	public void setMese(int mese) {
		this.mese = mese;
	}

	/**
	 * @return the anno
	 */
	public int getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(int anno) {
		this.anno = anno;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the comuneDiNascita
	 */
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	/**
	 * @param comuneDiNascita the comuneDiNascita to set
	 */
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	/**
	 * @return the carattereDiControllo
	 */
	public String getCarattereDiControllo() {
		return carattereDiControllo;
	}
	
	
	/**
	 * Calcola il codice fiscale.
	 * @return Restituisce il codice fiscale generato.
	 */
	public String calcola(){
		String codiceCognome = this.calcolaCodiceCognome(this.cognome);
		String codiceNome = this.calcolaCodiceNome(this.nome);
		String codiceDataNascitaESesso = this.calcolaCodiceDataNascitaESesso(this.anno, this.mese, this.giorno, this.sesso);
		String risultato = codiceCognome + codiceNome + codiceDataNascitaESesso;
		
		
		return risultato;
	}


	/**
	 * Calcola il codice del cognome del codice fiscale.
	 * @param cognome il cognome da cui calcolare il codice
	 * @return Il codice del cognome del codice fiscale.
	 */
	private String calcolaCodiceCognome(String cognome){
		String codiceCognome;
		int numeroConsonanti;
		cognome = UtilsParole.eliminaSpaziBianchi(cognome).toUpperCase();
		
		if(cognome.length() >= 3){
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome >= 3");
			}
			numeroConsonanti = UtilsParole.getNumeroConsonanti(cognome);
			
			if(numeroConsonanti >= 3){
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome >= 3");
				}
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, 3);
			}
			else{
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome < 3");
				}
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, numeroConsonanti);
				codiceCognome += UtilsParole.getPrimeVocali(cognome, 3 - numeroConsonanti);
			}
		}
		else{
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome < 3");
			}
			int numeroCaratteri = cognome.length();
			codiceCognome = cognome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		
		
		return codiceCognome;
	}
	
	
	/**
	 * Calcola il codice del nome del codice fiscale.
	 * @param nome il nome da cui calcolare il codice
	 * @return Il codice del nome del codice fiscale.
	 */
	private String calcolaCodiceNome(String nome){
		String codiceNome;
		int numeroConsonanti;
		nome = UtilsParole.eliminaSpaziBianchi(nome).toUpperCase();
		
		if(nome.length() >= 3){
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il nome >= 3");
			}
			numeroConsonanti = UtilsParole.getNumeroConsonanti(nome);
			
			if(numeroConsonanti >= 4){
				Log.d(Constants.LOG, "nc nome >= 4");
				codiceNome = UtilsParole.getConsonanteI(nome, 1) + UtilsParole.getConsonanteI(nome, 3) + UtilsParole.getConsonanteI(nome, 4);
			}
			else if(numeroConsonanti >= 3){
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc nome >= 3");
				}
				codiceNome = UtilsParole.getPrimeConsonanti(nome, 3);
			}
			else{
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc nome < 3");
				}
				codiceNome = UtilsParole.getPrimeConsonanti(nome, numeroConsonanti);
				codiceNome += UtilsParole.getPrimeVocali(nome, 3 - numeroConsonanti);
			}
		}
		else{
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il nome < 3");
			}
			int numeroCaratteri = nome.length();
			codiceNome = nome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		
		
		return codiceNome;
	}
	
	
	/**
	 * Calcola il codice della data di nascita e del sesso.
	 * @param anno l'anno da cui calcolare il codice.
	 * @param mese il mese da cui calcolare il codice.
	 * @param giorno il giorno da cui calcolare il codice.
	 * @param sesso il sesso da cui calcolare il codice.
	 * @return Il codice della data di nascita e del sesso del codice fiscale.
	 */
	private String calcolaCodiceDataNascitaESesso(int anno, int mese, int giorno, String sesso){
		String codiceDataNascitaESesso;
		String codiceAnno;
		
		codiceAnno = calcolaCodiceAnno(anno);
		
		
		codiceDataNascitaESesso = codiceAnno;
		
		return codiceDataNascitaESesso;
	}
	
	/**
	 * Calcola il codice dell'anno.
	 * @param anno l'anno da cui calcolare il codice.
	 * @return Il codice dell'anno del codice fiscale.
	 */
	private String calcolaCodiceAnno(int anno){
		return Integer.toString(anno).substring(2);
	}
	
	
	private String calcolaCodiceComune(String comune){
		//it.androidcf.database.AndroidCF database = new it.androidcf.database.AndroidCF(this);
		//return database.getCodiceCatastale(editTextComune.getText().toString().toUpperCase());
		return "";
	}
}
