//https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
package com.ur.entities;
public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}