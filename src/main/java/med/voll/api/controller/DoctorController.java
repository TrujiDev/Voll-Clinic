package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

  @Autowired
  private DoctorRepository doctorRepository;

  @PostMapping
  public void registerDoctor(@RequestBody @Valid CreateDoctorDTO createDoctorDTO) {
    doctorRepository.save(new Doctor(createDoctorDTO));
  }

  @GetMapping
  public Page<ReadDoctorsDTO> listDoctors(@PageableDefault(size = 4) Pageable pagination) {
    return doctorRepository.findByIsActiveTrue(pagination).map(ReadDoctorsDTO::new);
//    return doctorRepository.findAll(pagination).map(ReadDoctorsDTO::new);
  }

  @PutMapping
  @Transactional
  public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
    Doctor doctor = doctorRepository.getReferenceById(updateDoctorDTO.id());
    doctor.updateData(updateDoctorDTO);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void deleteDoctor(@PathVariable Long id) {
    Doctor doctor = doctorRepository.getReferenceById(id);
    doctor.deactivateDoctor();
  }

}
