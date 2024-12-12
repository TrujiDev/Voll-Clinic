package med.voll.api.domain.consultation.validations.cancellation;

import med.voll.api.domain.consultation.CancelReservationData;

public interface InquiriesCancellation {
  void validate(CancelReservationData data);
}
