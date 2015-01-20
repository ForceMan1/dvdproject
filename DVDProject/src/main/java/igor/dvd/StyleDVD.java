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
        @NamedQuery(name="StyleDVD.findByName", query="SELECT s.id from StyleDVD s where s.name = :name"),
        @NamedQuery(name="StyleDVD.findAll", query="SELECT s from StyleDVD s"),
        @NamedQuery(name="StyleDVD.findById", query="SELECT s from StyleDVD s where s.id = :id")
})
@Table(name="style")
public class StyleDVD implements Serializable {
    /******** Fields ********************/
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    //        (strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    private String name;

    /******** Methods *****************/
    public StyleDVD(){};
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
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
        if (!(object instanceof StyleDVD)) {
            return false;
        }
        StyleDVD other = (StyleDVD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "igor.dvd.StyleDVD[ id=" + id + " ]";
    }
    
}
