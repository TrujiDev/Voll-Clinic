package med.voll.api.domain.consultation.validations.cancellation;

import med.voll.api.domain.consultation.CancelReservationData;
import med.voll.api.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceScheduleValidator implements InquiriesCancellation{

  @Autowired
  private ConsultationRepository consultationRepository;

    @Override
    public void validate(CancelReservationData data) {
      var consultation = consultationRepository.getReferenceById(data.consultationId());
      var currentDate = LocalDateTime.now();
      var differenceInHours = Duration.between(currentDate, consultation.getDate()).toHours();

      if (differenceInHours < 24) {
        throw new RuntimeException("You can only cancel a reservation 24 hours before the consultation");
      }
    }

}
