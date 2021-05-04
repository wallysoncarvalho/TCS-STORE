package info.wallyson.tcsstore.rest.controller.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.wallyson.tcsstore.client.domain.CPF;
import info.wallyson.tcsstore.client.domain.Client;

public class ClientRequest {
  private String cpf;
  private String fullName;
  private String phone;
  private String fullAddress;
  private String postalCode;
  private String district;
  private String city;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ClientRequest(
      @JsonProperty(value = "cpf", required = true) String cpf,
      @JsonProperty(value = "full_name", required = true) String fullName,
      @JsonProperty(value = "phone", required = true) String phone,
      @JsonProperty("full_address") String fullAddress,
      @JsonProperty("postal_code") String postalCode,
      @JsonProperty("district") String district,
      @JsonProperty("city") String city) {
    this.cpf = cpf.replaceAll("[^\\d]", "");
    this.fullName = fullName;
    this.phone = phone;
    this.fullAddress = fullAddress;
    this.postalCode = postalCode;
    this.district = district;
    this.city = city;
  }

  public Client toClient() {
    var cpf = new CPF(this.cpf);
    return new Client(
        cpf,
        this.fullName,
        this.phone,
        this.fullAddress,
        this.postalCode,
        this.district,
        this.city);
  }

  public static ClientRequest fromClient(Client client) {
    return new ClientRequest(
        client.getCpfString(),
        client.getFullName(),
        client.getPhone(),
        client.getFullAddress(),
        client.getPostalCode(),
        client.getDistrict(),
        client.getCity());
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getCpf() {
    return cpf;
  }

  @JsonProperty("full_name")
  public String getFullName() {
    return fullName;
  }

  public String getPhone() {
    return phone;
  }

  @JsonProperty("full_address")
  public String getFullAddress() {
    return fullAddress;
  }

  @JsonProperty("postal_code")
  public String getPostalCode() {
    return postalCode;
  }

  public String getDistrict() {
    return district;
  }

  public String getCity() {
    return city;
  }
}
