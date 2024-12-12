package med.voll.api.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  @Query("""
      SELECT p.active
      FROM Patient p
      WHERE p.id = :patientId
      """)
  boolean findActiveById(Long patientId);
}
