package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(

    @NotBlank
    String street,

    @NotBlank
    String district,

    @NotBlank
    String city,

    @NotBlank
    String number,

    @NotBlank
    String complement

) {
}
