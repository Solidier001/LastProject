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

public class OrderKeyGenreater implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String timecount=Long.toString(date.getTime());
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder s=new StringBuilder(matcher.replaceAll(""));
        s.delete(6,s.length()-1);
        s.append(format.format(date).trim());
        s.append(timecount.substring(timecount.length()-4));
        return s.toString();
    }
    public static String getkey(){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String timecount=Long.toString(date.getTime());
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder s=new StringBuilder(matcher.replaceAll(""));
        s.delete(6,s.length()-1);
        s.append(format.format(date).trim());
        s.append(timecount.substring(timecount.length()-4));
        return s.toString();
    }
}
