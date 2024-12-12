package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record ReservationData(

    Long doctorId,
    @NotNull
    Long patientId,
    @NotNull
    @Future
    LocalDateTime date,
    Specialty specialty
) {
}
