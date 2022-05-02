import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        String myDate = "12/09/2011";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(myDate);
        System.out.println(date1.getTime());
    }
}
