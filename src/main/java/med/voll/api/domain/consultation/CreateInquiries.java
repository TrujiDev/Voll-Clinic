package med.voll.api.domain.consultation;

import med.voll.api.domain.consultation.validations.cancellation.InquiriesCancellation;
import med.voll.api.domain.consultation.validations.reservation.InquiriesValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateInquiries {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private ConsultationRepository consultationRepository;

  @Autowired
  private List<InquiriesValidator> validators;

  @Autowired
  private List<InquiriesCancellation> cancellations;

  public ConsultationData createConsultation(ReservationData data) {
    if (!patientRepository.existsById(data.patientId())) {
      throw new RuntimeException("Patient not found");
    }

    if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
      throw new RuntimeException("Doctor not found");
    }

    validators.forEach(v -> v.validate(data));

    Doctor doctor = assignDoctor(data);
    if (doctor == null) {
      throw new RuntimeException("No Doctor available at that time");
    }
    Patient patient = patientRepository.findById(data.patientId()).orElseThrow();
    Consultation consultation = new Consultation(null, doctor, patient, null, data.date());
    consultationRepository.save(consultation);
    return new ConsultationData(consultation);
  }

  private Doctor assignDoctor(ReservationData data) {
    if (data.doctorId() != null) {
      return doctorRepository.getReferenceById(data.doctorId());
    }

    if (data.specialty() == null) {
      throw new RuntimeException("Specialty is required");
    }

    return doctorRepository.chooseRandomDoctor(data.specialty(), data.date());
  }

  public void cancelConsultation(CancelReservationData data) {
    if (!consultationRepository.existsById(data.consultationId())) {
      throw new RuntimeException("Consultation not found");
    }

    cancellations.forEach(c -> c.validate(data));

    Consultation consultation = consultationRepository.getReferenceById(data.consultationId());
    consultation.cancel(data.reason());
  }

}
