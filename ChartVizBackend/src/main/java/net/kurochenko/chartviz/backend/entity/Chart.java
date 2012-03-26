package net.kurochenko.chartviz.backend.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Entity
public class Chart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(max = 64)
    private String name;
    
    @NotBlank
    @Length(max = 64)
    private String domainAxeName;

    @NotBlank
    @Length(max = 64)
    private String rangeAxeName;

    @NotBlank
    @Length(max = 16)
    private String unit;


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

    public void setDomainAxeName(String baseAxeName) {
        this.domainAxeName = baseAxeName;
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


}
