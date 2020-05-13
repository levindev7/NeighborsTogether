package study.web;

import study.model.HouseGroup;

import java.util.List;

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
}
