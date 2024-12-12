package med.voll.api.domain.consultation.validations.reservation;

import med.voll.api.domain.consultation.ReservationData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ThirtyMinuteAppointmentRule implements InquiriesValidator {

  public void validate(ReservationData data) {
    var consultationDate = data.date();
    var currentDate = LocalDateTime.now();
    var difference = Duration.between(currentDate, consultationDate).toMinutes();

    if (difference < 30) {
      throw new RuntimeException("Consultation must be scheduled at least 30 minutes in advance");
    }
  }

}
