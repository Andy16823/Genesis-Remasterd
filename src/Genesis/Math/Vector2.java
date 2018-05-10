/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Math;

/**
 *
 * @author Andy
 */
public class Vector2 {
    private int x;
    private int y;
    
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void addX(int Value)
    {
        this.x += Value;
    }
    
    public void addY(int Value)
    {
        this.y += Value;
    }
}
