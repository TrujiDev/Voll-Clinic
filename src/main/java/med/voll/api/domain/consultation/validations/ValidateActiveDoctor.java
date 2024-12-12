package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.consultation.ReservationData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidateActiveDoctor implements InquiriesValidator {

  private DoctorRepository doctorRepository;

  public void validate(ReservationData data) {
    if (data.doctorId() == null) {
      return;
    }

    var activeDoctor = doctorRepository.findIsActiveById(data.doctorId());
    if (!activeDoctor) {
      throw new RuntimeException("Consultation cannot be scheduled with inactive doctor");
    }
  }

}
