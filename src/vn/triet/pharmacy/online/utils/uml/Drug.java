package vn.triet.pharmacy.online.utils.uml;

import java.util.Objects;

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

    public Drug() {}

    public Drug(String raw) {
        String[] drugInformation = raw.split("~");
        this.id = Long.parseLong(drugInformation[0]);
        this.drugName = drugInformation[1];
        this.drugContent = Double.parseDouble(drugInformation[2]);
        this.quantity = Integer.parseInt(drugInformation[3]);
        this.dosageForm = drugInformation[4];
        this.usage = drugInformation[5];
        this.dosagePerDay = drugInformation[6];
        this.totalDosage5Days = Integer.parseInt(drugInformation[7]);
        this.pricePerPill = Double.parseDouble(drugInformation[8]);
        this.productionDate = drugInformation[9];
        this.expirationDate = drugInformation[10];
        this.note = drugInformation[11];
    }

    public void add(vn.triet.pharmacy.online.models.Drug newDrug) {

    }

    public void update(vn.triet.pharmacy.online.models.Drug newDrug) {

    }

    public void remove(vn.triet.pharmacy.online.models.Drug drug) {
        
    }
}
