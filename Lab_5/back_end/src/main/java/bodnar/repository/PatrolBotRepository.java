package bodnar.repository;

import bodnar.domain.PatrolBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrolBotRepository extends JpaRepository<PatrolBot, Integer> {
}
