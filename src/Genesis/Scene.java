/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Graphics.Lightmap;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Scene {
    private String name;
    private String tag;
    private boolean enabled;
    private Vector2 location;
    private Vector2 size;
    private Vector<GameElement> elements;
    private Vector<Lightmap> Lightmaps;
    private BufferedImage scene_buffer;
    
    /**
     * 
     * @param Name Your name for the scene
     * @param Location the location from the scene
     * @param Size the size from the scene
     */
    public Scene(String Name, Vector2 Size)
    {
        this.name = Name;
        this.location = new Vector2(0,0);
        this.size = Size;
        this.elements = new Vector<GameElement>();
        this.Lightmaps = new Vector<Lightmap>();
    }
    
    /**
     * Render the STATIC elements
     */
    public void RenderStaticElements()
    {
        this.scene_buffer = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scene_buffer.createGraphics();
        for(GameElement e : elements)
        {
            if(e.getRender_mode() == RenderMode.STATIC && e.isEnabled())
            {
                e.BevoreRender(g2d);
                g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(), null);
                e.AfterRender(g2d);
            }
        }
    }
    
    /**
     * Renders the scene
     * @param g 
     */
    public void RenderScene(Graphics g) {
        if(this.scene_buffer != null)
        {
            g.drawImage(scene_buffer, this.location.getX(), this.location.getY(), this.size.getX(), this.size.getY(), null);
            for(GameElement e : this.elements)
            {
                if(e.getRender_mode() == RenderMode.DYNAMIC && e.isEnabled())
                {
                    e.AfterRender(g);
                    g.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(),null);
                    e.BevoreRender(g);
                }
            }
            
            //Lightmap
            for(Lightmap lm : this.Lightmaps)
            {
                if(lm.isEnabled())
                {
                    if(lm.getRenderMode() == RenderMode.DYNAMIC)
                    {
                        lm.RenderLightmap();
                    }
                    g.drawImage(lm.getLightmap(), lm.getLocation().getX(), lm.getLocation().getY(), lm.getSize().getX(), lm.getSize().getY(), null);
                }
            }
            
        }
    }
    
    public void TransformScene(int x, int y)
    {
        this.location.addX(x);
        this.location.addY(y);
        
        for(GameElement e : this.elements)
        {
            e.getLocation().addX(x);
            e.getLocation().addY(y);
        }
        
        for(Lightmap lm : this.Lightmaps)
        {
            lm.getLocation().addX(x);
            lm.getLocation().addY(y);
        }
        
    }
    
    public void RenderLightmaps()
    {
        for(Lightmap lm : this.Lightmaps)
        {
            lm.RenderLightmap();
        }
    }
    
    /**
     * Update the Scene
     */
    public void OnUpdate()
    {
        for(GameElement e : this.elements)
        {
            if(e.isEnabled())
            {
                e.OnUpdate();
            }
        }
    }
    
    public void OnKeyDown(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnKeyDown(e);
            }
        }
    }
    
    public void OnKeyUp(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnKeyUp(e);
            }
        }
    }
    
    public void OnMouseClick(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseClick(e);
            }
        }
    }
    
    public void OnMouseDown(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseDown(e);
            }
        }
    }
    
    public void OnMouseUp(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseUp(e);
            }
        }
    }
    
    public void OnMouseEnter(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseEnter(e);
            }
        }
    }
    
    public void OnMouseLeave(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseLeave(e);
            }
        }
    }
    
    /**
     * Adds a new GameElement
     * @param e the element
     */
    public void AddGameElement(GameElement e)
    {
        this.elements.add(e);
    }
    
    public void AddLightmap(Lightmap lm)
    {
        this.Lightmaps.add(lm);
    }
    
}
