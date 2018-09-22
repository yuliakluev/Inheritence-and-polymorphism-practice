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
public abstract class FixedLock extends ParentLocks{
     
    public FixedLock() {
        this.fixedCombo(); 
    }
    
    
    private void fixedCombo(){
        for( int i = 0; i< numOfDigs; i++){
           
            combo[i] =  (int)(maxLockValue * Math.random());  

            
        }
    }
    
    
    @Override
    public void close(){
        if(!instantiated){
            
            System.out.println("You cannot close the lock without knowing its combination. Call .getCombo()");
//            System.out.println("Its combination is: ");
//            System.out.println(this.getCombo());
//            System.out.println("You may now close the lock ");
            
        }
        else{
            super.close();
        }
    }
    @Override
    //SET COMBO BEING PUBLIC IS BAD////
    public void setCombo(){ 
        System.out.println("the combination for fixed locks is set automatically");
    }
    
    @Override
    public void setCombo ( int[] configCombo){
        this.setCombo();
    }

    @Override
    public boolean isSet() {
        return true; 
    }

    
    
    
}
