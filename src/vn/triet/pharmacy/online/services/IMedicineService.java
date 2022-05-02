package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Drug;


import java.util.List;

public interface IMedicineService {

    List<Drug> getDrugs();

    Drug getDrugById(int id);

    void add(Drug newDrug);

    void update(Drug newDrug);

    boolean isIdExisted(int id);

    void remove(Drug drug);

}
