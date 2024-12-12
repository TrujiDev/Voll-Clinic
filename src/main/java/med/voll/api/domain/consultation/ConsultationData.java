package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record ConsultationData(
    Long id,
    Long doctorId,
    Long patientId,
    LocalDateTime date
) {
  public ConsultationData(Consultation consultation) {
    this(consultation.getId(), consultation.getDoctor().getId(), consultation.getPatient().getId(), consultation.getDate());
  }
}
