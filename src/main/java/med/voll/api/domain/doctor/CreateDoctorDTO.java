package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDTO;

public record CreateDoctorDTO(

    @NotBlank(message = "{name.mandatory}")
    String name,

    @NotBlank(message = "{email.mandatory}")
    @Email(message = "{email.required}")
    String email,

    @NotBlank(message = "{phone.mandatory}")
    @Pattern(regexp = "\\d{10}", message = "{phone.required}")
    String phone,

    @NotBlank(message = "{document.mandatory}")
    @Pattern(regexp = "\\d{6,10}", message = "{document.required}")
    String document,

    @NotNull(message = "{specialty.mandatory}")
    Specialty specialty,

    @NotNull(message = "{address.mandatory}")
    @Valid
    AddressDTO address

) {
}
