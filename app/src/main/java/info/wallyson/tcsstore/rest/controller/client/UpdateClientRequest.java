package info.wallyson.tcsstore.rest.controller.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.wallyson.tcsstore.client.domain.CPF;
import info.wallyson.tcsstore.client.domain.Client;

class UpdateClientRequest {
  private String fullName;
  private String phone;
  private String fullAddress;
  private String postalCode;
  private String district;
  private String city;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public UpdateClientRequest(
      @JsonProperty("full_name") String fullName,
      @JsonProperty("phone") String phone,
      @JsonProperty("full_address") String fullAddress,
      @JsonProperty("postal_code") String postalCode,
      @JsonProperty("district") String district,
      @JsonProperty("city") String city) {
    this.fullName = fullName;
    this.phone = phone;
    this.fullAddress = fullAddress;
    this.postalCode = postalCode;
    this.district = district;
    this.city = city;
  }

  public Client toClient(String cpf) {
    return new Client(
        new CPF(cpf),
        this.fullName,
        this.phone,
        this.fullAddress,
        this.postalCode,
        this.district,
        this.city);
  }
}
