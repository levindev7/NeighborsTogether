package study.web;

import study.model.HouseGroup;

import java.util.List;
import java.util.Objects;

public class RegistrationForm {
    private String login;
    private String password;
    private String telephoneNumber;
    private List<HouseGroup> houseGroups;
    private HouseGroup selectedHouseGroup;
    private String houseAddress;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<HouseGroup> getHouseGroups() {
        return houseGroups;
    }

    public void setHouseGroups(List<HouseGroup> houseGroups) {
        this.houseGroups = houseGroups;
    }

    public HouseGroup getSelectedHouseGroup() {
        return selectedHouseGroup;
    }

    public void setSelectedHouseGroup(HouseGroup selectedHouseGroup) {
        this.selectedHouseGroup = selectedHouseGroup;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(houseGroups, that.houseGroups) &&
                Objects.equals(selectedHouseGroup, that.selectedHouseGroup) &&
                Objects.equals(houseAddress, that.houseAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, telephoneNumber, houseGroups, selectedHouseGroup, houseAddress);
    }
}
