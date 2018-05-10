/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class GameElement {
    private Vector<GameBehavior> behaviors;
    private String name;
    private String tag;
    private BufferedImage sprite;
    private boolean enabled;
    private RenderMode render_mode;
    private Vector2 location;
    private Vector2 size;
    
    /**
     * 
     * @param Name The name of the element
     * @param Location The location of the element
     * @param Size The size of the element
     * @param Sprite The texture of the element
     * @param Type The render type of the element
     */
    public GameElement(String Name, Vector2 Location, Vector2 Size, BufferedImage Sprite, RenderMode Type)
    {
        this.behaviors = new Vector<GameBehavior>();
        this.name = Name;
        this.location = Location;
        this.size = Size;
        this.sprite = Sprite; 
        this.render_mode = Type;
        this.enabled = true;
    }

    public void OnUpdate() {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_UPDATE();
        }
    }
    
    public void BevoreRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.BEVORE_RENDER(g);
        }
    }
    
    public void AfterRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.AFTER_RENDER(g);
        }
    }
    
    public void OnKeyDown(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_KEY_DOWN(e);
        }
    }
    
    public void OnKeyUp(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_KEY_RELEASE(e);
        }
    }
    
    public void OnMouseClick(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_CLICK(e);
        }
    }
    
    public void OnMouseDown(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_DOWN(e);
        }
    }
    
    public void OnMouseUp(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_RELEASE(e);
        }
    }
    
    public void OnMouseEnter(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_ENTER(e);
        }
    }
    
    public void OnMouseLeave(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_LEAVE(e);
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RenderMode getRender_mode() {
        return render_mode;
    }

    public void setRender_mode(RenderMode render_mode) {
        this.render_mode = render_mode;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
    
    public void addBehavior(GameBehavior behavior) {
        this.behaviors.add(behavior);
    }

    public Vector<GameBehavior> getBehaviors() {
        return behaviors;
    }
    
    
}

