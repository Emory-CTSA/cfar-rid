package edu.ctsa.emory.cfar_rid.embedded;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class CfarLymphocyteKey implements Serializable {
    private String studyId;
    private Date labDate; // Assuming labDate could be part of the composite key

    // Getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CfarLymphocyteKey)) return false;
        CfarLymphocyteKey that = (CfarLymphocyteKey) o;
        return studyId.equals(that.studyId) &&
                Objects.equals(labDate, that.labDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyId, labDate);
    }
}
