package med.voll.api.domain.consultation;

import jakarta.validation.constraints.NotNull;

public record CancelReservationData(

    @NotNull
    Long consultationId,

    @NotNull
    CancelReason reason

) {
}
