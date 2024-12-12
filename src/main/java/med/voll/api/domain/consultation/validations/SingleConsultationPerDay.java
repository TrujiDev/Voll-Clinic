package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.ReservationData;
import org.springframework.stereotype.Component;

@Component
public class SingleConsultationPerDay implements InquiriesValidator {

  private ConsultationRepository consultationRepository;

  public void validate(ReservationData data) {
    var firstConsultation = data.date().withHour(7);
    var lastConsultation = data.date().withHour(18);
    var patientConsultations = consultationRepository.existsByPatientIdAndDateBetween(data.patientId(), firstConsultation, lastConsultation);

    if (patientConsultations) {
      throw new RuntimeException("Patient already has a consultation scheduled for this day");
    }
  }

}
