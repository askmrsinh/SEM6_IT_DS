/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import javax.ejb.Stateless;

/**
 *
 * @author Ashesh
 */
@Stateless
public class calcbean implements calcbeanLocal {

  // Add business logic below. (Right-click in editor and choose
  // "Insert Code > Add Business Method")
  @Override
  public Integer addition(int a, int b) {
    return (a + b);
  }

}
