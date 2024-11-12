package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.AddressDTO;

public record CreateDoctorDTO(

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    String phone,

    @NotBlank
    @Pattern(regexp = "\\d{6,10}")
    String document,

    @NotNull
    Specialty specialty,

    @NotNull
    @Valid
    AddressDTO address

) {
}
