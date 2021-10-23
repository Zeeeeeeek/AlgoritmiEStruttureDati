
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        GregorianCalendar a = new GregorianCalendar(2021,10,23, 11, 00);
        GregorianCalendar b = new GregorianCalendar(2021,10,23, 12, 00);

        System.out.println(a.compareTo(b));
    }
}
