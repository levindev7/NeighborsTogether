package study.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;


    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 16, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // ORDINAL - исп порядковый номер значения, недостаток в том что изменение порядка значений ЕНАМА все сломает
    private UserStatus status;

    //@Transient - аннотация указывающая не преобразовывать поле

    @Column(length = 14, nullable = false)
    private String telephoneNumber;

    @Column
    private int bonusScore;

    public int getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(int bonusScore) {
        this.bonusScore = bonusScore;
    }
//@Column
    //@Convert(converter = ColorConverterExample.class)
    //private Color color;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private HouseGroup houseGroup;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    //@ManyToMany() - mappedBy указывать с обеих сторон
    //@JoinColumn(name = "user_fk") - если schema уже есть
    //@JoinTable() - для задания вида третьей таблицы
    private List<Flat> flats;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public HouseGroup getHouseGroup() {
        return houseGroup;
    }

    public void setHouseGroup(HouseGroup houseGroup) {
        this.houseGroup = houseGroup;
    }

    public List<Flat> getFlats() {
        return flats;
    }

}
