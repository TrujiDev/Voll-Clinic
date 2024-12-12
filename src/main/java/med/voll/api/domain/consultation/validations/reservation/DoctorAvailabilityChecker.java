package med.voll.api.domain.consultation.validations.reservation;

import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorAvailabilityChecker implements InquiriesValidator {

  @Autowired
  private ConsultationRepository consultationRepository;

  public void validate(ReservationData data) {
    var doctorOccupied = consultationRepository.existsByDoctorIdAndDateAndCancelReasonIsNull(data.doctorId(), data.date());

    if (doctorOccupied) {
      throw new RuntimeException("Doctor is not available at the specified date");
    }
  }

}
