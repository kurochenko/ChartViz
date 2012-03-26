package net.kurochenko.chartviz.backend.entity;

import java.util.List;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public class ChartDTO {

    private Long id;
    private String name;
    private String domainAxeName;
    private String rangeAxeName;
    private String unit;
    private List<ChartData> data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainAxeName() {
        return domainAxeName;
    }

    public void setDomainAxeName(String domainAxeName) {
        this.domainAxeName = domainAxeName;
    }

    public String getRangeAxeName() {
        return rangeAxeName;
    }

    public void setRangeAxeName(String rangeAxeName) {
        this.rangeAxeName = rangeAxeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<ChartData> getData() {
        return data;
    }

    public void setData(List<ChartData> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChartDTO chartDTO = (ChartDTO) o;

        if (domainAxeName != null ? !domainAxeName.equals(chartDTO.domainAxeName) : chartDTO.domainAxeName != null)
            return false;
        if (data != null ? !data.equals(chartDTO.data) : chartDTO.data != null) return false;
        if (id != null ? !id.equals(chartDTO.id) : chartDTO.id != null) return false;
        if (name != null ? !name.equals(chartDTO.name) : chartDTO.name != null) return false;
        if (rangeAxeName != null ? !rangeAxeName.equals(chartDTO.rangeAxeName) : chartDTO.rangeAxeName != null)
            return false;
        if (unit != null ? !unit.equals(chartDTO.unit) : chartDTO.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (domainAxeName != null ? domainAxeName.hashCode() : 0);
        result = 31 * result + (rangeAxeName != null ? rangeAxeName.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
