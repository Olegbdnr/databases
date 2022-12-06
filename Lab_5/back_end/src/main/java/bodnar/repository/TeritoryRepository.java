package bodnar.repository;

import bodnar.domain.Teritory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeritoryRepository extends JpaRepository<Teritory, Integer> {
}
