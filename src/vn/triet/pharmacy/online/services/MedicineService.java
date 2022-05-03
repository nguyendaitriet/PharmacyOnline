package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Drug;
import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class MedicineService implements IMedicineService{
    public static String path = "data/drugs.csv";

    @Override
    public List<Drug> getDrugs() {
        List<Drug> newDrugsList = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            newDrugsList.add(new Drug(record));
        }
        return newDrugsList;
    }

    @Override
    public Drug getDrugById(int id) {
        List<Drug> drugs = getDrugs();
        for (Drug drug : drugs) {
            if (drug.getId() == id)
                return drug;
        }
        return null;
    }


    @Override
    public void add(Drug newDrug) {
        List<Drug> drugs = getDrugs();
        drugs.add(newDrug);
        CSVUtils.writeData(path, drugs);
    }

    @Override
    public void update(Drug newDrug) {
        List<Drug> drugs = getDrugs();
        for (Drug drug : drugs) {
            if (drug.getId() == newDrug.getId()) {
                if (newDrug.getDrugName() != null && !newDrug.getDrugName().isEmpty())
                    drug.setDrugName(newDrug.getDrugName());

                if (newDrug.getDrugContent() != drug.getDrugContent())
                    drug.setDrugContent(newDrug.getDrugContent());

                if (newDrug.getQuantity() != drug.getQuantity())
                    drug.setQuantity(newDrug.getQuantity());

                if (newDrug.getDosageForm() != null && !newDrug.getDosageForm().isEmpty())
                    drug.setDosageForm(newDrug.getDosageForm());

                if (newDrug.getUsage() != null && !newDrug.getUsage().isEmpty())
                    drug.setUsage(newDrug.getUsage());

                if (newDrug.getDosagePerDay() != null && !newDrug.getDosagePerDay().isEmpty())
                    drug.setDosagePerDay(newDrug.getDosagePerDay());

                if (newDrug.getTotalDosage5Days() != drug.getTotalDosage5Days())
                    drug.setTotalDosage5Days(newDrug.getTotalDosage5Days());

                if (newDrug.getPricePerPill() != drug.getPricePerPill())
                    drug.setPricePerPill(newDrug.getPricePerPill());

                if (newDrug.getProductionDate() != null && !newDrug.getProductionDate().isEmpty())
                    drug.setProductionDate(newDrug.getProductionDate());

                if (newDrug.getExpirationDate() != null && !newDrug.getExpirationDate().isEmpty())
                    drug.setExpirationDate(newDrug.getExpirationDate());

                if (newDrug.getNote() != null && !newDrug.getNote().isEmpty())
                    drug.setNote(newDrug.getNote());

                CSVUtils.writeData(path, drugs);
                break;
            }
        }
    }

    @Override
    public boolean isIdExisted(int id) {
        return getDrugById(id) != null;
    }

    @Override
    public void remove(Drug drug) {
        List<Drug> drugs = getDrugs();
        drugs.remove(drug);
        CSVUtils.writeData(path, drugs);
    }
}
