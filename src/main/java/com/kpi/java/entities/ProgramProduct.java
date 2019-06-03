package com.kpi.java.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PROGRAM_PRODUCT")
@SequenceGenerator(name = "programSequence", sequenceName = "PROGRAM_SEQ")
public class ProgramProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programSequence")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VERSION")
    private Long version;

    @OneToMany(mappedBy = "programProduct", fetch = FetchType.EAGER)
    private List<File> files;

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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("name", name)
                .append("version", version)
                .append("files", files)
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
        ProgramProduct rhs = (ProgramProduct) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.version, rhs.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(version)
                .toHashCode();
    }
}
