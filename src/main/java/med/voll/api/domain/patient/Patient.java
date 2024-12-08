package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String document;
  private String phone;
  private Boolean active;

  @Embedded
  private Address address;

  public Patient(PatientRegisterData patientRegisterData) {
    this.name = patientRegisterData.name();
    this.email = patientRegisterData.email();
    this.document = patientRegisterData.document();
    this.phone = patientRegisterData.phone();
    this.active = true;
    this.address = new Address(patientRegisterData.address());
  }

  public void update(PatientUpdateData patientUpdateData) {

    if (patientUpdateData.name() != null) {
      this.name = patientUpdateData.name();
    }

    if (patientUpdateData.email() != null) {
      this.email = patientUpdateData.email();
    }

    if (patientUpdateData.phone() != null) {
      this.phone = patientUpdateData.phone();
    }

    if (patientUpdateData.address() != null) {
      address.update(patientUpdateData.address());
    }

  }

  public void deactivate() {
    this.active = false;
  }

}
