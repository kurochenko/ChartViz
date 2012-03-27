package net.kurochenko.chartviz.backend.parser.ecb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ExchangeRateDTO {
    
    private Date day;
    private Map<String, BigDecimal> rates;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRateDTO that = (ExchangeRateDTO) o;

        if (rates != null ? !rates.equals(that.rates) : that.rates != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }
}
