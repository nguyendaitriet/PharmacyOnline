package vn.triet.pharmacy.online.uml;

import java.util.List;

public class Drug {
    private long id;
    private String drugName;
    private double drugContent;
    private int quantity;
    private String dosageForm;
    private String usage;
    private String dosagePerDay;
    private int totalDosage5Days;
    private double pricePerPill;
    private String productionDate;
    private String expirationDate;
    private String note;

    public List<Drug> getDrugs() {
        return null;
    }

    public Drug getDrugById(int id) {
        return null;
    }

    public void add(Drug newDrug) {
    }

    ;

    public void update(Drug newDrug) {
    }

    ;

    public boolean isIdExisted(int id) {
        return true;
    }

    ;

    public void remove(Drug drug) {
    }

    ;

    public List<Drug> getSearchDrugList(String searchContent, List<Drug> userDrugsList) {
        return null;
    }

    ;
}
