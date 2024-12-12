package med.voll.api.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  Page<Doctor> findByIsActiveTrue(Pageable pagination);

  @Query("""
      SELECT d
      FROM Doctor d
      WHERE d.isActive = true
      AND d.specialty = :specialty
      AND d.id NOT IN (
        SELECT c.doctor.id
        FROM Consultation c
        WHERE c.date = :date
      )
      ORDER BY rand()
      LIMIT 1
      """)
  Doctor chooseRandomDoctor(Specialty specialty, @NotNull @Future LocalDateTime date);

  boolean findIsActiveById(Long doctorId);
}
