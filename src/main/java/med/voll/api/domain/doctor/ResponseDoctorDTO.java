package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressDTO;

public record ResponseDoctorDTO(
    Long id,
    String name,
    String specialty,
    String email,
    String phone,
    String document,
    AddressDTO address
) {

  public static ResponseDoctorDTO from(Doctor doctor) {
    return new ResponseDoctorDTO(
        doctor.getId(),
        doctor.getName(),
        doctor.getSpecialty().toString(),
        doctor.getEmail(),
        doctor.getPhone(),
        doctor.getDocument(),
        new AddressDTO(
            doctor.getAddress().getStreet(),
            doctor.getAddress().getDistrict(),
            doctor.getAddress().getCity(),
            doctor.getAddress().getNumber(),
            doctor.getAddress().getComplement()
        )
    );
  }


}
