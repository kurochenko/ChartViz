package net.kurochenko.chartviz.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Entity
public class ChartData implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;
    
    @NotNull
    private BigDecimal value;
    
    @ManyToOne    
    private Chart chart;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartData chartData = (ChartData) o;

        if (chart != null ? !chart.equals(chartData.chart) : chartData.chart != null) return false;
        if (id != null ? !id.equals(chartData.id) : chartData.id != null) return false;
        if (time != null ? !time.equals(chartData.time) : chartData.time != null) return false;
        if (value != null ? !value.equals(chartData.value) : chartData.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (chart != null ? chart.hashCode() : 0);
        return result;
    }
}
