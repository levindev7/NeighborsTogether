package study.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseGroup that = (HouseGroup) o;
        return id == that.id &&
                Objects.equals(houseAddress, that.houseAddress) &&
                Objects.equals(users, that.users) &&
                Objects.equals(flats, that.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, houseAddress, users, flats);
    }
}
