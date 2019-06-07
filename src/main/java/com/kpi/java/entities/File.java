package com.kpi.java.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FILES")
@SequenceGenerator(name = "fileSequence", sequenceName = "FILE_SEQ", allocationSize = 1)
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FILE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileSequence")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VERSION")
    private Long version;

    @OneToOne
    @JoinColumn(name = "PREVIOUS_FILE_FK", referencedColumnName = "FILE_ID")
    private File previousFile;

    @ManyToOne
    @JoinColumn(name = "PROGRAM_PRODUCT_FK", referencedColumnName = "PP_ID")
    private ProgramProduct programProduct;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public File getPreviousFile() {
        return previousFile;
    }

    public void setPreviousFile(File previousFile) {
        this.previousFile = previousFile;
    }

    public ProgramProduct getProgramProduct() {
        return programProduct;
    }

    public void setProgramProduct(ProgramProduct programProduct) {
        this.programProduct = programProduct;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("name", name)
                .append("version", version)
                .append("previousFile", previousFile)
                .append("programProduct", programProduct)
                .toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        File rhs = (File) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.version, rhs.version)
                .append(this.programProduct, rhs.programProduct)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(version)
                .append(programProduct)
                .toHashCode();
    }
}
