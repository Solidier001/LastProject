package util;

import daomain.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Essaykey {
    public static String Generator(User user){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        String key=format.format(date)+user.getId()+".txt";
        return key;
    }
}
