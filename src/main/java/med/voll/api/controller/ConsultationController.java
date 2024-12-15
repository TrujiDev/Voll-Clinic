package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-jwt")
public class ConsultationController {

  @Autowired
  private CreateInquiries inquiries;

  @PostMapping
  @Transactional
  public ResponseEntity<ConsultationData> createReservation(@RequestBody @Valid ReservationData reservationData) {
    var consultationDetails = inquiries.createConsultation(reservationData);
    return ResponseEntity.ok(consultationDetails);
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<Void> cancelReservation(@RequestBody @Valid CancelReservationData cancelReservationData) {
    inquiries.cancelConsultation(cancelReservationData);
    return ResponseEntity.noContent().build();
  }

}
