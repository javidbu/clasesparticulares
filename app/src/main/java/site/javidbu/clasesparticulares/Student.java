package site.javidbu.clasesparticulares;

/**
 * Created by Javi on 24/04/2016.
 * This class represent a student.
 */
public class Student {
    // Student attributes:
    private long id, subject_id, price, phone;
    private String name, email, address, comments;

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public long getPrice() {
        return price;
    }

    public long getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getComments() {
        return comments;
    }

    // Used in the ArrayAdapter in the ListView
    // TODO Ver si esto cuadra con varios atributos de alguna manera...
    @Override
    public String toString() {
        return name;
    }
}
