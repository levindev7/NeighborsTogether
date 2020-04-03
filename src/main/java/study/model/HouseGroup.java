package study.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class HouseGroup {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String houseAddress;

    public HouseGroup(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public HouseGroup() {
    }

    @OneToMany(mappedBy = "houseGroup")
    private List<User> users;

    @OneToMany(mappedBy = "houseGroupFlats")
    private List<Flat> flats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Flat> getFlats() {
        return flats;
    }
}
