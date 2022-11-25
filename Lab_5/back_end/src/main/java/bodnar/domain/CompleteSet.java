package bodnar.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "complete_set", schema = "bodnar", catalog = "")
public class CompleteSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "battery_reserve")
    private BigDecimal batteryReserve;
    @Basic
    @Column(name = "average_speed")
    private Integer averageSpeed;
    @Basic
    @Column(name = "width")
    private BigDecimal width;
    @Basic
    @Column(name = "length")
    private BigDecimal length;
    @Basic
    @Column(name = "height")
    private BigDecimal height;
    @OneToMany(mappedBy = "completeSet")
    private List<Camera> cameras;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
    @OneToMany(mappedBy = "completeSet")
    private List<PatrolBot> patrolBots;

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

    public BigDecimal getBatteryReserve() {
        return batteryReserve;
    }

    public void setBatteryReserve(BigDecimal batteryReserve) {
        this.batteryReserve = batteryReserve;
    }

    public Integer getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Integer averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompleteSet that = (CompleteSet) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(batteryReserve, that.batteryReserve) && Objects.equals(averageSpeed, that.averageSpeed) && Objects.equals(width, that.width) && Objects.equals(length, that.length) && Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, batteryReserve, averageSpeed, width, length, height);
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<PatrolBot> getPatrolBots() {
        return patrolBots;
    }

    public void setPatrolBots(List<PatrolBot> patrolBots) {
        this.patrolBots = patrolBots;
    }
}
