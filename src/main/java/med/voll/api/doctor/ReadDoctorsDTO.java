package med.voll.api.doctor;

public record ReadDoctorsDTO(

    Long id,
    String name,
    String specialty,
    String document,
    String email

) {

  public ReadDoctorsDTO(Doctor doctor) {
    this(doctor.getId(), doctor.getName(), doctor.getSpecialty().toString(), doctor.getDocument(), doctor.getEmail());
  }

}
