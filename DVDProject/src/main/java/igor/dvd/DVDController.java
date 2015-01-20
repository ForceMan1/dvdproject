/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package igor.dvd;

import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.ejb.EJB;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author Игорь
**/ 
@ManagedBean(name="DVDController")
@RequestScoped
public class DVDController {
  @EJB
  private LoginEJB loginEJB;
    
  @EJB
  private StyleEJB styleEJB;
  
  @EJB
  private CountryEJB countryEJB;
  
  @EJB
  private DVDEJB dvdEJB;
  
  //@ManagedProperty(value="#{facesContext.externalContext.context.session}") SessionContext sctx; 
  
  private String style;
  private String selStyle;
  
  private String country;
  private String selCountry;
  
  private String title; // Название DVD
  
  // Карта для сохранения выбранных dvd для их удаления/взятия
  private Map<Long, Boolean> selectedDVDs = new HashMap<Long, Boolean>();
  // Карта для сохранения выбранных dvd для их возврата
  private Map<Long, Boolean> selectedDVDsReturn = new HashMap<Long, Boolean>();
  
  
  
  /* Methods ***************/
  
  
  
  public Map<Long, Boolean> getSelectedDVDs() {
    return selectedDVDs;
  }
  
  public Map<Long, Boolean> getSelectedDVDsReturn() {
    return selectedDVDsReturn;
  }
  
  
  public void setStyle(String style){
      this.style = style;
  }
  
  public String getStyle(){
      return this.style;
  }
  
 public void setTitle(String title){
      this.title = title;
  }
  
  public String getTitle(){
      return this.title;
  }
  
  public void setCountry(String country){
      this.country = country;
  }
  
  public String getCountry(){
      return this.country;
  }
  
  public String getSelStyle(){
      return this.selStyle;
  }
  
  public void setSelStyle(String selected){
      this.selStyle = selected;
  }
  
  public List<StyleDVD> getStyles(){
      return styleEJB.getListStyles();
  }
  
  public String getSelCountry(){
      return this.selCountry;
  }
  
  public void setSelCountry(String selected){
      this.selCountry = selected;
  }
  
  /********* Style ********************/
  
  public void createStyle(){
      styleEJB.addStyle(style);
      styleEJB.getListStyles();
  }
  
  /*
  public void styleSelected(ValueChangeEvent value){
       this.sel_style = (String)value.getNewValue();
  }
  
  */
  
  public void deleteStyle(){
      if(this.selStyle != null){
          try{
              styleEJB.delStyle(selStyle);
              styleEJB.getListStyles();
          }
          catch(Exception exc){
              System.out.println(exc.getLocalizedMessage());
          }
      }
  }
  
  /*** Country ***************/
  
  public void createCountry(){
      countryEJB.addCountry(country);
      countryEJB.getListCountries();
  }
  
  public List<CountryDVD> getCountries(){
      return countryEJB.getListCountries();
  }
  
  public void deleteCountry(){
      countryEJB.deleteCountry(selCountry);
      countryEJB.getListCountries();
  }
  
  public DVDController(){}
  
  /******** Create DVD ***************/
  public void addDVD(){
      
      try {
        StyleDVD styleDVD = styleEJB.findStyleById(Integer.parseInt(style));
        CountryDVD countryDVD = countryEJB.findCountryById(Integer.parseInt(country));
        DVD dvd = new DVD(null, title, styleDVD, countryDVD);
        dvdEJB.addDVD(dvd);
      }catch(Exception exc){
          FacesContext ctx = FacesContext.getCurrentInstance();
          ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, exc.getMessage() + 
                    ": " + exc.toString(), ""));
      }
  }
  
  /****** Delete DVD ************/
  public void delDVD(Long id){
      dvdEJB.delDVD(id);
  }
  
  /** Delete selected DVDs *****/
  public void delSelDVDs(){
      Set<Map.Entry<Long, Boolean>> setSelDVDs = this.selectedDVDs.entrySet();
      for(Map.Entry<Long, Boolean> meSelDVDs : setSelDVDs){
          if( meSelDVDs.getValue() == true ){
              delDVD(meSelDVDs.getKey());
          }            
      }
  }
  
  /*******Take DVD  **************/
  public void takeDVD(Long id){
     dvdEJB.takeDVD(id);
  }
  
  /**** Take selDVDs *********/
  public void takeSelDVDs(){
      Set<Map.Entry<Long, Boolean>> setSelDVDs = this.selectedDVDs.entrySet();
      for(Map.Entry<Long, Boolean> meSelDVDs : setSelDVDs){
          if(meSelDVDs.getValue() == true)
            takeDVD(meSelDVDs.getKey());
      }
  }
  
  /****** Return DVD to Library **************/
  public void returnDVD(Long id){
      Set<Map.Entry<Long, Boolean>> setSelDVDs = this.selectedDVDsReturn.entrySet();
      for(Map.Entry<Long,Boolean> meSelDVDs : setSelDVDs){
          if(meSelDVDs.getValue() == true)
              dvdEJB.returnDVD(meSelDVDs.getKey()); // Return DVD with certain ID to the Library
      }
  }
  
  /******* Return selDVD ************/
  public void returnSelDVDs(){
      Set<Map.Entry<Long, Boolean>> setSelDVDs = this.selectedDVDsReturn.entrySet();
      for(Map.Entry<Long, Boolean> meSelDVDs : setSelDVDs){
          if( meSelDVDs.getValue() == true )
              returnDVD(meSelDVDs.getKey());
      }
  }
  
  
  /****** getAll DVDs *************/
  public List<DVD> getAllDVD(){
      return dvdEJB.getAllDVD(); 
  }
  
  public List<DVD> getUserDVD(){
      return dvdEJB.getUserDVD();
  }
  
  public String getPrincipalName(){
     return loginEJB.getPrincipalName();
     //return sctx.getCallerPrincipal().getName(); 
       
  }
  
  
  public Boolean getIsPrincipalInRole(String role){
      return loginEJB.isPrincipalInRole(role);
  }
  
  public void logout(){
      loginEJB.logout();
  }
  /*
  public SessionContext getSctx(){
      return this.sctx;
  }
  
  public void setSctx(SessionContext sctx){
      this.sctx = sctx;
  }
  * */
  
}
