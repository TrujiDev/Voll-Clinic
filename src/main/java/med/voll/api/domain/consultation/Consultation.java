package med.voll.api.domain.consultation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity(name = "Consultation")
@Table(name = "consultation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @Column(name = "cancel_reason")
  @Enumerated(EnumType.STRING)
  private CancelReason cancelReason;

  private LocalDateTime date;

  public void cancel(@NotNull CancelReason reason) {
    this.cancelReason = reason;
  }
}
