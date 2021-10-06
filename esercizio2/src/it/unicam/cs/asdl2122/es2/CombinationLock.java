package it.unicam.cs.asdl2122.es2;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 *
 * @author Luca Tesei
 */
public class CombinationLock {

    // TODO inserire le variabili istanza che servono
    String combinazione;
    StringBuffer combinazioneInserita = new StringBuffer();

    private boolean locked = true;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     *
     * @param aCombination la combinazione che deve essere una stringa di 3
     *                     lettere maiuscole dell'alfabeto inglese
     * @throws IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throws NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
        char[] caratteriCombinazione = aCombination.toCharArray();
        if (aCombination.length() != 3 || !Character.isLetter(caratteriCombinazione[0]) ||
                Character.isLowerCase(caratteriCombinazione[0]) || !Character.isLetter(caratteriCombinazione[1]) ||
                Character.isLowerCase(caratteriCombinazione[1]) || !Character.isLetter(caratteriCombinazione[2]) ||
                Character.isLowerCase(caratteriCombinazione[2]))
            throw new IllegalArgumentException("La combinazione deve avere 3 lettere maiuscole");
        if (aCombination.equals(null)) throw new NullPointerException("La stringa inserita è nulla");

        combinazione = aCombination;
    }

    /**
     * Imposta la manopola su una certa posizione.
     *
     * @param aPosition un carattere lettera maiuscola su cui viene
     *                  impostata la manopola
     * @throws IllegalArgumentException se il carattere fornito non è una
     *                                  lettera maiuscola dell'alfabeto
     *                                  inglese
     */
    public void setPosition(char aPosition) {
        if (!Character.isLetter(aPosition) || Character.isLowerCase(aPosition)) throw new IllegalArgumentException(
                "Il carattere fornito non è una lettera maiuscola");
        if (combinazioneInserita.length() < 3) {
            combinazioneInserita.append(aPosition);
        }
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {
        if (combinazioneInserita.toString().equals(combinazione)) {
            locked = true;
        } else {
            combinazioneInserita.delete(0, 3);
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     *
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return locked;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        locked = false;//cassaforte chiusa
        combinazioneInserita.delete(0, 3);
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     *
     * @param aCombination la nuova combinazione che deve essere una stringa
     *                     di 3 lettere maiuscole dell'alfabeto inglese
     * @throws IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throws NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        if (locked) {
            //controllo aCombination
            if(aCombination == null) throw new NullPointerException("Nuova combinazione nulla");
            if (aCombination.length() != 3) throw new IllegalArgumentException("La nuova combinazione deve essere di " +
                    "tre caratteri");
            char[] caratteriCombinazione = aCombination.toCharArray();
            for (int i = 0; i < 3; i++) {
                if(!Character.isLetter(caratteriCombinazione[i]) || Character.isLowerCase(caratteriCombinazione[i]))
                    throw new IllegalArgumentException("La nuova combinazione devono essere tre lettere maiuscole");
            }
            combinazione = aCombination;
            lock();
        } else {
            combinazioneInserita.delete(0, 3);
        }
    }
}