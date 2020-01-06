package util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserKeyGenreater implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Date date=new Date();
        Pattern p=Pattern.compile("\\D");
        Matcher matcher=p.matcher(new StringBuilder(UUID.randomUUID().toString()));
        StringBuilder builder=new StringBuilder(matcher.replaceAll(""));
        for (int i=builder.length();i>5;i=builder.length())
            builder.deleteCharAt(new Random().nextInt(i));
        String change=Long.toString(date.getTime());
        builder.insert(0,change.substring(change.length()-7,change.length()-1));
        String code=builder.toString();
        return code;
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }
}
