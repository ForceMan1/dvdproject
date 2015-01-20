/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package igor.dvd;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Игорь
 */
@Entity
@NamedQueries({
    @NamedQuery(name="DVD.findAll", query="SELECT d from DVD d"),
    @NamedQuery(name="DVD.findUserDVD", query="SELECT d from DVD d where d.username = :name")
})
public class DVD implements Serializable {
    private static final long serialVersionUID = 1L;
    /****** Fields ********************************/
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 255)
    private String title;
    
    @OneToOne(fetch=FetchType.LAZY)
              //cascade={CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="style_fk")
    private StyleDVD style;
    
    @OneToOne(fetch=FetchType.LAZY)
           //cascade={CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="country_fk")
    private CountryDVD country;
    
    @Column(length=64)
    private String username;
    
    /********* Constructors *******************/
    public DVD(){};
    
    public DVD(Long id, String title, StyleDVD styleDVD, CountryDVD countryDVD){
        this.id = id;
        this.title = title;
        this.style = styleDVD;
        this.country = countryDVD;
        
    }
    
    
    /******* Methods ***************************************/
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getStyleName(){
        if(style != null){
            return style.getName();
        }
        return "";
    }
    
    public String getCountryName(){
        if(country != null){
            return country.getName();
        }
        return "";
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DVD)) {
            return false;
        }
        DVD other = (DVD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "igor.dvd.DVD[ id=" + id + " ]";
    }
    
}
