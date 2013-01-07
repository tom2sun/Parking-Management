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

public class SuperParkingBoy {
    public int BoyNO;
    public int CarNum;
    public Location SuperParkingBoyCar[] = new Location[100];
    
    SuperParkingBoy(int n) {
        BoyNO = n;
        CarNum = 0;
        for(int i=0; i<=99; i++) {
            SuperParkingBoyCar[i] = null;
        }
    }
    
    public Location ParkCar(int ParkingLotNum) {
        double MaxRate = 0.0;
        int MaxRateLot = 0;
        Location loc = new Location();
        
        for(int i=0; i<=ParkingLotNum-1; i++) {
            if(ParkingManager.Lots[i].getEmptyRate(i)>MaxRate) {
                MaxRate = ParkingManager.Lots[i].getEmptyRate(i);
                MaxRateLot = i;
            }            
        }
        int flag = 0; //flag:停车是否成功标记

        while(flag==0) {
            //产生随机位置
            int m = (int)(Math.random()*ParkingManager.Lots[MaxRateLot].M);
            int n = (int)(Math.random()*ParkingManager.Lots[MaxRateLot].N);

            if(ParkingManager.Lots[MaxRateLot].parkCar(m, n, MaxRateLot)) { //停车
                flag = 1;
                loc.setLocation(m, n, MaxRateLot);
                CarNum++;          
                this.SuperParkingBoyCar[CarNum-1] = loc;
                return loc; //成功则返回停车位置
            }
        }    
        loc.setErrorLocation();
        return loc;
    }
    
}
