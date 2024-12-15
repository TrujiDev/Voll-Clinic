package med.voll.api.controller;

import med.voll.api.domain.consultation.ConsultationData;
import med.voll.api.domain.consultation.CreateInquiries;
import med.voll.api.domain.consultation.ReservationData;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JacksonTester<ReservationData> jsonReservation;

  @Autowired
  private JacksonTester<ConsultationData> jsonConsultation;

  @MockBean
  private CreateInquiries createInquiries;

  @Test
  @DisplayName("Debe retornar un 400 si la consulta no tiene datos")
  @WithMockUser
  void createReservationScenario1() throws Exception {
    // Given or Arrange
    // When or Act
    MvcResult result = mockMvc.perform(post("/inquiries"))
        .andReturn();
    var response = result.getResponse();
    // Then or Assert
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("Debe retornar un 200 si la consulta tiene datos válidos")
  @WithMockUser
  void createReservationScenario2() throws Exception {
    // Given or Arrange
    var date = LocalDateTime.now().plusHours(1);
    var specialty = Specialty.CARDIOLOGY;
    var detailsData = new ConsultationData(1L, 2L, 5L, date);

    when(createInquiries.createConsultation(any())).thenReturn(detailsData);
    // When or Act
    MvcResult result = mockMvc.perform(post("/inquiries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonReservation.write(new ReservationData(2L, 5L, date, specialty)).getJson()))
        .andReturn();
    var response = result.getResponse();
    // Then or Assert
    var json = jsonConsultation.write(detailsData).getJson();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(json);
  }

}