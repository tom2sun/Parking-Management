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

@SuppressWarnings("unused")

public class ParkingLot {
    public int[][] parkLot;
    public int M = 0;
    public int N = 0;
    public int LotNO;
    
    //构造一个空的停车场
    ParkingLot(int m, int n, int LotNO) {
        M = m;
        N = n;
        this.LotNO = LotNO;
        parkLot = new int[M][N]; 
        for (int i=0; i<=M-1; i++) {
            for (int j=0; j<=N-1; j++) {
                parkLot[i][j] = 0;
            }
        }
    }   
    
    //停车，1表示占用
    public boolean parkCar(int m, int n, int LotNO) {
        //System.out.println(this.parkLot[m][n]);
        
        if(this.parkLot[m][n]==0) { //如果没被占用，则停车
            this.parkLot[m][n] = 1;
            return true;
            }
        else //被占用了，停车失败
            return false;
    }
    
    //取车，0表示空位
    public boolean takeCar(int m, int n, int LotNO) {
        try {
            this.parkLot[m][n] = 0;
        }
        catch (Exception e) {
            return false;
        }
        return true;        
    }
    
    public int getEmptyNumber(int LotNO) {
        int emptyNumber = 0;
        for (int i=0; i<=this.M-1; i++) {
            for (int j=0; j<=this.N-1; j++) {
                if(this.parkLot[i][j] == 0)
                    emptyNumber++;
            }
        }
        return emptyNumber;
    }
    
    public double getEmptyRate(int LotNO) {
        double emptyNumber = 0.0;
        for (int i=0; i<=this.M-1; i++) {
            for (int j=0; j<=this.N-1; j++) {
                if(this.parkLot[i][j] == 0)
                    emptyNumber++;
            }
        }
        return emptyNumber/(M*N);
    }
    
}
