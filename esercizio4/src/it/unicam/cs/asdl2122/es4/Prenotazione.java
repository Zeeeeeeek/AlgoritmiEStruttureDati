package it.unicam.cs.asdl2122.es4;

/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

    private final TimeSlot timeSlot;

    private final String docente;

    private final String motivo;

    /**
     * Costruisce una prenotazione.
     *
     * @param aula
     *                     l'aula a cui la prenotazione si riferisce
     * @param timeSlot
     *                     il time slot della prenotazione
     * @param docente
     *                     il nome del docente che ha prenotato l'aula
     * @param motivo
     *                     il motivo della prenotazione
     * @throws NullPointerException
     *                                  se uno qualsiasi degli oggetti passati è
     *                                  null
     */
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente,
                        String motivo) {
        if (aula == null || timeSlot == null || docente == null || motivo == null) throw new NullPointerException("I " +
                "parametri di una prenotazione non possono essere null");
        this.aula = aula;
        this.timeSlot = timeSlot;
        this.motivo = motivo;
        this.docente = docente;
    }

    /**
     * @return the aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @return the timeSlot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + aula.hashCode();
        hash = 31 * hash + timeSlot.hashCode();
        return hash;
    }

    /*
     * L'uguaglianza è data solo da stessa aula e stesso time slot. Non sono
     * ammesse prenotazioni diverse con stessa aula e stesso time slot.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Prenotazione)) return false;
        if(this == obj) return true;
        Prenotazione temp = (Prenotazione) obj;
        return (aula.equals(temp.aula) && timeSlot.equals(temp.timeSlot));
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot. Se
     * due prenotazioni hanno lo stesso time slot allora una precede l'altra in
     * base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {
        if(this.equals(o)) return 0;
        if(timeSlot.equals(o.timeSlot)) return aula.compareTo(o.aula);
        return timeSlot.compareTo(o.timeSlot);
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}