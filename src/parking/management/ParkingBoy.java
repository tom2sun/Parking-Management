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
import parking.management.ParkingLot;
import parking.management.ParkingManager;

@SuppressWarnings("unused")

public class ParkingBoy {
    public int BoyNO;
    public int CarNum;
    public Location ParkingBoyCar[] = new Location[100];
    
    ParkingBoy(int n){
        BoyNO = n;
        CarNum = 0;
        for(int i=0; i<=19; i++) {
            ParkingBoyCar[i] = null;
        }
    }    
    
    public int ParkCar(int LotNO, int m, int n) {
        if(m>ParkingManager.Lots[LotNO].M || n>ParkingManager.Lots[LotNO].N || m<0 || n<0) {
            return 2; //停车位置不合法情况
        }
        if(ParkingManager.Lots[LotNO].parkCar(m, n, LotNO)) {
            CarNum++;
            Location loc = new Location(); 
            loc.setLocation(m, n, LotNO);
            ParkingBoyCar[CarNum-1] = loc;            
            return 1; //停车成功
        }
        else
            return 0;        
    }
    
    public int TakeCar(int LotNO, int m, int n) {
        if(m>ParkingManager.Lots[LotNO].M || n>ParkingManager.Lots[LotNO].N || m<0 || n<0) {
            return 2; //停车位置不合法情况
        }
        int HasCarFlag = 0; //用于查看输入的位置是否有车
        int TakeCarLocNO = 0;
        for(int i=0; i<=CarNum-1; i++) {
            if(ParkingBoyCar[i].getLocationM()==m && 
                    ParkingBoyCar[i].getLocationN()==n
                    && ParkingBoyCar[i].getLotNO()==LotNO) {
                HasCarFlag = 1;
                TakeCarLocNO = i;
                break;
            }
        }
        if(HasCarFlag==0) { //要求取车的位置没有车，返回错误信息
            return 3;
        }
        
        if(ParkingManager.Lots[LotNO].takeCar(m, n, LotNO)) {           
            ParkingBoyCar[TakeCarLocNO] = null; //将车位腾空
            CarNum--;
            for(int i=TakeCarLocNO; i<=CarNum-1; i++) { //移动停车记录
                ParkingBoyCar[i] = ParkingBoyCar[i+1];
                ParkingBoyCar[i+1] = null;
            }
            return 1; //取车成功
        }
        else
            return 0;        
    }    
}
