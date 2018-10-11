package demo.utt37.congcau.apptracnghiem.config.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hepper {

        public static String setDate() {
            String date = "";
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simple = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
            date = simple.format(calendar.getTime());
            return date;
        }
}
