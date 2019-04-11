import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class courseWorkMain {
    static Scanner sc = new Scanner(System.in);

    //keep data for all the registered patients
    static Map<Integer, Person> patients = new HashMap<>();

    //created to store all the patients with a list of all the doctors they have seen already
    static Map<Integer, List<Person>> patientsList = new HashMap<>();

    //keep data for all the doctors
    static Map<Integer, Person> doctors = new HashMap<>();

    //store all the doctors and their current patients
    static Map<Integer, List<Person>> doctorsList = new HashMap<>();

    //keep track of daily appointments
    static Map<Integer, Appointment> appointments = new HashMap<>();

    //Patients personal ID
    static int autoGenID = 1010101;
    //appointment ID
    static int appID = 20201;

    //Dob format date
    static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    public static void main(String[] args) throws ParseException {

        /* list of doctors for testing purposes */

        Doctor george = new Doctor("George", "Georgev", "Marin drinov 8 street", "085465", "georg.com", format.parse("08.11.1985"));
        doctors.put(1, george);

        Doctor ivan = new Doctor("Ivan", "Ivanov", "8 street", "085465", "ivan.com", format.parse("12.10.1986"));
        doctors.put(2, ivan);

        Doctor piotr = new Doctor("Piotr", "Piotrov", "6 piotrov road", "074685", "piotr.com", format.parse("18.04.2019"));
        doctors.put(3, piotr);

        Doctor drago = new Doctor("Drago", "Dragov", "4 dragoev road", "0745855", "drago.com", format.parse("14.05.1990"));
        doctors.put(4, drago);

        Doctor john = new Doctor("John", "Mensa", "4 manshion", "0755245224", "drago.com", format.parse("02.09.1980"));
        doctors.put(5, john);

        //list of patients for testing purposes

        Person misho = new Patient("Misho", "Atanasov", "3 jubilee SS4 5PP", "5465464", "misho.com", format.parse("22.10.1999"), "1010102", "yearly");
        patients.put(1010102, misho);

        Person gosho = new Patient("Gosho", "Petrov", "35 woodcote SP7 8PS", "0788800880", "gosho.com", format.parse("12.01.1991"), "1010103", "monthly");
        patients.put(1010103, gosho);

        Person pesho = new Patient("Pesho", "Peshev", "5 cedar road FK6 9KJ", "082255446688", "pesho.com", format.parse("15.03.1981"), "1010104", "yearly");
        patients.put(1010104, pesho);

        Person milen = new Patient("Milen", "Milenov", "3 ashley road SM8 0HG", "0882225444665", "milen.com", format.parse("08.06.1986"), "1010105", "yearly");
        patients.put(1010105, milen);

        Person zheni = new Patient("Zheni", "Zheneva", "45 straiten road SE2 2GH", "07788544455521", "zheni.com", format.parse("23.01.1991"), "1010106", "monthly");
        patients.put(1010106, zheni);
        //Program starts with prompting the menu from my menu method

        menu();
    }
    /* Menu List */
    public static void menu() throws ParseException {
        System.out.println("********************");
        System.out.println("Armstrong GP Surgery");
        System.out.println("********************");
        System.out.printf("1. Register a patient.%n" +
                "2. Book appointment.%n" +
                "3. Cancel Appointment.%n" +
                "4. Edit Appointment.%n" +
                "5. Remove patient from GP list.%n" +
                "6. Search for appointments.%n" +
                "7. View Patient Profile.%n" +
                "8. Mgmt Reporting.%n" +
                "9. Exit.%n" +

                "Please select an option: ");
        //using switch instead of if else for readability
        //where I invoke every method I need

        String option = sc.nextLine();
        switch (option) {
            case "1":
                regPatient();
                break;
            case "2":
                BookAppointment();
                break;
            case "3":
                CancelAppointment();
                break;
            case "4":
                EditAppointment();
                break;
            case "5":
                RmvPatient();
                break;
            case "6":
                SearchForAPP();
                break;
            case "7":
                ViewPatientProfile();
                break;
            case "8":
                MgmtReporting();
                break;
            case "9":
                System.exit(0);
                break;
        }
        menu();
    }
    // all the methods starting from the last one in the Menu List

    // Management Reporting

    private static void MgmtReporting() throws ParseException {
        System.out.println("To see list of patients seen by a specific doctor please press 1: ");
        System.out.println("To see list of doctors seen by a specific patient please press 2: ");
        String option = sc.nextLine();
        if (option.equals("1")) {
            System.out.println("Please enter doctor's ID: ");
            int id = Integer.parseInt(sc.nextLine());
            if (doctorsList.containsKey(id)) {
                List list = doctorsList.get(id);

                for (int i = 0; i < list.size(); i++) {
                    System.out.print("-" + ((Patient) list.get(i)).getFirstName());
                    System.out.println(" " + ((Patient) list.get(i)).getLastName());
                }
            }
        } else if (option.equals("2")) {
            System.out.println("Please enter a appointments ID: ");
            int id = Integer.parseInt(sc.nextLine());
            LocalDateTime dateOfApp = appointments.get(id).getDateOfAppointment();
            System.out.println("Please enter patient's ID");
            int idP = Integer.parseInt(sc.nextLine());
            List listP = patientsList.get(idP);
            for (int j = 0; j < listP.size(); j++) {
                System.out.print("-" + ((Doctor) listP.get(j)).getFirstName());
                System.out.print(" " + ((Doctor) listP.get(j)).getLastName());
                System.out.println(" - " + dateOfApp);
            }
        }
        System.out.println("To see another list press 1: " +
                "To return to the main menu press 2: ");
        option = sc.nextLine();
        if (option.equals("1")) {
            MgmtReporting();
        } else if (option.equals("2")) {
            menu();
        } else {
            System.out.println("Please enter one of the options: " + option);
        }

    }
        //View a Patient profile

    private static void ViewPatientProfile() throws ParseException {
        System.out.println("Please enter a patient's ID: ");
        int id = Integer.parseInt(sc.nextLine());
        if (patients.containsKey(id)) {
            System.out.print("Patient's name: " + patients.get(id).getFirstName());
            System.out.println(" " + patients.get(id).getLastName());
            System.out.println("Dob: " + patients.get(id).getDob());
            System.out.println("Address and post code: " + patients.get(id).getAddress());
            System.out.println("ID: " + id);
            System.out.println("Type ot paying: " + ((Patient) patients.get(id)).getYearly());

        }
        System.out.println("To check another patient, please press 1, to return to the main menu press 2! ");
        String option = sc.nextLine();
        if (option.equals("1")) {
            ViewPatientProfile();
        } else if (option.equals("2")) {
            menu();
        }
        System.out.println("Please enter one of the options: ");
        sc.nextLine();
    }

    //searching fo existing appointments

    private static void SearchForAPP() throws ParseException {
        for (var entry : appointments.entrySet()) {
            System.out.println("Please enter appointment ID or press 1 to return to the main menu: ");
            int appID = Integer.parseInt(sc.nextLine());
            if (appID == 1) {
                menu();
            }
            if (appointments.containsKey(appID)) {
                System.out.print("Patient's name: " + entry.getValue().getPatient().getFirstName());
                System.out.println(" " + entry.getValue().getPatient().getLastName());
                System.out.print("Doctor's name: " + entry.getValue().getDoctor().getFirstName());
                System.out.println(" " + entry.getValue().getDoctor().getLastName());
                System.out.println("Date of the appointment: " + entry.getValue().getDateOfAppointment());
            } else {
                System.out.println("This appointment ID does not exist. Please enter different ID or press 1 to return" + appID);
                if (appID == 1) {
                    SearchForAPP();
                }
            }
        }
        System.out.println("To check another appointment press 1 or press 2 to return to the main menu:");
        String option = sc.nextLine();
        if (option.equals("1")) {
            SearchForAPP();
        } else if (option.equals("2")) {
            menu();
        } else {
            System.out.println("Please select one of the options" + option);
        }
    }
    //Removing a patient

    private static void RmvPatient() throws ParseException {
        for (var entry : patients.entrySet()) {
            System.out.printf("Patient ID: " + entry.getKey() + ",%n" +
                    "Patient name: " + entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
            System.out.println();
        }
        System.out.println("Choose the patient ID you want to remove: ");
        int delID = Integer.parseInt(sc.nextLine());
        System.out.printf("You are about to remove a patient: " + delID + "%nPlease press 1 to confirm, 2 to return or 3 to return to the main menu:%n");
        int option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                if (patients.containsKey(delID)) {
                    patients.remove(delID);
                    System.out.println("Patient removed!");
                }
                break;
            case 2:
                RmvPatient();
                break;
            case 3:
                menu();
                break;
        }
        System.out.println("To remove another patient press 2 or 3 to return to the main menu: ");
        option = Integer.parseInt(sc.nextLine());
        if (option == 2) {
            RmvPatient();
        } else if (option == 3) {
            menu();
        } else {
            System.out.println("Please choose one of the options: " + option);
        }
    }
    //Edin appointment

    private static void EditAppointment() throws ParseException {
        for (var entry : appointments.entrySet()) {
            System.out.println("Appointment ID " + entry.getKey() + ",");
            System.out.print("Doctor's name: " + entry.getValue().getDoctor().getFirstName());
            System.out.println(" " + entry.getValue().getDoctor().getLastName());
            System.out.print("Patient's name: " + entry.getValue().getPatient().getFirstName());
            System.out.println(" " + entry.getValue().getPatient().getLastName());
            System.out.println("Date and hour of the appointment: " + entry.getValue().getDateOfAppointment());
            System.out.println();
        }
        String choice;
        System.out.println("Choose appointment by his ID: ");
        choice = sc.nextLine();
        int tempID = Integer.parseInt(choice);
        System.out.printf("To change the doctor press 1%n" +
                "To change the hour of the appointment press 2 (note that you can change only the hour ot the current day).");
        String option = sc.nextLine();
        if (option.equals("1")) {
            System.out.printf("Please choose a doctor's number: %n");
            System.out.println("1.George Georgev\n" +
                    "2.Ivan Ivanov\n" +
                    "3.Piotr Piotrov\n" +
                    "4.Drago Dragov\n" +
                    "5.John Mensa\n");
            int docN = Integer.parseInt(sc.nextLine());

            if (!doctors.containsKey(docN)) {
                System.out.println("Doctor does not exist!");
                EditAppointment();
            } else {
                appointments.get(tempID).setDoctor(doctors.get(docN));
            }
        } else if (option.equals("2")) {
            LocalDateTime newDate = dateOfAppointment();
            appointments.get(tempID).setDateOfAppointment(newDate);
        }
        System.out.println("To edit another appointment press 3 or press 4 to return to the main menu!");
        String optionS = sc.nextLine();
        if (optionS.equals("3")) {
            EditAppointment();
        } else if (optionS.equals("4")) {
            menu();
        } else {
            System.out.println("Please select one of the options 1 - 4." + optionS);
        }
    }
    //Cancel appointment
    private static void CancelAppointment() throws ParseException {
        System.out.println("Please enter a appointment ID number." +
                "Press 1 to return to the main menu: ");
        int tempID = Integer.parseInt(sc.nextLine());
        if (tempID == 1) {
            menu();
        }
        for (Map.Entry<Integer, Appointment> entry : appointments.entrySet()) {
            if (entry.getKey() == tempID) {
                System.out.println("You are about to delete an appointment, please confirm with Y or to return with N: ");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    appointments.remove(tempID);
                    System.out.println("You have successfully remove the appointment!");
                } else {
                    CancelAppointment();
                }
            } else {
                System.out.println("The appointment ID does not exist!");
                CancelAppointment();
            }
            System.out.println("To return to the main menu enter 1, to cancel another appointment enter 2");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                menu();
            } else if (choice.equals("2")) {
                CancelAppointment();
            }
        }
    }
    //Book an appointment

    private static void BookAppointment() throws ParseException {
        System.out.println("Please choose a doctor: ");
        System.out.println("1.George Georgev\n" +
                "2.Ivan Ivanov\n" +
                "3.Piotr Piotrov\n" +
                "4.Drago Dragov\n" +
                "5.John Mensa\n");
        int docNum = Integer.parseInt(sc.nextLine());
        if (!doctors.containsKey(docNum)) {
            System.out.println("Wrong ID! ");
            BookAppointment();
        }
        System.out.println("Please enter a patient ID number: ");
        int idPatient = Integer.parseInt(sc.nextLine());
        if (patients.containsKey(idPatient)) {
            if (!doctorsList.containsKey(docNum)) {
                doctorsList.put(docNum, new ArrayList<>());
            }
            doctorsList.get(docNum).add(patients.get(idPatient));
            if (!patientsList.containsKey(idPatient)) {
                patientsList.put(idPatient, new ArrayList<>());
            }
            patientsList.get(idPatient).add(doctors.get(docNum));
        } else {
            System.out.println("Wrong ID, Please enter a valid ID number or press 2 to return to the main menu: ");
            if (idPatient == 2) {
                menu();
            } else {
                idPatient = Integer.parseInt(sc.nextLine());
            }
        }
        LocalDateTime dateOfAppointment = dateOfAppointment();
        Appointment appointment = new Appointment(doctors.get(docNum), patients.get(idPatient), dateOfAppointment, "20201");
        appointments.put(appID, appointment);
        appID++;
        System.out.printf("To make another appointment press 1.%n" +
                "To return to the main menu press 2.%n" +
                "To edit an appointment press 3. ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                BookAppointment();
                break;
            case "2":
                menu();
                break;
            case "3":
                EditAppointment();
                break;
            default:
                System.out.println("Please select option 1, 2 or 3. " + choice);
                break;
        }
    }
    // only for making appointments

    private static LocalDateTime dateOfAppointment() {
        LocalDateTime currentDate = LocalDateTime.now();
        int year = currentDate.getYear();
        Month month = currentDate.getMonth();
        int day = currentDate.getDayOfMonth();
        System.out.println("Please enter the hours of appointment(HH): ");
        int hour = Integer.parseInt(sc.nextLine());
        while (hour > 24 || hour <= -1) {
            System.out.println("Please enter the hours of appointment(HH): ");
            hour = Integer.parseInt(sc.nextLine());
        }
        System.out.println("Please enter the minutes(mm): ");
        int minutes = Integer.parseInt(sc.nextLine());
        while (minutes > 60 || minutes <= -1) {
            System.out.println("Please enter the minutes(mm): ");
            minutes = Integer.parseInt(sc.nextLine());
        }
        LocalDateTime time = LocalDateTime.of(year, month, day, hour, minutes);
        return time;
    }
    //Register a patient

    public static void regPatient() throws ParseException {
        System.out.println("Please enter first name: ");
        String firstName = sc.nextLine();
        System.out.println("Please enter surname: ");
        String lastName = sc.nextLine();
        System.out.println("Please enter full address(including postcode): ");
        String address = sc.nextLine();
        System.out.println("Please enter telephone number: ");
        String telNumber = sc.nextLine();
        System.out.println("Please enter email: ");
        String email = sc.nextLine();
        Date dob = getDob();
        System.out.println("You have two options for paying the fee. Option one is to pay for 1 year and get 10% discount or in monthly instalments.");
        System.out.println("Note that the monthly fee is 100¶ and the yearly fee is 1000¶ before the discount.");
        System.out.println("Please enter 1 for yearly payment and 2 for monthly payment.");
        String yearly = null;
        String bool = sc.nextLine();
        if (bool.equals("1")) {
            yearly = "yearly";
        } else if (bool.equals("2")) {
            yearly = "monthly";
        }

        Person patient = new Patient(firstName, lastName, address, telNumber, email, dob, String.valueOf(autoGenID), yearly);
        patients.put(autoGenID, patient);
        autoGenID++;
        System.out.println("To register another patient press 1 or 2 to return to the main menu!");
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            regPatient();
        } else if (choice.equals("2")) {
            menu();
        } else {
            System.out.println("Please select option 1 or 2. " + choice);
        }
    }

    //only when register a new patient

    public static Date getDob() {
        System.out.println("Please enter Date of Birth(dd.MM.yyyy): ");
        String dateOfB = sc.nextLine();
        format = new SimpleDateFormat("dd.MM.yyyy");
        Date formattedDate = null;
        try {
            formattedDate = format.parse(dateOfB);
        } catch (ParseException ex) {
            System.out.println("Please use the correct date format (dd.MM.yyyy): ");
            getDob();
        }
        return formattedDate;
    }
}
