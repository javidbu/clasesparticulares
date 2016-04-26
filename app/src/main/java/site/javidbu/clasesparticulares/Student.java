package site.javidbu.clasesparticulares;

/**
 * Created by Javi on 24/04/2016.
 * This class represent a student.
 */
public class Student {
    // Student attributes:
    private long id, subject_id, phone, classes;
    private float price, debt;
    private String name, email, address, comments;

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
    }

    public void setPrice(float price) {
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

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public void setClasses(long classes) {
        this.classes = classes;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public float getPrice() {
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

    public long getClasses() {
        return classes;
    }

    public float getDebt() {
        return debt;
    }
}
