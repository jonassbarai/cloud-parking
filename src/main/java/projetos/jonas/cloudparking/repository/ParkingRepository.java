package projetos.jonas.cloudparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetos.jonas.cloudparking.model.Parking;
@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
}
