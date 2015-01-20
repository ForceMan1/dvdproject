/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package igor.dvd;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

/**
 *
 * @author Игорь
 */
@Stateful
//@DeclareRoles("ADMIN")
public class LoginEJB {
    @Resource
    SessionContext sctx;
    
    public String getPrincipalName(){
        return sctx.getCallerPrincipal().getName();
    }
    
    
    public Boolean isPrincipalInRole(String role){
        return sctx.isCallerInRole(role);
    }
    
    public void logout(){
        //sctx.
    }
}
