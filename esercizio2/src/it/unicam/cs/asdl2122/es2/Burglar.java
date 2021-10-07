package it.unicam.cs.asdl2122.es2;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 *
 * @author Luca Tesei
 */
public class Burglar {

    private int tentativi = -1;
    private CombinationLock cassaforteDaForzare;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     *
     * @param aCombinationLock
     * @throws NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        if (aCombinationLock == null) throw new NullPointerException("La cassaforte da forzare non è valida");
        cassaforteDaForzare = aCombinationLock;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     *
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {
        StringBuffer combinazione = new StringBuffer("$$$");//creo una stringa di 3 caratteri qualsiasi
            /*
             Tramite il cast di un numero compreso tra 65 e 90, secondo la tabella ASCII,
             ottengo una lettera dell'alfabeto inglese maiuscola.
             */
            for (int k = 65; k <= 90; k++) {
                combinazione.setCharAt(0, (char) k);//prima lettera
                for (int j = 65; j <= 90; j++) {
                    combinazione.setCharAt(1, (char) j);//seconda lettera
                    for (int i = 65; i <= 90; i++) {
                        combinazione.setCharAt(2, (char) i);//terza lettera
                        //Imposto la combinazione
                        cassaforteDaForzare.setPosition(combinazione.charAt(0));
                        cassaforteDaForzare.setPosition(combinazione.charAt(1));
                        cassaforteDaForzare.setPosition(combinazione.charAt(2));
                        //Provo ad aprirla
                        cassaforteDaForzare.open();
                        tentativi++;
                        if (cassaforteDaForzare.isOpen()) break;
                    }
                    if (cassaforteDaForzare.isOpen()) break;
                }
                if (cassaforteDaForzare.isOpen()) break;
            }
        return combinazione.toString();
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     *
     * @return il numero di tentativi che ci sono voluti per trovare la
     * combinazione, oppure -1 se la cassaforte non è stata ancora
     * forzata.
     */
    public long getAttempts() {
        return tentativi;
    }
}
