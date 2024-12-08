package med.voll.api.domain.patient;

public record ListPatientData(
    String name,
    String email,
    String phone,
    String document
) {

  public ListPatientData(Patient patient) {
    this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getDocument());
  }

}
