/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vel.cdi.example1;

/**
 *
 * @author velmuruganv
 */
public class HelloImpl implements Hello {

    @Override
    public String say(String name) {    
              return "hello mr."+ name;
    }
    
}
