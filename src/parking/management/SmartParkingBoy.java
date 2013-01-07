/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parking.management;

/**
 *
 * @author Wendy
 */
import parking.management.ParkingManagement;
import java.io.*;

@SuppressWarnings("unused")

public class SmartParkingBoy {
    public int BoyNO;
    public int CarNum;
    public Location SmartParkingBoyCar[] = new Location[100];
    
    SmartParkingBoy(int n){
        BoyNO = n;
        CarNum = 0;
        for(int i=0; i<=99; i++) {
            SmartParkingBoyCar[i] = null;
        }
    }
    
    public Location ParkCar(int ParkingLotNum) {
        int MaxFree = 0;
        int MaxFreeLot = 0;
        Location loc = new Location();
        
        for(int i=0; i<=ParkingLotNum-1; i++) {
            if(ParkingManager.Lots[i].getEmptyNumber(i)>MaxFree) {
                MaxFree = ParkingManager.Lots[i].getEmptyNumber(i);
                MaxFreeLot = i;
            }            
        }
        int flag = 0; //flag:停车是否成功标记

        while(flag==0) {
            //产生随机位置
            int m = (int)(Math.random()*ParkingManager.Lots[MaxFreeLot].M);
            int n = (int)(Math.random()*ParkingManager.Lots[MaxFreeLot].N);

            if(ParkingManager.Lots[MaxFreeLot].parkCar(m, n, MaxFreeLot)) { //停车
                flag = 1;
                loc.setLocation(m, n, MaxFreeLot);
                CarNum++;          
                this.SmartParkingBoyCar[CarNum-1] = loc;
                return loc; //成功则返回停车位置
            }
        }    
        loc.setErrorLocation();
        return loc;
    }
    
}
