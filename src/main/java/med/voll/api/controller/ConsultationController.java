package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.CancelReservationData;
import med.voll.api.domain.consultation.ConsultationData;
import med.voll.api.domain.consultation.CreateInquiries;
import med.voll.api.domain.consultation.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiries")
public class ConsultationController {

  @Autowired
  private CreateInquiries inquiries;

  @PostMapping
  @Transactional
  public ResponseEntity<ConsultationData> createReservation(@RequestBody @Valid ReservationData reservationData) {
    inquiries.createConsultation(reservationData);
    return ResponseEntity.ok(new ConsultationData(null, null, null, null));
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<Void> cancelReservation(@RequestBody @Valid CancelReservationData cancelReservationData) {
    inquiries.cancelConsultation(cancelReservationData);
    return ResponseEntity.noContent().build();
  }

}
