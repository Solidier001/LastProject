package util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewKeyGenreater implements IdentifierGenerator {
    public String getReviewKey(){
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

    public static String gerDirKey(){
        long timevalue=new Date().getTime();
        String appendix=String.valueOf(timevalue);
        appendix=appendix.substring(appendix.length()-6);
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder str=new StringBuilder(matcher.replaceAll(""));
        str.delete(10,str.length());
        str.append(appendix);
        return str.toString();
    }


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return getReviewKey();
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }
}
