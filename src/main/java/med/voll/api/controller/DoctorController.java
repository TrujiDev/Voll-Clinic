package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-jwt")
public class DoctorController {

  @Autowired
  private DoctorRepository doctorRepository;

  @PostMapping
  public ResponseEntity<ResponseDoctorDTO> registerDoctor(
      @RequestBody @Valid CreateDoctorDTO createDoctorDTO,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    Doctor doctor = doctorRepository.save(new Doctor(createDoctorDTO));
    ResponseDoctorDTO responseDoctorDTO = ResponseDoctorDTO.from(doctor);
    URI url = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
    return ResponseEntity.created(url).body(responseDoctorDTO);
  }

  @GetMapping
  public ResponseEntity<Page<ReadDoctorsDTO>> listDoctors(@PageableDefault(size = 4) Pageable pagination) {
    return ResponseEntity.ok(doctorRepository.findByIsActiveTrue(pagination).map(ReadDoctorsDTO::new));
//    return doctorRepository.findAll(pagination).map(ReadDoctorsDTO::new);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<ResponseDoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
    Doctor doctor = doctorRepository.getReferenceById(updateDoctorDTO.id());
    doctor.updateData(updateDoctorDTO);
    return ResponseEntity.ok(ResponseDoctorDTO.from(doctor));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
    Doctor doctor = doctorRepository.getReferenceById(id);
    doctor.deactivateDoctor();
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDoctorDTO> getDoctorByID(@PathVariable Long id) {
    Doctor doctor = doctorRepository.getReferenceById(id);
    var responseDoctorDTO = ResponseDoctorDTO.from(doctor);
    return ResponseEntity.ok(responseDoctorDTO);
  }

}
