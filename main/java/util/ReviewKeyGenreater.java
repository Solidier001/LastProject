package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewKeyGenreater {
    public String getDirKey(){
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder s=new StringBuilder(matcher.replaceAll(""));
        s.delete(4,s.length());
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String timecount=Long.toString(date.getTime()%10000);
        s.append(format.format(date));
        s.append(timecount);
        return s.toString();
    }
    public String getKey(){
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder s=new StringBuilder(matcher.replaceAll(""));
        s.delete(2,s.length());
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        s.append(format.format(new Date()));
        return s.toString();
    }
}
