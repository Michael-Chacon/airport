package org.vuelosGlobales.maintenanceTechnician.revision.domain;

public class Revision {
    private int id;
    private String revisionDate;
    private int idPlane;
    private String description;

    public Revision() {
    }

    public Revision(int id, String revisionDate, int idPlane, String description) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.idPlane = idPlane;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
