package it.unicam.cs.asdl2122.es4;

import java.util.Arrays;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 */
public class Aula implements Comparable<Aula> {

    /*
     * numero iniziale delle posizioni dell'array facilities. Se viene richiesto
     * di inserire una facility e l'array è pieno questo viene raddoppiato. La
     * costante è protected solo per consentirne l'accesso ai test JUnit
     */
    protected static final int INIT_NUM_FACILITIES = 5;

    /*
     * numero iniziale delle posizioni dell'array prenotazioni. Se viene
     * richiesto di inserire una prenotazione e l'array è pieno questo viene
     * raddoppiato. La costante è protected solo per consentirne l'accesso ai
     * test JUnit.
     */
    protected static final int INIT_NUM_PRENOTAZIONI = 100;

    // Identificativo unico di un'aula
    private final String nome;

    // Location dell'aula
    private final String location;

    /*
     * Insieme delle facilities di quest'aula. L'array viene creato all'inizio
     * della dimensione specificata nella costante INIT_NUM_FACILITIES. Il
     * metodo addFacility(Facility) raddoppia l'array qualora non ci sia più
     * spazio per inserire la facility.
     */
    private Facility[] facilities;

    // numero corrente di facilities inserite
    private int numFacilities;

    /*
     * Insieme delle prenotazioni per quest'aula. L'array viene creato
     * all'inizio della dimensione specificata nella costante
     * INIT_NUM_PRENOTAZIONI. Il metodo addPrenotazione(TimeSlot, String,
     * String) raddoppia l'array qualora non ci sia più spazio per inserire la
     * prenotazione.
     */
    private Prenotazione[] prenotazioni;

    // numero corrente di prenotazioni inserite
    private int numPrenotazioni;

    /**
     * Costruisce una certa aula con nome e location. Il set delle facilities è
     * vuoto. L'aula non ha inizialmente nessuna prenotazione.
     *
     * @param nome     il nome dell'aula
     * @param location la location dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni
     *                              richieste è nulla
     */
    public Aula(String nome, String location) {
        if (nome == null || location == null) throw new NullPointerException("Il nome o la location dell'aula sono" +
                "null");
        facilities = new Facility[INIT_NUM_FACILITIES];
        prenotazioni = new Prenotazione[INIT_NUM_PRENOTAZIONI];
        this.nome = nome;
        this.location = location;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + nome.hashCode();
        return hash;
    }

    /* Due aule sono uguali se e solo se hanno lo stesso nome */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Aula)) return false;
        Aula temp = (Aula) obj;
        return nome.equals(temp.nome);
    }

    /* L'ordinamento naturale si basa sul nome dell'aula */
    @Override
    public int compareTo(Aula o) {
        return nome.compareTo(o.nome);
    }

    /**
     * @return the facilities
     */
    public Facility[] getFacilities() {
        return this.facilities;
    }

    /**
     * @return il numero corrente di facilities
     */
    public int getNumeroFacilities() {
        return this.numFacilities;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * @return the prenotazioni
     */
    public Prenotazione[] getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * @return il numero corrente di prenotazioni
     */
    public int getNumeroPrenotazioni() {
        return this.numPrenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula. Controlla se la facility è già
     * presente, nel qual caso non la inserisce.
     *
     * @param f la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     * aggiunta, false altrimenti
     * @throws NullPointerException se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        /*
         * Nota: attenzione! Per controllare se una facility è già presente
         * bisogna usare il metodo equals della classe Facility.
         *
         * Nota: attenzione bis! Si noti che per le sottoclassi di Facility non
         * è richiesto di ridefinire ulteriormente il metodo equals...
         */
        if (f == null) throw new NullPointerException("La facility passatta è null");
        //Se facilities è vuoto
        if (numFacilities == 0) {
            facilities[0] = f;
            numFacilities++;
            return true;
        }
        //Controllo se la facility esiste
        for (int i = 0; i < numFacilities; i++) {
            if (facilities[i].equals(f)) return false;
        }
        //Se facilities è pieno ne raddoppio lo spazio
        if (facilities[facilities.length - 1] != null) facilities = Arrays.copyOf(facilities,
                facilities.length * 2);
        facilities[numFacilities] = f;
        numFacilities++;
        return true;
    }

    /**
     * Determina se l'aula è libera in un certo time slot.
     *
     * @param ts il time slot da controllare
     * @return true se l'aula risulta libera per tutto il periodo del time slot
     * specificato
     * @throws NullPointerException se il time slot passato è nullo
     */
    public boolean isFree(TimeSlot ts) {
        if (ts == null) throw new NullPointerException("Il time slot passato è nullo");
        for (int i = 0; i < numPrenotazioni; i++) if (prenotazioni[i].getTimeSlot().overlapsWith(ts)) return false;
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     *
     * @param requestedFacilities l'insieme di facilities richieste da
     *                            soddisfare, sono da considerare solo le
     *                            posizioni diverse da null
     * @return true se e solo se tutte le facilities di
     * {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Facility[] requestedFacilities) {
        if (requestedFacilities == null) throw new NullPointerException("il set di facility richieste è nullo");
        for (int i = 0; i < requestedFacilities.length; i++) {
            if (requestedFacilities[i] != null) {
                if (!(Arrays.asList(facilities).contains(requestedFacilities[i]))) return false;
            }
        }
        return true;
    }

    /**
     * Prenota l'aula controllando eventuali sovrapposizioni.
     *
     * @param ts
     * @param docente
     * @param motivo
     * @throws IllegalArgumentException se la prenotazione comporta una
     *                                  sovrapposizione con un'altra
     *                                  prenotazione nella stessa aula.
     * @throws NullPointerException     se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public void addPrenotazione(TimeSlot ts, String docente, String motivo) {
        if (ts == null || docente == null || motivo == null)
            throw new NullPointerException("ts o docente o motivo sono" +
                    "null");
        if (isFree(ts)) {
            Prenotazione tempPrenotazione = new Prenotazione(this, ts, docente, motivo);
            //Se array prenotazione è pieno ne raddoppio lo spazio
            if(prenotazioni[prenotazioni.length-1] !=null) prenotazioni = Arrays.copyOf(prenotazioni,
                    prenotazioni.length*2);
            prenotazioni[numPrenotazioni] = tempPrenotazione;
            numPrenotazioni++;
        } else {
            throw new IllegalArgumentException("L'aula è occupata per quel determinato timeslot");
        }
    }

    // TODO inserire eventuali metodi privati per questioni di organizzazione
}
