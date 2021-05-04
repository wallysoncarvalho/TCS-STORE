package info.wallyson.tcsstore.persistence.client;

import info.wallyson.tcsstore.client.domain.CPF;
import info.wallyson.tcsstore.client.domain.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
class ClientEntity {
  @Id private String cpf;

  @Column(name = "full_name", nullable = false, length = 200)
  private String fullName;

  @Column(name = "phone")
  private String phone;

  @Column(name = "fullAddress")
  private String fullAddress;

  @Column(name = "postalCode")
  private String postalCode;

  @Column(name = "district")
  private String district;

  @Column(name = "city")
  private String city;

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

  public static ClientEntity fromClient(Client client) {
    var clientEntity = new ClientEntity();
    clientEntity.setCpf(client.getCpfString());
    clientEntity.setFullName(client.getFullName());
    clientEntity.setPhone(client.getPhone());
    clientEntity.setFullAddress(client.getFullAddress());
    clientEntity.setPostalCode(client.getPostalCode());
    clientEntity.setDistrict(client.getDistrict());
    clientEntity.setCity(client.getCity());
    return clientEntity;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
