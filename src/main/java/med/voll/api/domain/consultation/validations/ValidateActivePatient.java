package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.consultation.ReservationData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements InquiriesValidator {

  private PatientRepository patientRepository;

  public  void validate(ReservationData data) {
    var activePatient = patientRepository.findActiveById(data.patientId());

    if (!activePatient) {
      throw new RuntimeException("Consultation cannot be scheduled for inactive patient");
    }
  }

}
