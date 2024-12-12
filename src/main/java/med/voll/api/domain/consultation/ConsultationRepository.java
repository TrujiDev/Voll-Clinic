package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
  boolean existsByPatientIdAndDateBetween(@NotNull Long patientId, LocalDateTime firstConsultation, LocalDateTime lastConsultation);

  boolean existsByDoctorIdAndDate(Long doctorId, @NotNull @Future LocalDateTime date);
}
