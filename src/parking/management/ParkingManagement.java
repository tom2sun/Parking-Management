/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parking.management;

/**
 *
 * @author Wendy
 */
import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")

public class ParkingManagement {

    /**
     * @param args the command line arguments
     */
    
    public static ParkingManager PM = new ParkingManager();
    
    public static void main(String[] args) {        
        
        while (true) {
            Scanner x = new Scanner(System.in);
            System.out.println("欢迎光临停车场管理系统，请选择身份："
                    + "1-停车场经理 2-停车场主管");
            int choice = x.nextInt();        

            switch(choice){
                case 1: {
                    Scanner y = new Scanner(System.in);
                    System.out.println("欢迎您！停车场经理！请选择：1-自己随机停车 "
                        + "2-让停车仔停车");
                    int choice2 = y.nextInt();

                    switch(choice2){
                        case 1: {
                            Location loc = PM.RandomParkCar();
                            if(loc.getLocationM()==999 || loc.getLocationN()==999) {
                                System.out.println("停车失败！没有空余位置。");
                            }                
                            else {
                                System.out.println("您在第"+loc.getLotNO()+"停车场"
                                    + "的("+loc.getLocationM()+", "
                                    +loc.getLocationN()+")停车成功！");
                            }    
                            break;
                        }

                        case 2: {
                            Scanner z = new Scanner(System.in);
                            System.out.println("您已进入使唤小弟们模式，请选择："
                                + "1-让普通停车仔停车 2-让聪明停车仔停车 3-让超级停车仔停车");
                            int choice3 = z.nextInt();

                            switch(choice3) {
                                case 1: {
                                    Scanner a = new Scanner(System.in);
                                    System.out.print("您已进入让普通停车仔停车模式，"
                                            + "请输入普通停车仔编号、停车场编号及以停车位置坐标，用空格分隔。\r\n"
                                            + "例如：1 2 3 4代表让停车仔1号在第2号停车场的（3, 4）坐标停车。"
                                            + "提示：现在有普通停车仔");
                                    for(int i=0; i<=PM.ParkingBoyNum-1; i++) {
                                        System.out.print(i+" ");
                                    }
                                    System.out.print("号。\r\n现在有空位的停车场是第");
                                    int FL[] = PM.FindFreeLot();

                                    for(int i=1; i<=FL[0]; i++) {
                                        System.out.print(FL[i]+" ");
                                    }

                                    System.out.println("号停车场，请在这些停车仔和停车场中做选择。");

                                    String tempStr = a.nextLine();                                
                                    tempStr.replaceAll("\r\n", " ");
                                    String[] temp = tempStr.split("\\s{1,}");
                                    int BoyNO = Integer.parseInt(temp[0]);
                                    int LotNO = Integer.parseInt(temp[1]);
                                    int m = Integer.parseInt(temp[2]);
                                    int n = Integer.parseInt(temp[3]);

                                    PM.MakeBoysParking(BoyNO, LotNO, m, n);

                                    break;
                                }

                                case 2: {
                                    Scanner a = new Scanner(System.in);
                                    System.out.print("您已进入让聪明停车仔停车模式，"
                                            + "请输入聪明停车仔编号，他将选择空位最多的停车场随机停车。\r\n"
                                            + "提示：现在有聪明停车仔");
                                    for(int i=0; i<=PM.SmartParkingBoyNum-1; i++) {
                                        System.out.print(i+" ");
                                    }
                                    System.out.println("号。请在这些停车仔之间做选择。");
                                    int DesignedBoyNO = a.nextInt();
                                    Location loc = new Location();
                                    loc = PM.MakeSmartBoysParking(DesignedBoyNO);

                                    if(loc.getLocationM()==999 && loc.getLocationN()==999) {
                                        System.out.println("停车失败！所有停车场都满了。");
                                    }
                                    else
                                        System.out.println("聪明停车仔计算出来第"
                                                +loc.getLotNO()+"号停车场空位最多。\r\n"
                                                +"所以聪明停车仔"+DesignedBoyNO+"号在第"
                                                +loc.getLotNO()+"号停车场的（"+loc.getLocationM()+
                                                ", "+loc.getLocationN()+"）停车成功！");
                                    break;
                                }

                                case 3: {
                                    Scanner a = new Scanner(System.in);
                                    System.out.print("您已进入让超级停车仔停车模式，"
                                            + "请输入超级停车仔编号，他将选择空位率最高的停车场随机停车。\r\n"
                                            + "提示：现在有超级停车仔");
                                    for(int i=0; i<=PM.SuperParkingBoyNum-1; i++) {
                                        System.out.print(i+" ");
                                    }
                                    System.out.println("号。请在这些停车仔之间做选择。");
                                    int DesignedBoyNO = a.nextInt();
                                    Location loc = new Location();
                                    loc = PM.MakeSuperBoysParking(DesignedBoyNO);

                                    if(loc.getLocationM()==999 && loc.getLocationN()==999) {
                                        System.out.println("停车失败！所有停车场都满了。");
                                    }
                                    else
                                        System.out.println("超级停车仔计算出来第"
                                                +loc.getLotNO()+"号停车场空位率最高。\r\n"
                                                +"所以超级停车仔"+DesignedBoyNO+"号在第"
                                                +loc.getLotNO()+"号停车场的（"+loc.getLocationM()+
                                                ", "+loc.getLocationN()+"）停车成功！");

                                    break;
                                }

                                default:
                                    System.out.println("输入不合法，请重新输入。");                                

                            } //end switch choice3
                            break;
                        }
                        default:
                            System.out.println("输入不合法，请重新输入。");                   
                    }
                    break;
                }

                case 2: {
                    System.out.println("欢迎您！停车场主管！下面为您展示停车报表。");
                    int totalScaleNum = 0, totalFreeNum = 0;

                    for(int i=0; i<=PM.ParkingLotNum-1; i++) {
                        System.out.println("停车场编号："+i+"号");
                        int ScaleNum = ParkingManager.Lots[i].M*ParkingManager.Lots[i].N;
                        totalScaleNum = totalScaleNum + ScaleNum;
                        System.out.println("车位数："
                                +ScaleNum+"个");

                        int FreeNum = ParkingManager.Lots[i].getEmptyNumber(i);
                        totalFreeNum = totalFreeNum + FreeNum;
                        System.out.println("空位数："
                                +FreeNum+"个");
                    }
                    System.out.println("Total 车位数："+totalScaleNum+"个");
                    System.out.println("Total 空位数："+totalFreeNum+"个");
                    
                    
                    System.out.println("经理所管理的车：");
                    if(PM.CarNum==0) {
                        System.out.println("该经理没有停车记录！");
                    }
                    else {
                        for(int j=0; j<=PM.CarNum-1; j++) {
                            System.out.println("第"+PM.ManagerCar[j].getLotNO()
                                    +"号停车场的（"+PM.ManagerCar[j].getLocationM()
                                    +", "+PM.ManagerCar[j].getLocationN()
                                    +"）坐标");
                        }
                    }

                    for(int i=0; i<=PM.ParkingBoyNum-1; i++) {
                        System.out.println("普通停车仔"+i+"号所管理的车：");
                        if(ParkingManager.Boys[i].CarNum==0) {
                            System.out.println("该停车仔没有停车记录！");
                        }
                        else {
                            for(int j=0; j<=ParkingManager.Boys[i].CarNum-1; j++) {
                                System.out.println("第"+ParkingManager.Boys[i].ParkingBoyCar[j].getLotNO()
                                        +"号停车场的（"+ParkingManager.Boys[i].ParkingBoyCar[j].getLocationM()
                                        +", "+ParkingManager.Boys[i].ParkingBoyCar[j].getLocationN()
                                        +"）坐标");
                            }
                        }
                    }

                    for(int i=0; i<=PM.SmartParkingBoyNum-1; i++) {
                        System.out.println("聪明停车仔"+i+"号所管理的车：");
                        //System.out.println("carnum "+ParkingManager.SmartBoys[i].CarNum);
                        if(ParkingManager.SmartBoys[i].CarNum==0) {
                            System.out.println("该停车仔没有停车记录！");
                        }
                        else {
                            for(int j=0; j<=ParkingManager.SmartBoys[i].CarNum-1; j++) {
                                System.out.println("第"+ParkingManager.SmartBoys[i].SmartParkingBoyCar[j].getLotNO()
                                        +"号停车场的（"+ParkingManager.SmartBoys[i].SmartParkingBoyCar[j].getLocationM()
                                        +", "+ParkingManager.SmartBoys[i].SmartParkingBoyCar[j].getLocationN()
                                        +"）坐标");
                            }
                        }
                    }

                    for(int i=0; i<=PM.SuperParkingBoyNum-1; i++) {
                        System.out.println("超级停车仔"+i+"号所管理的车：");
                        if(ParkingManager.SuperBoys[i].CarNum==0) {
                            System.out.println("该停车仔没有停车记录！");
                        }
                        else {
                            for(int j=0; j<=ParkingManager.SuperBoys[i].CarNum-1; j++) {
                                System.out.println("第"+ParkingManager.SuperBoys[i].SuperParkingBoyCar[j].getLotNO()
                                        +"号停车场的（"+ParkingManager.SuperBoys[i].SuperParkingBoyCar[j].getLocationM()
                                        +", "+ParkingManager.SuperBoys[i].SuperParkingBoyCar[j].getLocationN()
                                        +"）坐标");
                            }
                        }
                    }                
                    break;
                }                
                default:
                    continue;
                    //System.out.println("输入不合法，请重新输入。");
            } //end switch(choice)        
        }
    }
}
