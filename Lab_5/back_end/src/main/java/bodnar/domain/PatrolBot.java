package bodnar.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patrol_bot", schema = "bodnar", catalog = "")
public class PatrolBot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "complete_set_id", referencedColumnName = "id", nullable = false)
    private CompleteSet completeSet;
    @ManyToOne
    @JoinColumn(name = "teritory_id", referencedColumnName = "id", nullable = false)
    private Teritory teritory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatrolBot patrolBot = (PatrolBot) o;
        return Objects.equals(id, patrolBot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public CompleteSet getCompleteSet() {
        return completeSet;
    }

    public void setCompleteSet(CompleteSet completeSet) {
        this.completeSet = completeSet;
    }

    public Teritory getTeritory() {
        return teritory;
    }

    public void setTeritory(Teritory teritory) {
        this.teritory = teritory;
    }
}
