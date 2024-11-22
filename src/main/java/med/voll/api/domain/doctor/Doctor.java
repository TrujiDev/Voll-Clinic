package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String phone;
  private String document;

  @Column(name = "is_active")
  private Boolean isActive;

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  @Embedded()
  private Address address;

  public Doctor(CreateDoctorDTO createDoctorDTO) {
    this.isActive = true;
    this.name = createDoctorDTO.name();
    this.email = createDoctorDTO.email();
    this.phone = createDoctorDTO.phone();
    this.document = createDoctorDTO.document();
    this.specialty = createDoctorDTO.specialty();
    this.address = new Address(createDoctorDTO.address());
  }

  public void updateData(UpdateDoctorDTO updateDoctorDTO) {
    if (updateDoctorDTO.name() != null) {
      this.name = updateDoctorDTO.name();
    }

    if (updateDoctorDTO.document() != null) {
      this.document = updateDoctorDTO.name();
    }

    if (updateDoctorDTO.address() != null) {
      this.address = address.updateData(updateDoctorDTO.address());
    }
  }

  public void deactivateDoctor() {
    this.isActive = false;
  }
}
