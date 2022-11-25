package bodnar.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "length")
    private BigDecimal length;
    @ManyToOne
    @JoinColumn(name = "teritory_id", referencedColumnName = "id", nullable = false)
    private Teritory teritory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(name, route.name) && Objects.equals(length, route.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, length);
    }

    public Teritory getTeritory() {
        return teritory;
    }

    public void setTeritory(Teritory teritory) {
        this.teritory = teritory;
    }
}
