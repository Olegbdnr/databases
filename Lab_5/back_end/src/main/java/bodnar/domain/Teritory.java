package bodnar.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Teritory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "teritory")
    private List<Employee> employees;
    @OneToMany(mappedBy = "teritory")
    private List<PatrolBot> patrolBots;
    @OneToMany(mappedBy = "teritory")
    private List<Route> routes;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teritory teritory = (Teritory) o;
        return Objects.equals(id, teritory.id) && Objects.equals(phone, teritory.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<PatrolBot> getPatrolBots() {
        return patrolBots;
    }

    public void setPatrolBots(List<PatrolBot> patrolBots) {
        this.patrolBots = patrolBots;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
