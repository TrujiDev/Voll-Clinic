package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.ListPatientData;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.PatientUpdateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-jwt")
public class PatientController {

  @Autowired
  private PatientRepository patientRepository;

  @GetMapping
  public Page<ListPatientData> list(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pageable) {
    return patientRepository.findAll(pageable).map(ListPatientData::new);
  }

  @PostMapping
  @Transactional
  public void update(@RequestBody @Valid PatientUpdateData patientUpdateData) {
    var patient = patientRepository.getReferenceById(patientUpdateData.id());
    patient.update(patientUpdateData);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void delete(@PathVariable Long id) {
    var patient = patientRepository.getReferenceById(id);
    patient.deactivate();
  }

}
