package bodnar.repository;


import bodnar.domain.CompleteSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteSetRepository extends JpaRepository<CompleteSet, Integer> {
}
