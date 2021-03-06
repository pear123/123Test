package converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YearMonthConverter   implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat("yyyy-MM").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
