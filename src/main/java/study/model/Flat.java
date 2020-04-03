package study.model;


import javax.persistence.*;

@Entity
@Table(name = "Flats")
public class Flat {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String flatNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private HouseGroup houseGroupFlats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HouseGroup getHouseGroupFlats() {
        return houseGroupFlats;
    }

    public void setHouseGroupFlats(HouseGroup houseGroupFlats) {
        this.houseGroupFlats = houseGroupFlats;
    }
}
