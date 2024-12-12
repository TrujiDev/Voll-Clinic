package med.voll.api.domain.consultation.validations.reservation;

import med.voll.api.domain.consultation.ReservationData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ScheduleValidation implements  InquiriesValidator {

  public void validate(ReservationData data) {
    var consultationDate = data.date();
    var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    var openingHour = consultationDate.getHour() < 7;
    var closingHour = consultationDate.getHour() > 18;

    if (sunday || openingHour || closingHour) {
      throw new RuntimeException("Time selected after working hours");
    }
  }

}
