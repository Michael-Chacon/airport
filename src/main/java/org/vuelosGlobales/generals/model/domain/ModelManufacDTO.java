package org.vuelosGlobales.generals.model.domain;

public class ModelManufacDTO {
    private int id;
    private String nameModel;
    private String nameManufac;

    public ModelManufacDTO() {
    }

    public ModelManufacDTO(int id, String nameModel, String nameManufac) {
        this.id = id;
        this.nameModel = nameModel;
        this.nameManufac = nameManufac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public String getNameManufac() {
        return nameManufac;
    }

    public void setNameManufac(String nameManufac) {
        this.nameManufac = nameManufac;
    }

    @Override
    public String toString() {
        return "id: " + id + ", modelo: " + nameModel + ", fabricane: " + getNameManufac();
    }
}
