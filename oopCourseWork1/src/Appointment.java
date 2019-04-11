import java.time.LocalDateTime;

public class Appointment {
    private Person doctor, patient;
    private LocalDateTime dateOfAppointment;
    private String appID;

    public Appointment(Person doctor, Person patient, LocalDateTime dateOfAppointment, String appID) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateOfAppointment = dateOfAppointment;
        this.appID = appID;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDateTime dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
