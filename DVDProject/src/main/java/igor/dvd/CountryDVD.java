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
    @NamedQuery(name="CountryDVD.findByName", query="SELECT c.id from CountryDVD c where c.name = :name"),
    @NamedQuery(name="CountryDVD.findAll", query="SELECT c from CountryDVD c")
    
})
@Table(name="country")
public class CountryDVD implements Serializable {
    /********* Fields *********************/
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    
    /************ Methods ************************/
    public CountryDVD(){};
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof CountryDVD)) {
            return false;
        }
        CountryDVD other = (CountryDVD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "igor.dvd.CountryDVD[ id=" + id + " ]";
    }
    
}
