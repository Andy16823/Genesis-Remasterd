/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.UI.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javafx.scene.paint.Color;
import javax.swing.JPanel;

/**
 *
 * @author Andy
 */
public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    private Vector<Scene> scenes;
    private Vector<Canvas> uiCanvases;
    private Scene active_scene;
    private GameCallbacks callbacks;
    private boolean bQuit = false;
    private Timer loop;
    
    public Game(GameCallbacks cbs) {
        this.scenes = new Vector<Scene>();
        this.uiCanvases = new Vector<Canvas>();
        this.setBackground(new java.awt.Color(46, 154, 254));
        this.callbacks = cbs;
        this.loop = new Timer(30, this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable(true);
    }
    
    /**
     * Adds a scene to the game
     * @param scene the item to add 
     */
    public void AddScene(Scene scene) {
        this.scenes.add(scene);
    }
    
    /**
     * Adds a canvas to the game
     * @param canvas the canvas to add
     */
    public void AddCanvas(Canvas canvas)
    {
        this.uiCanvases.add(canvas);
    }
    
    /**
     * Loads a scene
     * @param i the scene to load
     */
    public void LoadScene(int i) {
        this.active_scene = this.scenes.elementAt(i);
        this.active_scene.RenderStaticElements();
    }
    
    /**
     * Loads the scene
     * @param name the name of the scene
     * @return true if its loadet, false if scene dosent exist
     */
    public boolean LoadScene(String name)
    {
        for(Scene scene : this.scenes)
        {
            if(scene.getName() == name)
            {
                this.active_scene = scene;
                this.active_scene.RenderStaticElements();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Updates the game
     */
    public void UpdateGame() {
        this.callbacks.CB_ON_UPDATE();
        this.active_scene.OnUpdate();
    }
    
    /**
     * Starts the Loop
     */
    public void TheLoop() {
        this.loop.run();
    }
    
    /**
     * Stops the Loop
     */
    public void Stop() {
        this.bQuit = true;
    }
        
    /**
     * Set the callback functions
     * @param callbacks the callbacks
     */
    public void setCallbacks(GameCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    
    /**
     * Get the callbacks
     * @return 
     */
    public GameCallbacks getCallbacks() {
        return callbacks;
    }
    
    /**
     * Resturn the loop state
     * @return 
     */
    public boolean isbQuit() {
        return bQuit;
    }
    
    /**
     * Render the game and the ui
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if(this.active_scene != null)
        {
            active_scene.RenderScene(g);
        } 
        for(Canvas c : this.uiCanvases)
        {
            if(c.isEnable())
            {
                c.RenderCanvas(g);
            }
        }
    }

    /**
     * Event for KeyTyped
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * Event for KeyPressed
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.callbacks.CB_ON_KEY_DOWN(e);
    }

    /**
     * Event for KeyRelease
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.callbacks.CB_ON_KEY_RELEASE(e);
    }
    
    /**
     * Event for Mouse Click
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_CLICK(e);
        for(Canvas c : this.uiCanvases)
        {
            if(c.Contains(e.getX(), e.getY()))
            {
                c.OnClick(e);
            }
        }
    }

    /**
     * Event for Mouse Pressed
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_DOWN(e);
    }

    /**
     * Event for Mouse Release
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_RELEASE(e);
    }

    /**
     * Event for Mouse Enter
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_ENTER(e);
    }

    /**
     * Event for Mouse Exit
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.callbacks.CB_ON_MOUSE_LEAVE(e);
    }

    /**
     * Event for Mouse Dragged
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Event for MouseMoved
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for(Canvas c : this.uiCanvases)
        {
            if(c.Contains(e.getX(), e.getY()))
            {
                c.Hover(e);
            }
            else {
                c.OnMouseLeave(e);
            }
        }
    }
       
}
