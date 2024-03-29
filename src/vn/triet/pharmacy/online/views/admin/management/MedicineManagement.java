package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.Drug;
import vn.triet.pharmacy.online.services.IOderItemService;
import vn.triet.pharmacy.online.services.OrderItemService;
import vn.triet.pharmacy.online.views.Exit;
import vn.triet.pharmacy.online.services.IMedicineService;
import vn.triet.pharmacy.online.services.MedicineService;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.AdminView;
import vn.triet.pharmacy.online.views.Menu;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MedicineManagement {
    private static IMedicineService medicineService = new MedicineService();
    private static IOderItemService orderItemService = new OrderItemService();
    private static final Scanner input = new Scanner(System.in);
    private static final int YEARS = 2;

    private static void showActionForm() {
        System.out.println("\n --------------- Medicine Management -----------------");
        System.out.println("|                                                     |");
        System.out.println("|        1. Show drugs list.                          |");
        System.out.println("|             2. Sort by quantity.                    |");
        System.out.println("|             3. Sort by price per pill.              |");
        System.out.println("|             4. Show expired drugs list.             |");
        System.out.println("|             5. Check a drug detail (by ID).         |");
        System.out.println("|             6. Search drug by name.                 |");
        System.out.println("|        7. Add new drug.                             |");
        System.out.println("|        0. Return.                                   |");
        System.out.println("|                                                     |");
        System.out.println(" -----------------------------------------------------");
    }

    public static void chooseActionInMedicineManagement() {
        List<Drug> drugs = medicineService.getDrugs();
        do {
            showActionForm();
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    showDrugsList(drugs, 1);
                    break;
                }
                if (number == 2) {
                    showDrugsList(drugs, 2);
                    break;
                }
                if (number == 3) {
                    showDrugsList(drugs, 3);
                    break;
                }
                if (number == 4) {
                    showDrugsList(drugs, 4);
                    break;
                }
                if (number == 5) {
                    updateDrug();
                    break;
                }
                if (number == 6) {
                    searchDrugByName(drugs);
                    break;
                }
                if (number == 7) {
                    addNewDrug();
                    break;
                }
                if (number == 0) {
                    AdminView.chooseAdminAction();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
        } while (true);
    }

    public static void showDrugsList(List<Drug> drugs, int action) throws ParseException {
        switch (action) {
            case 1:
                showAllDrugs(drugs);
                break;
            case 2:
                sortByQuantityASCE(drugs);
                break;
            case 3:
                sortByPricePerPillASCE(drugs);
                break;
            case 4:
                showExpiredDrugs(drugs);
                break;
        }
        chooseNextOperation();
    }

    public static void showAllDrugs(List<Drug> drugs) {
        System.out.println("\nDRUGS LIST ----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-23s %-17s %-20s %-20s\n",
                "ID", "Drug Name", "Drug Content (mg)", "Price (VND)", "Quantity (pill)", "Expiration Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Drug drug : drugs) {
            System.out.printf("%-12s %-25s %-23s %-17s %-20s %-20s\n", drug.getId(), drug.getDrugName(), drug.getDrugContent(),
                    ValidateUtils.priceWithDecimal(drug.getPricePerPill()), drug.getQuantity(), drug.getExpirationDate());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }


    private static void chooseNextOperation() {
        do {
            System.out.println("\n---> Enter '1' to edit a drug (by ID).");
            System.out.println("---> Enter '2' to remove a drug (by ID).");
            System.out.println("---> Enter '0' to return.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    updateDrug();
                    break;
                }
                if (number == 2) {
                    removeDrug();
                    break;
                }
                if (number == 0) {
                    chooseActionInMedicineManagement();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void sortByQuantityASCE(List<Drug> drugs) {
        drugs.sort((e1, e2) -> Integer.compare(e1.getQuantity() - e2.getQuantity(), 0));
        showAllDrugs(drugs);
    }

    private static void sortByPricePerPillASCE(List<Drug> drugs) {
        drugs.sort((e1, e2) -> Double.compare(e1.getPricePerPill() - e2.getPricePerPill(), 0));
        showAllDrugs(drugs);
    }

    private static void showExpiredDrugs(List<Drug> drugs) throws ParseException {
        List<Drug> expiredDrugs = new ArrayList<>();
        for (Drug drug : drugs) {
            long expirationTime = ValidateUtils.convertDateToMilli(drug.getExpirationDate());
            long expiredTime = (long) (System.currentTimeMillis() + 2592 * Math.pow(10, 6));
            if (expirationTime <= expiredTime) {
                expiredDrugs.add(drug);
            }
        }
        System.out.println("\n----- This list includes drugs expired or is expiring next month -----");
        showAllDrugs(expiredDrugs);
    }

    private static void searchDrugByName(List<Drug> drugs) {
        boolean is;
        do {
            System.out.print("\nEnter drug name you want to search: ");
            String searchName = input.nextLine().toLowerCase().trim();
            List<Drug> drugsListSearch = medicineService.getSearchDrugList(searchName, drugs);
            if (drugsListSearch.size() == 0) {
                System.out.printf("\nCan't find drug with name '%s'. Do you want to try again?\n", searchName);
                do {
                    System.out.println("(Enter 'y' to find again or enter 'n' to exit)");
                    try {
                        String letter = Menu.chooseActionByLetter();
                        if (letter.charAt(0) == 'y' && letter.length() == 1) {
                            is = false;
                            break;
                        }
                        if (letter.charAt(0) == 'n' && letter.length() == 1) {
                            is = true;
                            chooseActionInMedicineManagement();
                            break;
                        }
                        Menu.alert();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Menu.alert();
                    }
                } while (true);
                continue;
            }
            showAllDrugs(drugsListSearch);
            is = true;
        } while (!is);
        chooseNextOperation();
    }

    public static void setID(Drug newDrug) {
        int min = 100000;
        int max = 999999;
        int id;
        do {
            id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        } while (medicineService.isIdExisted(id));
        newDrug.setId(id);
        System.out.println("1. Drug ID: " + id);
    }

    private static boolean cancelEntering(String string) {
        if (string.equalsIgnoreCase(Exit.E4)) {
            System.out.println("\nYour operation has been canceled!");
            chooseActionInMedicineManagement();
            return true;
        }
        return false;
    }

    private static boolean enterDrugName(Drug newDrug) {
        do {
            System.out.println("2. Enter Drug Name (Example: Paracetamol). ");
            System.out.print("==> ");
            String drugName = input.nextLine().trim();
            System.out.println();
            if (ValidateUtils.isNameValid(drugName)) {
                newDrug.setDrugName(drugName);
                return false;
            }
            if (cancelEntering(drugName)) return true;
            System.out.println("Invalid name format, please try again!\n");
        } while (true);
    }

    private static boolean enterDrugContent(Drug newDrug) {
        do {
            try {
                System.out.println("3. Enter Drug Content (mg). ");
                System.out.print("==> ");
                String drugContent = input.nextLine();
                if (cancelEntering(drugContent)) return true;
                double drugContentValue = Double.parseDouble(drugContent);
                System.out.println();
                if (drugContentValue <= 0) {
                    System.out.println("You can't enter a negative value or '0', please try again!\n");
                    continue;
                }
                newDrug.setDrugContent(drugContentValue);
                return false;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static boolean checkDuplicateDrug(Drug newDrug) {
        List<Drug> drugList = medicineService.getDrugs();
        for (Drug drug : drugList) {
            if (drug.equals(newDrug)) {
                System.out.println("This drug has existed. Please enter another drug!\n");
                return false;
            }
        }
        return true;
    }

    private static boolean enterQuantity(Drug newDrug) {
        do {
            try {
                System.out.println("4. Enter Quantity (pill). ");
                System.out.print("==> ");
                String quantity = input.nextLine();
                if (cancelEntering(quantity)) return true;
                int quantityValue = Integer.parseInt(quantity);
                System.out.println();
                if (quantityValue < 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setQuantity(quantityValue);
                return false;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static boolean enterDosageForm(Drug newDrug) {
        do {
            try {
                System.out.print("5. Enter dosage form (Choose '1' or '2').\n");
                System.out.printf("%-10s %-15s\n", " ", "1. Tablet.");
                System.out.printf("%-10s %-15s\n", " ", "2. Capsule.");
                System.out.print("==> ");
                String value = input.nextLine();
                if (cancelEntering(value)) return true;
                int number = Integer.parseInt(value);
                if (number == 1) {
                    newDrug.setDosageForm("tablet");
                    return false;
                }
                if (number == 2) {
                    newDrug.setDosageForm("capsule");
                    return false;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static boolean enterUsage(Drug newDrug) {
        System.out.println("6. Enter common usage (Example: fever, cough).");
        System.out.print("==> ");
        String usage = input.nextLine().trim();
        if (cancelEntering(usage)) return true;
        newDrug.setUsage(usage);
        return false;
    }

    private static boolean enterDosagePerDay(Drug newDrug) {
        System.out.println("7. Enter dosage per day (Example: 1 Morning, 1 Night).");
        System.out.print("==> ");
        String dosagePerDay = input.nextLine().trim();
        if (cancelEntering(dosagePerDay)) return true;
        newDrug.setDosagePerDay(dosagePerDay);
        return false;
    }

    private static boolean enterTotalDosage5Days(Drug newDrug) {
        do {
            try {
                System.out.println("8. Enter total dosage in 5 days.");
                System.out.print("==> ");
                String totalDosage5Days = input.nextLine();
                if (cancelEntering(totalDosage5Days)) return true;
                int totalDosage5DaysValue = Integer.parseInt(totalDosage5Days);
                if (totalDosage5DaysValue <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setTotalDosage5Days(totalDosage5DaysValue);
                return false;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static boolean enterPricePerPill(Drug newDrug) {
        do {
            try {
                System.out.println("9. Enter price per pill.");
                System.out.print("==> ");
                String pricePerPill = input.nextLine();
                if (cancelEntering(pricePerPill)) return true;
                double pricePerPillValue = Double.parseDouble(pricePerPill);
                if (pricePerPillValue <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setPricePerPill(pricePerPillValue);
                return false;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static boolean enterProductionDate(Drug newDrug) throws ParseException {
        do {
            System.out.println("10. Enter Production Date (Example: 12/04/2021).");
            System.out.print("==> ");
            String productionDate = input.nextLine().trim();
            if (cancelEntering(productionDate)) return true;
            System.out.println();
            if (!checkProductionDate(productionDate)) continue;
            if (ValidateUtils.isDateValid(productionDate)) {
                newDrug.setProductionDate(productionDate);
                return false;
            }
            System.out.println("Invalid date format, please try again!\n");
        } while (true);
    }

    private static boolean checkProductionDate(String productionDate) throws ParseException {
        long productionTime = ValidateUtils.convertDateToMilli(productionDate);
        long currentTime = System.currentTimeMillis();
        long range = Math.abs(currentTime - productionTime);
        if (productionTime > currentTime) {
            System.out.println("\nProduction Date must NOT be after today's date '" + ValidateUtils.convertMilliToDate(currentTime) + "'\n");
            return false;
        }
        if (TimeUnit.DAYS.convert(range, TimeUnit.MILLISECONDS) / 365.0 > 2) {
            long years2Milli = (long) YEARS * 1000 * 60 * 60 * 24 * 365;
            System.out.println("\nDrug produced over " + YEARS + " years ago from today MUST NOT be added!");
            System.out.println("(You have to enter the date after '" + ValidateUtils.convertMilliToDate(currentTime - years2Milli) + "')\n");
            return false;
        }
        return true;
    }

    private static boolean enterExpirationDate(Drug newDrug) throws ParseException {
        do {
            System.out.println("11. Enter Expiration Date (Example: 12/04/2022) ");
            System.out.print("==> ");
            String expirationDate = input.nextLine().trim();
            if (cancelEntering(expirationDate)) return true;
            System.out.println();
            if (!ValidateUtils.isDateValid(expirationDate)) {
                System.out.println("Invalid date format, please try again!\n");
                continue;
            }
            long expirationDateToMilli = ValidateUtils.convertDateToMilli(expirationDate);
            long productionDateToMilli = ValidateUtils.convertDateToMilli(newDrug.getProductionDate());
            if (expirationDateToMilli <= productionDateToMilli) {
                System.out.println("Expiration Date can't be after Production Date, please try again!\n");
                continue;
            }
            newDrug.setExpirationDate(expirationDate);
            return false;
        } while (true);
    }

    private static boolean enterNote(Drug newDrug) {
        System.out.println("12. Enter note (Example: antibiotic).");
        System.out.print("==> ");
        String note = input.nextLine();
        if (cancelEntering(note)) return true;
        newDrug.setNote(note);
        return false;
    }

    public static void showConfirmForm() {
        System.out.println("\nPlease confirm that you want to add a new drug with above information!");
        System.out.println("1. Agree to add new drug.");
        System.out.println("2. Cancel.\n");
    }

    public static void confirmAddingNewDrug(Drug newDrug) {
        do {
            try {
                showConfirmForm();
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    medicineService.add(newDrug);
                    System.out.println("\nNew drug has been added successfully!");
                    chooseActionInMedicineManagement();
                    break;
                }
                if (number == 2) {
                    chooseActionInMedicineManagement();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void showDrugDetail(Drug drug) {
        System.out.println("\nDRUG DETAIL ------------------------------------------\n");
        System.out.printf("%-30s %-12d\n", "1. ID:", drug.getId());
        System.out.printf("%-30s %-12s\n", "2. Generic Name:", drug.getDrugName());
        System.out.printf("%-30s %-12s\n", "3. Drug Content (mg):", drug.getDrugContent());
        System.out.printf("%-30s %-12s\n", "4. Quantity (pill):", drug.getQuantity());
        System.out.printf("%-30s %-12s\n", "5. Dosage Form:", drug.getDosageForm());
        System.out.printf("%-30s %-12s\n", "6. Usage:", drug.getUsage());
        System.out.printf("%-30s %-12s\n", "7. Dosage per Day:", drug.getDosagePerDay());
        System.out.printf("%-30s %-12s\n", "8. Total dosage in 5 days:", drug.getTotalDosage5Days());
        System.out.printf("%-30s %-12s\n", "9. Price (VND):", ValidateUtils.priceWithDecimal(drug.getPricePerPill()));
        System.out.printf("%-30s %-12s\n", "10. Production Date:", drug.getProductionDate());
        System.out.printf("%-30s %-12s\n", "11. Expiration Date:", drug.getExpirationDate());
        System.out.printf("%-30s %-12s\n", "12. Note:", drug.getNote());
        System.out.println("\n-----------------------------------------------------\n");
    }

    public static Drug getExistedDrug() {
        do {
            try {
                System.out.print("\nEnter Drug ID: ");
                int id = Integer.parseInt(input.nextLine());
                if (id == 0) {
                    chooseActionInMedicineManagement();
                    break;
                }
                if (!medicineService.isIdExisted(id)) {
                    System.out.println("\nID doesn't exist, please try again or enter '0' to return.");
                    continue;
                }
                if (orderItemService.isItemOrdered(id)) {
                    System.out.println("\nSorry, you can't remove this drug because it has been ordered");
                    System.out.println("Please enter another ID!");
                    continue;
                }
                return medicineService.getDrugById(id);
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
        return null;
    }

    public static void removeDrug() {
        Drug drug = getExistedDrug();
        do {
            System.out.printf("\nConfirm that you want to remove drug %s '%s %s mg'.\n", drug.getId(), drug.getDrugName(), drug.getDrugContent());
            System.out.println("1. Agree to remove.");
            System.out.println("2. Cancel.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    System.out.printf("\nDrug '%s' has been removed successfully!\n", drug.getId());
                    medicineService.remove(drug);
                    chooseActionInMedicineManagement();
                    break;
                }
                if (number == 2) {
                    chooseActionInMedicineManagement();
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void updateDrug() {
        Drug oldDrug = getExistedDrug();
        boolean is = true;
        int number = -1;
        do {
            showDrugDetail(oldDrug);
            showChangeStatus(number);
            System.out.println("Choose what information you want to update.");
            System.out.println("NOTE: You CANNOT update drug ID. Please enter a value in range '2-12'!\n");
            System.out.println("---> Enter '13' to CONFIRM that you agree to update your account with below information.\n");
            System.out.println("---> Enter '0' to cancel updating.");
            System.out.println("---> NOTE: You can enter '" + Exit.E4 + "' to cancel updating at any step (2-12).\n");
            try {
                number = Menu.chooseActionByNumber();
                switch (number) {
                    case 2:
                        is = enterDrugName(oldDrug);
                        break;
                    case 3:
                        is = enterDrugContent(oldDrug);
                        break;
                    case 4:
                        is = enterQuantity(oldDrug);
                        break;
                    case 5:
                        is = enterDosageForm(oldDrug);
                        break;
                    case 6:
                        is = enterUsage(oldDrug);
                        break;
                    case 7:
                        is = enterDosagePerDay(oldDrug);
                        break;
                    case 8:
                        is = enterTotalDosage5Days(oldDrug);
                        break;
                    case 9:
                        is = enterPricePerPill(oldDrug);
                        break;
                    case 10:
                        is = enterProductionDate(oldDrug);
                        break;
                    case 11:
                        is = enterExpirationDate(oldDrug);
                        break;
                    case 12:
                        is = enterNote(oldDrug);
                        break;
                    case 13:
                        is = true;
                        medicineService.update(oldDrug);
                        System.out.println("\n-----> Drug has been updated successfully!\n");
                        chooseActionInMedicineManagement();
                        break;
                    case 0:
                        is = true;
                        chooseActionInMedicineManagement();
                        break;
                    default:
                        Menu.alert();
                        is = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.alert();
            }
        } while (!is);
    }

    private static void showChangeStatus(int number) {
        switch (number) {
            case 2 -> System.out.println("---  Drug name has been changed.\n");
            case 3 -> System.out.println("---  Drug content has been changed.\n");
            case 4 -> System.out.println("---  Quantity has been changed.\n");
            case 5 -> System.out.println("---  Dosage form has been changed.\n");
            case 6 -> System.out.println("---  Usage has been changed.\n");
            case 7 -> System.out.println("---  Dosage per day has been changed.\n");
            case 8 -> System.out.println("---  Total dosage in 5 days has been changed.\n");
            case 9 -> System.out.println("---  Price has been changed.\n");
            case 10 -> System.out.println("---  Production date has been changed.\n");
            case 11 -> System.out.println("---  Expiration date has been changed.\n");
            case 12 -> System.out.println("---  Note has been changed.\n");
        }
    }

    public static void addNewDrug() {
        System.out.println("\n\n----- Add new drug -----\n");
        System.out.println("---> NOTE: You can enter '" + Exit.E4 + "' to cancel operating at any step (2-12).\n");
        Drug newDrug = new Drug();
        try {
            setID(newDrug);
            do {
                enterDrugName(newDrug);
                enterDrugContent(newDrug);
            } while (!checkDuplicateDrug(newDrug));
            enterQuantity(newDrug);
            enterDosageForm(newDrug);
            enterUsage(newDrug);
            enterDosagePerDay(newDrug);
            enterTotalDosage5Days(newDrug);
            enterPricePerPill(newDrug);
            enterProductionDate(newDrug);
            enterExpirationDate(newDrug);
            enterNote(newDrug);
            confirmAddingNewDrug(newDrug);
        } catch (Exception ex) {
            Menu.alert();
        }
    }
}
