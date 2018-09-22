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
//SHOULD THIS BE AN ABSTRACT CLASS?
public abstract class ParentLocks {

    private int attempts;
    final static int maxAttempts = 3;
    protected int numOfDigs;
    private static int counter = 0; 
    private int serialNumber = 0;
    private boolean closed;
    protected boolean instantiated;
    protected int[] combo;
    protected int[] noCombo = new int[1]; 
    protected int maxLockValue = 9;
    
    public ParentLocks() {
     
        numOfDigs = 3; 
        attempts = 0; 
        combo = new int[numOfDigs];
        noCombo[0] = -1; 
        serialNumber = ++counter;
  
    }

    

    public int getSerialNumber() {
        return serialNumber;
    }
    protected void setMaxValue(int n) {
        maxLockValue = n; 
    }
    
    public int getMaxLockValue(){
     
        return this.maxLockValue;  
    }

    protected void setNumOfDigs(int n) {
        numOfDigs = n; 
    }
   
    public int getNumOfDigs(){
    
        return this.numOfDigs; 
    }
    
    public int getAttempts() {
        
        return attempts;
    }
    
    public boolean isInstantiated(){
        if( !instantiated) return false;
        return true; 
    }
    
    public boolean isOpen() {

        if (this.closed) {
            return false;
        }
        return true;

    }
    
    public void close() {
        
        //DONT CLOSE WITHOUT SETTING A COMBINATION
        if(!this.isSet()){
            System.out.println("do not close this lock as a combination has not yet been set");
        }else{
            System.out.println("The lock is now closed");
            this.closed = true;
        }
        
    }

    public boolean unlock(int[] userCombo) {
        //is this statement necessary?
        if (instantiated) {
            //if the lock is already open
            if (this.isOpen()) {
                System.out.println("This lock is already open");
                return true;
            }
            //if you have exceeded the number of attempts which can be made
            if (this.getAttempts() == maxAttempts) {
                System.out.println("you have been locked out of the lock after 3 attempts");
                return false;
            }
            //if the combination has the wrong number of parameters
            if (userCombo.length != numOfDigs) {
                System.out.println("The combination you have enterred contains the wrong number of parameters");
                attempts++;
                return false;
            }
            //compare the two combinations
        
            for (int i = 0; i < numOfDigs; i++) {

                if (userCombo[i] != combo[i]) {
                    this.attempts++;
                    System.out.println("the combination you have enterred is incorrect");
                    return false;

                }

            }
   
            this.closed = false;
            this.attempts++;
            System.out.println("it took you " + this.attempts + " attempts to successfully unlock the lock ");
            
            //set attempts back to 0 after successfully unlocking the lock
            this.attempts = 0;
            //unlock the lock
            return true;
        }

        System.out.println("You cannot unlock the lock without knowing the combination");
        return false;
    }

    /**
     *
     * @param configCombo
     * 
     */
    abstract public void setCombo( int[] configCombo);
    abstract public void setCombo(); 
    abstract public boolean isSet(); 
    
    
    
    public int[] getCombo() {
        
        if (!this.instantiated && this.isSet()) {
            instantiated = true;
      
            return combo;
        } 
        System.out.println("you can only get the combination when the lock is first instantiated");
        return noCombo; 
             
        
    }
}
