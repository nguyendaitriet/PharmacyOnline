package vn.triet.pharmacy.online.views.admin.management;

import vn.triet.pharmacy.online.models.Drug;
import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.services.IMedicineService;
import vn.triet.pharmacy.online.services.MedicineService;
import vn.triet.pharmacy.online.utils.ValidateUtils;
import vn.triet.pharmacy.online.views.AdminView;
import vn.triet.pharmacy.online.views.Menu;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class MedicineManagement {
    private static IMedicineService medicineService = new MedicineService();

    private static final Scanner input = new Scanner(System.in);

    private static void showActionForm() {
        System.out.println("\n --------------- Medicine Management -----------------");
        System.out.println("|                                                     |");
        System.out.println("|        1. Show drugs list.                          |");
        System.out.println("|             2. Sort by quantity.                    |");
        System.out.println("|             3. Sort by price per pill.              |");
        System.out.println("|             4. Sort by production date.             |");
        System.out.println("|             5. Show a drug in detail (by ID).       |");
        System.out.println("|        6. Add new drug.                             |");
//        System.out.println("|        3. Edit drug's information.                  |");
//        System.out.println("|        4. Remove drug.                              |");
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

                    break;
                }
                if (number == 3) {

                    break;
                }
                if (number == 4) {
//                    System.out.println("------ Sorry, this action is not available now. Please choose another!");
//                    chooseActionInMedicineManagement();
                    removeDrug();
                    break;
                }
                if (number == 5) {
                    updateDrug();
                    break;
                }
                if (number == 6) {
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

    public static void showDrugsList(List<Drug> drugs, int action) {
        System.out.println("\nDRUGS LIST -------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-23s %-20s %-20s %-20s\n",
                "ID", "Drug Name", "Drug Content (mg)", "Quantity (pill)", "Price per Pill", "Expiration Date");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        switch (action) {
            case 1:
                showAllDrugs(drugs);
                break;
            case 2:
//                sortByNameASCE(drugs);
                break;
            case 3:
//                sortByCreationDateASCE(drugs);
                break;
            case 4:
//                filterByAdmin(drugs);
                break;
            case 5:
//                filterByUser(drugs);
                break;
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------\n");
        showReturningChoice();
    }

    public static void showAllDrugs(List<Drug> drugs) {
        for (Drug drug : drugs) showOneDrug(drug);
    }

    public static void showOneDrug(Drug drug) {
        System.out.printf("%-12s %-25s %-23s %-20s %-20s %-20s\n", drug.getId(), drug.getDrugName(), drug.getDrugContent(), drug.getQuantity(), drug.getPricePerPill(), drug.getExpirationDate());
    }

    private static void showReturningChoice() {
        do {
            System.out.print("Enter '0' to return: ");
            try {
                Scanner input = new Scanner(System.in);
                int number = input.nextInt();
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

    public static void addNewDrug() {
        System.out.println("\n\n----- Add new drug -----\n");
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
            ex.printStackTrace();
            Menu.alert();
            Menu.showExceptionAction();
        }
    }

    public static void removeDrug() {
        do {
            try {
                System.out.print("\nEnter drug ID you want to REMOVE: ");
                int id = Integer.parseInt(input.nextLine());
                if (medicineService.isIdExisted(id)) {
                    Drug drug = medicineService.getDrugById(id);
                    confirmRemovingDrug(drug);
                    break;
                }
                if (id == 0) {
                    chooseActionInMedicineManagement();
                    break;
                }
                System.out.println("\nWrong ID, please try again or enter '0' to return.");
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    public static void confirmRemovingDrug(Drug drug) {
        do {
            System.out.printf("\nConfirm that you want to remove drug '%s'.\n", drug.getId());
            System.out.println("1. Agree to remove.");
            System.out.println("2. Cancel.");
            try {
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    System.out.printf("\nDrug '%s' has been removed successfully!", drug.getId());
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

    public static void setID(Drug newDrug) {
        int min = 100001;
        int max = 999999;
        int id;
        do {
            id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        } while (medicineService.isIdExisted(id));
        newDrug.setId(id);
        System.out.println("1. Drug ID: " + id);
    }

    private static boolean cancelEntering(String string) {
        if (string.equals("exit-04")) {
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

    private static void enterDrugContent(Drug newDrug) {
        do {
            try {
                System.out.println("3. Enter Drug Content (mg). ");
                System.out.print("==> ");
                double drugContent = Double.parseDouble(input.nextLine());
                System.out.println();
                if (drugContent <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setDrugContent(drugContent);
                break;
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

    private static void enterQuantity(Drug newDrug) {
        do {
            try {
                System.out.println("4. Enter Quantity (pill). ");
                System.out.print("==> ");
                int quantity = Integer.parseInt(input.nextLine());
                System.out.println();
                if (quantity <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setQuantity(quantity);
                break;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void enterDosageForm(Drug newDrug) {
        do {
            try {
                System.out.println("5. Enter dosage form (Choose '1' or '2').");
                System.out.printf("%-10s %-15s", " ", "1. Tablet.\n");
                System.out.printf("%-10s %-15s", " ", "2. Capsule.\n");
                int number = Menu.chooseActionByNumber();
                if (number == 1) {
                    newDrug.setDosageForm("tablet");
                    break;
                }
                if (number == 2) {
                    newDrug.setDosageForm("capsule");
                    break;
                }
                Menu.alert();
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void enterUsage(Drug newDrug) {
        System.out.println("6. Enter common usage (Example: fever, cough).");
        System.out.print("==> ");
        String usage = input.nextLine().trim();
        newDrug.setUsage(usage);
    }

    private static void enterDosagePerDay(Drug newDrug) {
        System.out.println("7. Enter dosage per day (Example: 1 Morning, 1 Night).");
        System.out.print("==> ");
        String dosagePerDay = input.nextLine().trim();
        newDrug.setDosagePerDay(dosagePerDay);
    }

    private static void enterTotalDosage5Days(Drug newDrug) {
        do {
            try {
                System.out.println("8. Enter total dosage in 5 days.");
                System.out.print("==> ");
                int totalDosage5Days = Integer.parseInt(input.nextLine());
                if (totalDosage5Days <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setTotalDosage5Days(totalDosage5Days);
                break;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void enterPricePerPill(Drug newDrug) {
        do {
            try {
                System.out.println("9. Enter price per pill.");
                System.out.print("==> ");
                double pricePerPill = Double.parseDouble(input.nextLine());
                if (pricePerPill <= 0) {
                    System.out.println("You can't enter a negative value, please try again!\n");
                    continue;
                }
                newDrug.setPricePerPill(pricePerPill);
                break;
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
    }

    private static void enterProductionDate(Drug newDrug) {
        do {
            System.out.println("10. Enter Production Date (Example: 12/04/2021) ");
            System.out.print("==> ");
            String productionDate = input.nextLine().trim();
            System.out.println();
            if (ValidateUtils.isDateValid(productionDate)) {
                newDrug.setProductionDate(productionDate);
                break;
            }
            System.out.println("Invalid date format, please try again!\n");
        } while (true);
    }


    private static void enterExpirationDate(Drug newDrug) throws ParseException {
        do {
            System.out.println("11. Enter Expiration Date (Example: 12/04/2022) ");
            System.out.print("==> ");
            String expirationDate = input.nextLine().trim();
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
            break;
        } while (true);
    }

    private static void enterNote(Drug newDrug) {
        System.out.println("12. Enter note (Example: antibiotic).");
        System.out.print("==> ");
        String note = input.nextLine();
        newDrug.setNote(note);
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
        System.out.println("\nDRUG DETAIL------------------------------\n");
        System.out.printf("%-30s %-12d\n", "1. ID:", drug.getId());
        System.out.printf("%-30s %-12s\n", "2. Generic Name:", drug.getDrugName());
        System.out.printf("%-30s %-12s\n", "3. Drug Content (mg):", drug.getDrugContent());
        System.out.printf("%-30s %-12s\n", "4. Quantity (pill):", drug.getQuantity());
        System.out.printf("%-30s %-12s\n", "5. Dosage Form", drug.getDosageForm());
        System.out.printf("%-30s %-12s\n", "6. Usage:", drug.getUsage());
        System.out.printf("%-30s %-12s\n", "7. Dosage per Day:", drug.getDosagePerDay());
        System.out.printf("%-30s %-12s\n", "8. Total dosage in 5 days:", drug.getTotalDosage5Days());
        System.out.printf("%-30s %-12s\n", "9. Price per Pill:", drug.getPricePerPill());
        System.out.printf("%-30s %-12s\n", "10. Production Date:", drug.getProductionDate());
        System.out.printf("%-30s %-12s\n", "11. Expiration Date:", drug.getExpirationDate());
        System.out.printf("%-30s %-12s\n", "12. Note:", drug.getNote());
        System.out.println("\n-----------------------------------------\n");
    }

    public static Drug getExistedDrug() {
        Drug drug = null;
        do {
            try {
                System.out.print("\nEnter Drug ID you want to UPDATE: ");
                int id = Integer.parseInt(input.nextLine());
                if (medicineService.isIdExisted(id)) {
                    drug = medicineService.getDrugById(id);
                    break;
                }
                if (id == 0) {
                    chooseActionInMedicineManagement();
                    break;
                }
                System.out.println("\nWrong ID, please try again or enter '0' to return.");
            } catch (Exception ex) {
                Menu.alert();
            }
        } while (true);
        return drug;
    }

    public static void updateDrug() {
        Drug oldDrug = getExistedDrug();
        boolean is = true;
        int number = -1;
        do {
            showDrugDetail(oldDrug);
            showChangeStatus(number);
            System.out.println("Choose what information you want to update.");
            System.out.println("NOTE: You CANNOT update your account ID. Please don't enter '1'!\n");
            System.out.println("---> Enter '8' to update password.");
            System.out.println("---> Enter '9' to CONFIRM that you agree to update your account with below information.\n");
            System.out.println("---> Enter '0' to cancel updating.");
            System.out.println("---> NOTE: You can enter 'exit-04' to cancel updating at any step (2-12).\n");
            try {
                number = Menu.chooseActionByNumber();
                switch (number) {
                    case 2:
                        is = newRegister.enterFullName(currentUser);
                        break;
                    case 3:
                        is = newRegister.enterBirthday(currentUser);
                        break;
                    case 4:
                        is = newRegister.enterPhoneNumber(currentUser);
                        break;
                    case 5:
                        is = newRegister.enterAddress(currentUser);
                        break;
                    case 6:
                        is = newRegister.enterEmail(currentUser);
                        break;
                    case 7:
                        is = newRegister.enterUserName(currentUser);
                        break;
                    case 8:
                        updatePassword(currentUser);
                        is = true;
                        break;
                    case 9:
                        userService.update(currentUser);
                        is = true;
                        break;
                    case 0:
                        is = true;
                        break;
                    default:
                        Menu.alert();
                        is = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Menu.showExceptionAction();
            }
        } while (!is);
    }

    private static void showChangeStatus(int number) {
        switch (number) {
            case 2 -> System.out.println("--- Your full name has been changed.\n");
            case 3 -> System.out.println("--- Your date of birth has been changed.\n");
            case 4 -> System.out.println("--- Your phone number has been changed.\n");
            case 5 -> System.out.println("--- Your address has been changed.\n");
            case 6 -> System.out.println("--- Your email has been changed.\n");
            case 7 -> System.out.println("--- Your username has been changed.\n");
            case 8 -> System.out.println("--- Your username has been changed.\n");
            case 9 -> System.out.println("--- Your username has been changed.\n");
            case 10 -> System.out.println("--- Your username has been changed.\n");
            case 11 -> System.out.println("--- Your username has been changed.\n");
            case 12 -> System.out.println("--- Your username has been changed.\n");
        }
    }
}
