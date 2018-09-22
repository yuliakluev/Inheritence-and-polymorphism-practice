/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.yulia.ics4u.u6.LOCKS_TESTER;

import edu.hdsb.gwss.yulia.ics4u.u6.Android;
import edu.hdsb.gwss.yulia.ics4u.u6.DubdlyLock;
import edu.hdsb.gwss.yulia.ics4u.u6.Master;
import edu.hdsb.gwss.yulia.ics4u.u6.MasterU;
import java.util.StringTokenizer;

/**
 *
 * @author yulia
 */
public class LocksTester1 {

    //why isn't my class visible when it's in another package?
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Master a = new Master();
        DubdlyLock b = new DubdlyLock();
        MasterU c = new MasterU();
        Android d = new Android();

        int[] userCombo = new int[c.getNumOfDigs()];
        int[] unlockCombo = new int[c.getNumOfDigs()];
        int[] wrongCombo = new int[c.getNumOfDigs() + 1];
        int[] wrongCombo2 = new int[c.getNumOfDigs()];
        int[] fixedCombo = new int[a.getNumOfDigs()];
        String comboDisplay = ""; 

        System.out.println(" ----ASSERTING EACH RESPECTIVE LOCK HAS THE CORRECT MAX VALUE AND NUM OF DIGS-----");
        assert a.getMaxLockValue() == 39;
        assert a.getNumOfDigs() == 3;

        assert b.getMaxLockValue() == 59;
        assert b.getNumOfDigs() == 3;

        assert c.getMaxLockValue() == 9;
        assert c.getNumOfDigs() == 4;

        assert d.getMaxLockValue() == 9;
        assert d.getNumOfDigs() == 3;

        System.out.println("-------ASSERT THAT ALL SERIAL NUMBERS ARE CORRECT--------------------");

        assert a.getSerialNumber() == 1;
        assert b.getSerialNumber() == 2;
        assert c.getSerialNumber() == 3;
        assert d.getSerialNumber() == 4;

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("-------TRYING TO CLOSE ALL LOCKS BEFORE KNOWING THE COMBINATION--------");
        a.close();
        b.close();
        c.close();
        d.close();

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("");
        System.out.println("//////////////////TESTING CONFIGURABLE LOCKS/////////////////////////////");

        System.out.println("My setCombo is");
        System.out.println("");
        for (int i = 0; i < userCombo.length; i++) {

            userCombo[i] = i;
            System.out.print(userCombo[i] + " ");
            System.out.print("");
        }
        System.out.println("");
       

        for (int i = 0; i < wrongCombo2.length; i++) {

            wrongCombo[i] = i + 1;
        }
        //TESTING CONFGURABLE LOCK BEFORE INSTATIATION
        
        assert !c.isInstantiated();
        
        assert c.getAttempts() == 0;
        
        assert c.isOpen();
        
        System.out.println("---trying to close the lock before setting the combination---");
        c.close();

        System.out.println("---------------TRYING TO GET THE COMBINATIONS WITHOUT INSTANTIATING--------------");
        System.out.println("");
        
        unlockCombo = c.getCombo();

        for (int i = 0; i < unlockCombo.length; i++) {
                
                comboDisplay = comboDisplay + " " + unlockCombo[i] + " " ;     
            }   
        System.out.println(comboDisplay);
     
        assert c.getAttempts() == 0;
        
        System.out.println("");
        System.out.println("---------------TRYING TO UNLOCK WITHOUT INSTANTIATING--------------");
        System.out.println("");
  
        c.unlock(userCombo);
        assert c.getAttempts() == 0;
         
        System.out.println("");
        System.out.println("---------------SETTING THE COMBINATION--------------");
        System.out.println("");

        System.out.println("setting case 1: the array combination has the wrong number of numOfDigs");
        System.out.println("");

        c.setCombo(wrongCombo);

        System.out.println("");
        System.out.println("------------");
        System.out.println("setting case 2: the array combination has the correct number of numOfDigs");
        System.out.println("");

        assert !c.isSet();
        c.setCombo(userCombo);
        assert c.isSet();

        System.out.println("");
        System.out.println("---------------GETTING THE COMBINATION--------------");
        System.out.println("");

        unlockCombo = c.getCombo();
        comboDisplay = "";

        for (int i = 0; i < unlockCombo.length; i++) {
                
                comboDisplay = comboDisplay + " " + unlockCombo[i] + " " ;     
            }   
        System.out.println(comboDisplay);
     
        System.out.println("");
        System.out.println("---------------ATTEMPT TO GET THE COMBINATION A SECOND TIME --------------");
        c.getCombo();
        System.out.println("---------------ATTEMPTING TO UNLOCK SUCCESSFULLY-----------------");
        c.close();
        c.unlock(userCombo);
        c.close();
        assert c.getAttempts() == 0;
        assert !c.isOpen();
        System.out.println("---------------ATTEMPTING TO UNLOCK UNSUCCESSFULLY (GETTING LOCKED OUT)-----------");
        c.unlock(wrongCombo);
        assert c.getAttempts() == 1;
        c.unlock(wrongCombo);
        assert c.getAttempts() == 2;
        c.unlock(wrongCombo2);
        assert c.getAttempts() == 3;
        c.unlock(wrongCombo2);

        System.out.println("//////////////////TESTING FIXED LOCKS/////////////////////////////");
        System.out.println("");

        assert a.isSet();
        assert !a.isInstantiated();
   
        a.setCombo();
        a.setCombo(wrongCombo);
        fixedCombo = a.getCombo();
        //System.out.println(fixedCombo);
        //System.out.println(a.getCombo());
        a.close();
        //st = new StringTokenizer(fixedCombo);

//        for (int i = 0; i < fixedArray.length; i++) {
//
//            fixedArray[i] = Integer.parseInt(st.nextToken());
//            System.out.print(fixedArray[i] + " ");
//
//        }
        
        System.out.println("");
        
        a.unlock(fixedCombo);
        assert a.isOpen();
    }

}
