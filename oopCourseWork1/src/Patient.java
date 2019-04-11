import java.util.Date;

public class Patient extends Person {
    private String id;
    public String yearly;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearly() {
        return yearly;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public Patient(String firstName, String lastName, String address, String telNumber, String email, Date dob, String id, String yearly) {
        super(firstName, lastName, address, telNumber, email, dob);
        this.id = id;
        this.yearly = yearly;

    }
}
