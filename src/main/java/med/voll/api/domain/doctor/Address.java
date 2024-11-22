package med.voll.api.domain.doctor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.AddressDTO;

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

  public Address(AddressDTO address) {
    this.street = address.street();
    this.district = address.district();
    this.city = address.city();
    this.number = address.number();
    this.complement = address.complement();
  }

  public Address updateData(AddressDTO address) {
    this.street = address.street();
    this.district = address.district();
    this.city = address.city();
    this.number = address.number();
    this.complement = address.complement();
    return this;
  }

}
