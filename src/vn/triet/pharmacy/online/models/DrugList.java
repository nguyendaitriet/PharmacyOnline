package vn.triet.pharmacy.online.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DrugList {
    private static final Scanner input = new Scanner(System.in);

    private ArrayList<Drug> drugList;

    public DrugList() {
        this.drugList = new ArrayList<Drug>();
    }

    public DrugList(ArrayList<Drug> drugList) {
        this.drugList = drugList;
    }

    public void addNewDrug(Drug... newDrug) {
        Collections.addAll(this.drugList, newDrug);
//        for (Drug drug:newDrug) {
//            this.drugList.add(drug);
//        }
    }

    public void addNewDrug(String id, String drugName, int drugContent, int quantity, double pricePerTablet, String productionDate, String expirationDate) {
        Drug newDrug = new Drug(id, drugName, drugContent, quantity, pricePerTablet, productionDate, expirationDate);
        this.drugList.add(newDrug);
    }

    public void showDrugList() {
        System.out.println("---------------------------------------------------------------------------- DRUG LIST ----------------------------------------------------------------------------\n");
        System.out.printf("%-15s %-20s %-25s %-25s %-25s %-25s %-25s\n", "ID", "Drug Name", "Drug Content (mg)", "Quantity (tablet)", "Price Per Tablet", "Production Date", "Expiration Date");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Drug drug : drugList) {
            System.out.printf("%-15s %-20s %-25s %-25s %-25s %-25s %-25s \n", drug.getId(), drug.getDrugName(), drug.getDrugContent(),
                    drug.getQuantity(), drug.getPricePerTablet(), drug.getProductionDate(), drug.getExpirationDate());
        }
    }

    public boolean isEmptyList() {
        return this.drugList.isEmpty();
    }

    public int getTheNumberOfDrugs() {
        return this.drugList.size();
    }

    public void removeAllDrugs() {
        this.drugList.removeAll(drugList);
    }

    private Drug getInputId() {
        System.out.print("Enter drug ID: ");
        String id = input.next();
        return new Drug(id);
    }

    public boolean isExistedInList() {
        return this.drugList.contains(getInputId());
    }

    public boolean removeDrugFromList() {
        return this.drugList.remove(getInputId());
    }

    //Not completed
    public String searchDrugName() {
        System.out.print("Enter drug name: ");
        String drugName = input.next();
        String result = "";
//        for (Drug drug : drugList) {
//            if (drug.getDrugName().toLowerCase().contains(drugName)) {
//                return "Your drug " + drug.getDrugName() + " is available.";
//            }
//        }
        for (Drug drug : drugList) {
            if (drug.getDrugName().toLowerCase().contains(drugName)) {
                result += drug.getDrugName() + " ";
            }
        }
        return result.equals("") ? "No drug found!" : "Your drug " + result + " is/are available.";
    }

    public void sortByDrugName() {
        this.drugList.sort((o1, o2) -> Integer.compare(o1.getDrugName().compareTo(o2.getDrugName()), 0)
//                if (o1.getDrugName().compareTo(o2.getDrugName()) > 0)
//                    return 1;
//                if (o1.getDrugName().compareTo(o2.getDrugName()) < 0)
//                    return -1;
//                return 0;
        );
    }

    public void sortByQuantity() {
        this.drugList.sort((o1, o2) -> Integer.compare(o2.getQuantity(), o1.getQuantity()));
    }

    public void sortByPricePerTablet() {
        this.drugList.sort((o1, o2) -> Double.compare(o2.getPricePerTablet(), o1.getPricePerTablet()));
    }

    public void sortByProductionDate() {
        this.drugList.sort((o1, o2) -> Integer.compare(o1.getProductionDate().compareTo(o2.getProductionDate()), 0));
    }
}
