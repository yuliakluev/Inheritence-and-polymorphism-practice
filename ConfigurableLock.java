/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.yulia.ics4u.u6;

/**
 *
 * @author yulia
 */
public abstract class ConfigurableLock extends ParentLocks {

    private boolean valueError;
    private boolean isSet; 

    public ConfigurableLock() {

    }

    @Override
    public boolean unlock(int[] userCombo) {

        if (!this.isSet()) {
            System.out.println("you must set the combination before you can unlock the lock");
            return false;
        }
        super.unlock(userCombo);
        return false;

    }

    @Override
    public void setCombo(int[] configCombo) {

        //CHECK THAT THE COMBINATION HAS THE CORRECT NUMBER OF ARGUMENTS
        if (configCombo.length != numOfDigs) {
            System.out.println("this lock requires " + numOfDigs + " parameters. You have provided " + configCombo.length + ". Your combination has not been configured");
        } else if (this.isOpen()) {

            for (int i = 0; i < this.numOfDigs; i++) {

                if (configCombo[i] > maxLockValue || configCombo[i] < 0) {
                    valueError = true;
                }

            }
            
            if (this.valueError) {
                System.out.println("The combination was not inside the required set of values");
            } else {
                System.out.println("you have successfully set a combination");
                isSet = true;
                //firstInstant = false;
                combo = configCombo;

            }

        } else {
            System.out.println("you cannot set the combination if the lock is closed");
        }
    }

    @Override
    public void setCombo(){
        
        System.out.println("You must provide an array to set a combination for this configurable locks");
    }
    //returns if the combination haas been set already
    
    public boolean isSet(){
        return isSet; 
    }

    @Override
    public int[] getCombo() {
        if (!this.isSet()) {
            
           System.out.println("you must first set the combination of the lock, before getting the combination");
           return noCombo; 
            
            
        }
        return super.getCombo();
    }



}
