package com.mwstudio.component.rest.dao.entity;

import javax.persistence.*;
import java.util.Date;


/**
 * it is Booking entity
 *
 * @author mengwang
 */
@Entity
@Table(name = "BOOKING", schema = "STUDIO")
public class BookingEntity {
    private Integer id;
    private Date date;
    private UserEntity user;

    /**
     * @return the booking id
     */
    @Id
    @Column(name = "BOOKING_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return booking date
     */
    @Column(name = "BOOKING_DATE", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "USERID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Booking[");
        sb.append("id : ");
        sb.append(this.id);
        sb.append(", ");

        sb.append("date : ");
        sb.append(this.date);
        sb.append(" ");

        sb.append("with user : ");
        sb.append(this.user.getId());
        sb.append("]");

        return sb.toString();
    }

}
