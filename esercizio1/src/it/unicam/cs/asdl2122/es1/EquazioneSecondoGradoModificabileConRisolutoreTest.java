/**
 *
 */
package it.unicam.cs.asdl2122.es1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author Template: Luca Tesei, Implementation: Collettiva da Esercitazione a
 *         Casa
 *
 */
class EquazioneSecondoGradoModificabileConRisolutoreTest {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    static final double EPSILON = 1.0E-15;

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#EquazioneSecondoGradoModificabileConRisolutore(double, double, double)}.
     */
    @Test
    final void testEquazioneSecondoGradoModificabileConRisolutore() {
        // controllo che il valore 0 su a lanci l'eccezione
        assertThrows(IllegalArgumentException.class,
                () -> new EquazioneSecondoGradoModificabileConRisolutore(0, 1, 1));
        // devo controllare che comunque nel caso normale il costruttore
        // funziona
        new EquazioneSecondoGradoModificabileConRisolutore(1, 1, 1);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#getA()}.
     */
    @Test
    final void testGetA() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e1 = new EquazioneSecondoGradoModificabileConRisolutore(
                x, 1, 1);
        // controllo che il valore restituito sia quello che ho messo
        // all'interno
        // dell'oggetto
        assertEquals(x, e1.getA());
        // in generale si dovrebbe usare assertTrue(Math.abs(x -
        // e1.getA())<EPSILON) ma in
        // questo caso il valore che testiamo non ha subito manipolazioni quindi
        // la sua rappresentazione sarà la stessa di quella inserita nel
        // costruttore senza errori di approssimazione

    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#setA(double)}.
     */
    @Test
    final void testSetA() {
        EquazioneSecondoGradoModificabileConRisolutore e2 = new EquazioneSecondoGradoModificabileConRisolutore(10, 1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            e2.setA(0);
        });
        e2.setA(11);
        assertEquals(11, e2.getA());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#getB()}.
     */
    @Test
    final void testGetB() {
        EquazioneSecondoGradoModificabileConRisolutore e3 = new EquazioneSecondoGradoModificabileConRisolutore(10, 0, 1);
        assertEquals(e3.getB() , 0);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#setB(double)}.
     */
    @Test
    final void testSetB() {
        EquazioneSecondoGradoModificabileConRisolutore e4 = new EquazioneSecondoGradoModificabileConRisolutore(15, 8, 9);
        e4.setB(15);
        assertEquals(e4.getB() , 15);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#getC()}.
     */
    @Test
    final void testGetC() {
        EquazioneSecondoGradoModificabileConRisolutore e5 = new EquazioneSecondoGradoModificabileConRisolutore(16, 8, 13);
        assertEquals(e5.getC(), 13);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#setC(double)}.
     */
    @Test
    final void testSetC() {
        EquazioneSecondoGradoModificabileConRisolutore e6 = new EquazioneSecondoGradoModificabileConRisolutore(12, 3, 6);
        e6.setC(12);
        assertEquals(e6.getC(), 12);
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#isSolved()}.
     */
    @Test
    final void testIsSolved() {
        EquazioneSecondoGradoModificabileConRisolutore e7 = new EquazioneSecondoGradoModificabileConRisolutore(7, -3, -4);
        e7.solve();
        assertTrue(e7.isSolved());
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#solve()}.
     */
    @Test
    final void testSolve() {
        EquazioneSecondoGradoModificabileConRisolutore e3 = new EquazioneSecondoGradoModificabileConRisolutore(
                1, 1, 3);
        // controllo semplicemente che la chiamata a solve() non generi errori
        e3.solve();
        // i test con i valori delle soluzioni vanno fatti nel test del metodo
        // getSolution()
    }

    /**
     * Test method for
     * {@link it.unicam.cs.asdl2122.es1.EquazioneSecondoGradoModificabileConRisolutore#getSolution()}.
     */
    @Test
    final void testGetSolution() {
        EquazioneSecondoGradoModificabileConRisolutore e8 = new EquazioneSecondoGradoModificabileConRisolutore(5, -2, -16);
        e8.solve();
        double soluzione1 = e8.getSolution().getS1();
        double soluzione2 = e8.getSolution().getS2();
        assertEquals(2, soluzione1);
        assertEquals(-1.6, soluzione2);
    }

}
