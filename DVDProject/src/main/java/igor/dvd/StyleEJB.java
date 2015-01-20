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
import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.transaction.UserTransaction;

/**
 *
 * @author Игорь
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class StyleEJB {
    /**** Fields *************************/
    @PersistenceContext(name="DVD")
    private EntityManager em;
    // Injects UserTransaction
    //@Resource
    //private UserTransaction tx;
    private StyleDVD style = new StyleDVD();
    
    /************ Methods ******************/
    public void addStyle(String name){
        TypedQuery<StyleDVD> query = em.createNamedQuery("StyleDVD.findByName", StyleDVD.class);
        query.setParameter("name", name);
        
        try {
            List<StyleDVD> styles = query.getResultList();
            if(styles.isEmpty() )
            {
                style.setName(name);
                style.setId(null);
                //tx.begin();
                em.persist(style);
                em.flush();
                //tx.commit();
                /*
                tx.begin();
                em.detach(style);
                tx.commit();
                
                */
            } 
        }catch(Exception pe){
            System.out.println(pe.getLocalizedMessage());
        }    
    }
    
    public void delStyle(String name){
        try{
            style = em.find(StyleDVD.class, Integer.parseInt(name));
            //tx.begin();
            //em.persist(style);
            em.remove(style);
            //tx.commit();
            em.flush();
        }catch(Exception exc){
                String text = exc.getLocalizedMessage();
                String text2 = "";
        }    
   }
    
    public List<StyleDVD> getListStyles(){
    //public void getListStyles(){    
        TypedQuery<StyleDVD> query = em.createNamedQuery("StyleDVD.findAll", StyleDVD.class);
        //Query query = em.createNamedQuery("StyleDVD.findAll");
        return query.getResultList();
    }
    
    /******** Find  Style by Id **************/
    public StyleDVD findStyleById(Integer id){
       try{
              return em.find(StyleDVD.class, id);
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
          ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
            return null;
        }
    }
    
    
}
