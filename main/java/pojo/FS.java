package pojo;

public class FS {
    private String id;
    private String faculty;
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FS{" +
                "faculty='" + faculty + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
