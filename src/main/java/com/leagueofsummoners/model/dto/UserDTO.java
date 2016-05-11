package com.leagueofsummoners.model.dto;

import com.leagueofsummoners.model.custom.validators.annotations.ValidateSummonerNameExists;
import com.leagueofsummoners.model.utils.ValidationRegEXP;
import org.apache.commons.lang.WordUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Este bean representa el objeto User de la base de datos
 *
 * @author Juanjors
 */
@Entity(name = "user")
@Table(name = "users")
public class UserDTO extends GenericDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long idUser;

    @Column(nullable = false, name = "summoner_name", unique = true)
    @Length(min = 4, max = 20)
    @Pattern(regexp = ValidationRegEXP.VALIDATE_NAME)
    @ValidateSummonerNameExists
    private String summonerName;

    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 12)
    @Pattern(regexp = ValidationRegEXP.VALIDATE_NAME)
    private String username;

    @Column(nullable = false)
    @Length(min = 8, max = 300)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String avatar;
    @Column(nullable = true)
    private String firma;
    @Column(nullable = true, name = "permission_level")
    private String permissionLevel;

    public UserDTO() {
        this.permissionLevel = "User";
    }

    public UserDTO(UserDTO user) {
        this.idUser = user.idUser;
        this.summonerName = user.summonerName;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.avatar = user.avatar;
        this.firma = user.firma;
        this.permissionLevel = "User";
    }

    public boolean isAdmin() {
        return (this.permissionLevel.equals("Admin"));
    }

    @Override
    public String toString() {
        return "UserDTO [idUser=" + idUser + ", summonerName=" + summonerName + ", username=" + username + ", password="
                + password + ", email=" + email + ", avatar=" + avatar + ", firma=" + firma + ", permissionLevel="
                + permissionLevel + "]";
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirma() {
        /*String firma = WordUtils.wrap(this.firma, 37, "<br>", false);*/
        return this.firma;
    }

    public void setFirma(String firma) {
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }


    public UserDTO(Long idUser, String summonerName, String username, String password, String email, String avatar,
                   String firma, String permissionLevel) {
        super();
        this.idUser = idUser;
        this.summonerName = summonerName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.firma = firma;
        this.permissionLevel = permissionLevel;
    }

    public UserDTO(String summonerName, String username, String password, String email, String avatar,
                   String firma, String permissionLevel) {
        this.summonerName = summonerName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.firma = firma;
        this.permissionLevel = "User";
    }

}