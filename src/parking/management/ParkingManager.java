/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parking.management;

/**
 *
 * @author Wendy
 */
import parking.management.ParkingBoy;
import java.io.*;
import java.util.Scanner;
import java.math.*;

@SuppressWarnings("unused")

public class ParkingManager {
    public int ParkingLotNum;
    public int ParkingBoyNum;
    public int SmartParkingBoyNum;
    public int SuperParkingBoyNum;
    public int CarNum;
    public Location[] ManagerCar = new Location[50];
    
    public static ParkingBoy[] Boys = new ParkingBoy[20]; //普通停车仔团队，设定最多为20个
    public static SmartParkingBoy[] SmartBoys = new SmartParkingBoy[10]; //Smart停车仔团队，设定最多为10个
    public static SuperParkingBoy[] SuperBoys = new SuperParkingBoy[10]; //Super停车仔团队，设定最多为10个
    public static ParkingLot[] Lots = new ParkingLot[10]; //停车场个数，设定最多为10个
    
    ParkingManager() {
        CarNum = 0;
        for(int i=0; i<=19; i++) {
            ManagerCar[i] = null;
        }
        
        //最初拥有2个停车场default,最初停了几辆车
        Lots[0] = new ParkingLot(5, 4, 0);
        Lots[1] = new ParkingLot(6, 6, 1);
        Lots[2] = new ParkingLot(4, 6, 1);
        ParkingLotNum = 3;
        
        //最初拥有4个停车仔default，2个普通停车仔，1个Smart停车仔，1个Super停车仔
        Boys[0] = new ParkingBoy(0);
        Boys[1] = new ParkingBoy(1);
        Boys[2] = new ParkingBoy(2);
        Boys[3] = new ParkingBoy(3);
        SmartBoys[0] = new SmartParkingBoy(0);
        SmartBoys[1] = new SmartParkingBoy(1);
        SmartBoys[2] = new SmartParkingBoy(2);
        SuperBoys[0] = new SuperParkingBoy(0);
        SuperBoys[1] = new SuperParkingBoy(1);
        ParkingBoyNum = 4;   
        SmartParkingBoyNum = 3;
        SuperParkingBoyNum = 2;
        
        Boys[0].ParkCar(0, 2, 3);
        Boys[0].ParkCar(1, 3, 3);
        Boys[1].ParkCar(2, 3, 5);        
        SmartBoys[0].ParkCar(3);
        SmartBoys[1].ParkCar(3);        
        SuperBoys[0].ParkCar(3);
    }
    
    public Location RandomParkCar() {
        Location loc = new Location();        
       
        if(allNull()) { //如果所有停车场都为满，返回错误提示
            loc.setErrorLocation();
            return loc;
        }
        else { //有停车场有空位
            int findFreeLot = 0;
            
            while(findFreeLot==0) {            
                int LotNO = (int)(Math.random()*ParkingLotNum); //随机产生一个停车场号
                //System.out.println(LotNO);
                if(Lots[LotNO].getEmptyNumber(LotNO)!=0) { //如果该停车场有空位
                    findFreeLot = 1;
                    int flag = 0; //flag:停车是否成功标记

                    while(flag==0) {
                        //产生随机位置
                        int m = (int)(Math.random()*Lots[LotNO].M);
                        int n = (int)(Math.random()*Lots[LotNO].N);

                        if(Lots[LotNO].parkCar(m, n, LotNO)) { //停车
                            flag = 1;                            
                            CarNum++;
                            loc.setLocation(m, n, LotNO);
                            this.ManagerCar[CarNum-1] = loc; 
                            return loc; //成功则返回停车位置
                        }
                    }                            
                } //end if
            }
        } //end else
        loc.setErrorLocation();
        return loc;
    }
    
    //让普通停车仔停车函数
    public void MakeBoysParking(int BoyNO, int LotNO, int m, int n) {
        int ParkingResult = Boys[BoyNO].ParkCar(LotNO, m, n);
        if(ParkingResult==2) {
            System.out.println("输入的停车场坐标不合法（可能是超出了范围），请重新输入！");
        }
        if(ParkingResult==1) {
            //Location loc = new Location();
            //loc.setLocation(m, n, LotNO);
            //ParkingBoy.ParkingBoyCar[ParkingManager.Boys[BoyNO].CarNum-1] = loc;
            System.out.println(BoyNO+"号停车仔在第"+LotNO+"停车场坐标("+m+", "+n+")停车成功！");
        }
        else
            System.out.println("停车失败！该位置已被占用。");    
    }
    
    //让聪明停车仔停车
    public Location MakeSmartBoysParking(int BoyNO) {
        Location loc = new Location();        
       
        if(allNull()) { //如果所有停车场都为满，返回错误提示
            loc.setErrorLocation();
            return loc;
        }
        else { //有停车场有空位
            loc = SmartBoys[BoyNO].ParkCar(ParkingLotNum);
            //SmartParkingBoy.SmartParkingBoyCar[ParkingManager.SmartBoys[BoyNO].CarNum-1] = loc;
            return loc;
        }
    }
    
    //让超级停车仔停车
    public Location MakeSuperBoysParking(int BoyNO) {
        Location loc = new Location();        
       
        if(allNull()) { //如果所有停车场都为满，返回错误提示
            loc.setErrorLocation();
            return loc;
        }
        else { //有停车场有空位
            Location locTemp = SuperBoys[BoyNO].ParkCar(ParkingLotNum);
            return locTemp;
        }
    }
    
    public boolean isNull() {
        for(int i=0; i<=9; i++) {
            if(Boys[i]!=null) {
                return false;
            }
        }
        return true;
    }
    
    //判断是否所有停车场都为满函数
    public boolean allNull() {
        for(int i=0; i<=ParkingLotNum-1; i++) {
            if(Lots[i].getEmptyNumber(i)!=0) {
                return false;
            }
        }
        return true;
    }
    
    //创建一个数组，里面存放有空位的停车场。数组0下标元素存放有空位停车场的个数。
    public int[] FindFreeLot() {
        int FreeLot[] = new int[10];
        int j = 0;
        for(int i=0; i<=ParkingLotNum-1; i++) {
            if(Lots[i].getEmptyNumber(i)>=1) {
                j++;
                FreeLot[j] = i;
            }
        }
        FreeLot[0] = j;
        return FreeLot;
    }
}
