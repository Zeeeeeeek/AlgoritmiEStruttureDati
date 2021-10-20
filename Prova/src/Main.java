import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        String[] lista = new String[]{"aa", "bb", null};
        System.out.println(Arrays.asList(lista).contains("bb"));
    }
}
