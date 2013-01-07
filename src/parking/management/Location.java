/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parking.management;

/**
 *
 * @author Wendy
 */
//停车场的位置坐标类
public class Location {
    private int m;
    private int n;
    private int LotNO;
    
    Location() {
        this.m = -1;
        this.n = -1;
        this.LotNO = -1;
    }
    
    public void setLocation (int m, int n, int LotNO) {
        this.m = m;
        this.n = n;
        this.LotNO = LotNO;
    }
    
    public void setErrorLocation () {
        m = 999;
        n = 999;
        LotNO = 999;
    }
    
    public int getLocationM() {
        return m;
    }
    
    public int getLocationN() {
        return n;
    }
    
    public int getLotNO() {
        return LotNO;
    }
    
}
