package CLI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class utilities {
    public static String time(){
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        return ft.format(date);
    }


    public  static boolean contains(String[] st,String name){
        for (int i = 0; i <st.length ; i++) {
            if (st[i].equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static int firstIndex(String[] names,String name){
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])){
                return i;
            }
        }
        return -1;
    }

}
