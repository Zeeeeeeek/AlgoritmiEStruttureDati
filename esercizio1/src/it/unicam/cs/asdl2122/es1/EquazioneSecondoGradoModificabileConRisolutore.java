/**
 *
 */
package it.unicam.cs.asdl2122.es1;

/**
 * Un oggetto di questa classe permette di rappresentare una equazione di
 * secondo grado e di trovarne le soluzioni reali. I valori dei parametri
 * dell'equazione possono cambiare nel tempo. All'inizio e ogni volta che viene
 * cambiato un parametro la soluzione dell'equazione non esiste e deve essere
 * calcolata con il metodo <code>solve()</code>. E' possibile sapere se al
 * momento la soluzione dell'equazione esiste con il metodo
 * <code>isSolved()</code>. Qualora la soluzione corrente non esista e si tenti
 * di ottenerla verrà lanciata una eccezione.
 *
 * @author Template: Luca Tesei, Implementation: Collettiva da Esercitazione a
 *         Casa
 *
 */
public class EquazioneSecondoGradoModificabileConRisolutore {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    private double a;

    private double b;

    private double c;

    private boolean solved;

    private SoluzioneEquazioneSecondoGrado lastSolution;

    /**
     * Costruisce una equazione di secondo grado modificabile. All'inizio
     * l'equazione non è risolta.
     *
     *
     * @param a
     *              coefficiente del termine x^2, deve essere diverso da zero.
     * @param b
     *              coefficiente del termine x
     * @param c
     *              termine noto
     * @throws IllegalArgumentException
     *                                      se il parametro <code>a</code> è
     *                                      zero
     *
     */
    public EquazioneSecondoGradoModificabileConRisolutore(double a, double b, double c) {
        if (Math.abs(a) < EPSILON) {
            throw new IllegalArgumentException("Il parametro a non può essere zero");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return il valore corrente del parametro a
     */
    public double getA() {
        return a;
    }

    /**
     * Cambia il parametro a di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     *
     * @param a
     *              il nuovo valore del parametro a
     * @throws IllegalArgumentException
     *                                      se il nuovo valore è zero
     */
    public void setA(double a) {
        if (Math.abs(a) < EPSILON) {
            throw new IllegalArgumentException("Il parametro a non può essere zero");
        }
        this.a = a;
        this.solved = false;
        this.solve();
    }

    /**
     * @return il valore corrente del parametro b
     */
    public double getB() {
        return b;
    }

    /**
     * Cambia il parametro b di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     *
     * @param b
     *              il nuovo valore del parametro b
     */
    public void setB(double b) {
        this.b = b;
        this.solved = false;
        this.solve();
    }

    /**
     * @return il valore corrente del parametro c
     */
    public double getC() {
        return c;
    }

    /**
     * Cambia il parametro c di questa equazione. Dopo questa operazione
     * l'equazione andrà risolta di nuovo.
     *
     * @param c
     *              il nuovo valore del parametro c
     */
    public void setC(double c) {
        this.c = c;
        this.solved = false;
        this.solve();
    }

    /**
     * Determina se l'equazione, nel suo stato corrente, è già stata risolta.
     *
     * @return true se l'equazione è risolta, false altrimenti
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Risolve l'equazione risultante dai parametri a, b e c correnti. Se
     * l'equazione era già stata risolta con i parametri correnti non viene
     * risolta di nuovo.
     */
    public void solve() {
        if (lastSolution != null && lastSolution.e.getA() == this.a && lastSolution.e.getB() == this.b && lastSolution.e.getC() == c) {
                this.solved = true;
            } else {
            double delta;
            delta = this.b * this.b - 4 * this.a * this.c;

            if (Math.abs(delta) < EPSILON) {
                double soluzione = -this.b / (2 * this.a);
                lastSolution = new SoluzioneEquazioneSecondoGrado(new EquazioneSecondoGrado(this.a, this.b, this.c), soluzione);
                this.solved = true;
            } else if (delta > EPSILON) {
                double s1 = (-this.b + Math.sqrt(delta)) / (2 * this.a);
                double s2 = (-this.b - Math.sqrt(delta)) / (2 * this.a);
                lastSolution = new SoluzioneEquazioneSecondoGrado(new EquazioneSecondoGrado(this.a, this.b, this.c), s1, s2);
                this.solved = true;
            } else {
                lastSolution = new SoluzioneEquazioneSecondoGrado(new EquazioneSecondoGrado(this.a, this.b, this.c));
                this.solved = true;
            }

            }
        }



    /**
     * Restituisce la soluzione dell'equazione risultante dai parametri
     * correnti. L'equazione con i parametri correnti deve essere stata
     * precedentemente risolta.
     *
     * @return la soluzione
     * @throws IllegalStateException
     *                                   se l'equazione risulta non risolta,
     *                                   all'inizio o dopo il cambiamento di
     *                                   almeno uno dei parametri
     */
    public SoluzioneEquazioneSecondoGrado getSolution() {
        if (this.solved && lastSolution != null) {
            return lastSolution;
        } else {
            throw new IllegalStateException("L'equazione non risulta risolta");
        }

    }
}
