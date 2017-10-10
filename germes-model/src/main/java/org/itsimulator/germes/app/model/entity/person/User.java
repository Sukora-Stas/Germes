package org.itsimulator.germes.app.model.entity.person;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;

import javax.persistence.*;

/**
 * Entity that encapsulates user of the application
 */

@Table(name = "USER")
@Entity
@NamedQueries({@NamedQuery(name = User.QUERY_FIND_ALL, query = "from User"),
        @NamedQuery(name = User.QUERY_FIND_BY_USERNAME, query = "from User where userName = :userName")})
public class User extends AbstractEntity {
    public static final String QUERY_FIND_ALL = "User.findAll";

    public static final String QUERY_FIND_BY_USERNAME = "User.findByUserName";

    /**
     * Unique user name within the system
     */
    private String userName;

    /**
     * User password
     */
    private String password;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 24)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "PASSWORD", nullable = false, length = 24)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}