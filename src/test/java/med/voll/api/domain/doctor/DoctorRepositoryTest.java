package med.voll.api.domain.doctor;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegisterData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private EntityManager em;

  @Test
  @DisplayName("Debe retornar null si el doctor buscado existe pero no está disponible en ese horario")
  void chooseRandomDoctorScenario1() {
    // Given or Arrange
    var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

    var doctor = registerDoctor("Dr. House", "medico@vollmed.com", "9234234", "23425", Specialty.CARDIOLOGY);
    var patient = registerPatient("Alba", "alba@correo.com", "23423565", "35346563");
    registerConsultation(doctor, patient, nextMondayAt10);

    // When or Act
    var aviableDoctor = doctorRepository.chooseRandomDoctor(Specialty.CARDIOLOGY, nextMondayAt10);

    // Then or Assert
    assertThat(aviableDoctor).isNull();
  }

  @Test
  @DisplayName("Debe retornar un doctor si el doctor buscado existe y está disponible en ese horario")
  void chooseRandomDoctorScenario2() {
    // Given or Arrange
    var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

    var doctor = registerDoctor("Dr. House", "medico@vollmed.com", "9234234", "23425", Specialty.CARDIOLOGY);

    // When or Act
    var aviableDoctor = doctorRepository.chooseRandomDoctor(Specialty.CARDIOLOGY, nextMondayAt10);

    // Then or Assert
    assertThat(aviableDoctor).isEqualTo(doctor);
  }

  private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime date) {
    em.persist(new Consultation(null, doctor, patient, null, date));
  }

  private Doctor registerDoctor(String name, String email, String phone, String document, Specialty specialty) {
    var doctor = new Doctor(doctorData(name, email, phone, document, specialty));
    em.persist(doctor);
    return doctor;
  }

  private Patient registerPatient(String name, String email, String phone, String document) {
    var patient = new Patient(patientData(name, email, phone, document));
    em.persist(patient);
    return patient;
  }

  private CreateDoctorDTO doctorData(String name, String email, String phone, String document, Specialty specialty) {
    return new CreateDoctorDTO(
        name,
        email,
        phone,
        document,
        specialty,
        addressData()
    );
  }

  private PatientRegisterData patientData(String name, String email, String phone, String document) {
    return new PatientRegisterData(
        name,
        email,
        phone,
        document,
        addressData()
    );
  }

  private AddressDTO addressData() {
    return new AddressDTO(
        "Rua dos Bobos",
        "0",
        "apto 101",
        "Bairro Feliz",
        "São Paulo"
    );
  }

}