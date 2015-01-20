/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package igor.dvd;

import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
@Stateless
@DeclareRoles({"ADMIN", "USERS"})
public class DVDEJB {
    @PersistenceContext(name="DVD")
    private EntityManager em;
    
    @Resource
    SessionContext sctx;
    
    private DVD dvd = new DVD();
    private String title;
    private Integer style;
    private Integer country;
    
    /***** Constructors *******/
    public DVDEJB(){};
    
    /******* Setters and Getters ****************/
    public void setTitle(String title){
        this.title = title;
    }    
    public String getTitle(){
        return title;    
    }
    
    public void setStyle(Integer style){
        this.style = style;
    }
    public Integer getStyle(){
        return style;
    }
    
    public void setCountry(Integer country){
        this.country = country;
    }
    public Integer getCountry(){
        return country;
    }

    public void addDVD(DVD dvd){
        try {
            em.persist(dvd);
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
        }
    }
    
    public void delDVD(Long id){
        try {
            dvd = em.find(DVD.class, id);
            em.remove(dvd);
            
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
        }
    }
    
    public void takeDVD(Long id){
        try{
            dvd = em.find(DVD.class, id);
            if(dvd.getUsername() == null){
                dvd.setUsername(sctx.getCallerPrincipal().getName());
            }    
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
        }
           
    }
    
    public void returnDVD(Long id){
        try {
            dvd = em.find(DVD.class, id);
            dvd.setUsername(null);
            em.flush();
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
        }
    }
    
    public List<DVD> getAllDVD(){
        try {
            TypedQuery<DVD> query = em.createNamedQuery("DVD.findAll", DVD.class);
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    
    public List<DVD> getUserDVD(){
        String username = sctx.getCallerPrincipal().getName();
        try {
            TypedQuery<DVD> query = em.createNamedQuery("DVD.findUserDVD", DVD.class);
            query.setParameter("name", username);
            return query.getResultList();
        }catch(Exception e){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + 
                    ": " + e.toString(), ""));
        }
        return null;
    }
    
    
    
}
