package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  private String street;
  private String district;
  private String city;
  private String number;
  private String complement;

  public Address(AddressDTO addressDTO) {
    this.street = addressDTO.street();
    this.district = addressDTO.district();
    this.city = addressDTO.city();
    this.number = addressDTO.number();
    this.complement = addressDTO.complement();
  }

  public Address update(AddressDTO addressDTO) {
    this.street = addressDTO.street();
    this.district = addressDTO.district();
    this.city = addressDTO.city();
    this.number = addressDTO.number();
    this.complement = addressDTO.complement();
    return this;
  }

}
