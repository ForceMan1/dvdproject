/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package igor.dvd;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author Игорь
 */
@Stateless
public class CountryEJB {
    @PersistenceContext(name="DVD")
    private EntityManager em;
    private CountryDVD country = new CountryDVD();
    
    
    /************** Methods ********************************/
    public boolean addCountry(String name){
        try {
            TypedQuery<CountryDVD> query = em.createNamedQuery("CountryDVD.findByName", CountryDVD.class);
            query.setParameter("name", name);
            List<CountryDVD> countries = query.getResultList();
            if(countries.size() > 0)
                return false;
            else{
                country.setId(null);
                country.setName(name);
                em.persist(country);
                em.flush();
                return true;
            }
        }catch(Exception exc){
            String text = exc.getMessage();
            return false;
        }    
    }
    
    public List<CountryDVD> getListCountries(){
        TypedQuery query = em.createNamedQuery("CountryDVD.findAll", CountryDVD.class);
        return query.getResultList();
    }
    
    public void deleteCountry(String name){
        try {
            country = em.find(CountryDVD.class, Integer.parseInt(name));
            if(country != null){
                //em.persist(country);
                em.remove(country);
                em.flush();
            }
        }catch(Exception exc){
            
        }    
     
    }
    
    /****** Find Country by id *********/
    public CountryDVD findCountryById(Integer id){
        try {
            int i;
            i =0;
            return em.find(CountryDVD.class, id);
        }catch(Exception e){
            return null;
        }
    }
    
}
