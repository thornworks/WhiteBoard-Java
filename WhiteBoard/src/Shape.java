
import java.awt.*;
import java.awt.event.*;

public class Shape extends Component implements Cloneable {

  protected int x1, x2, y1, y2;

  protected boolean dSelected = false;

  private static final int dSelectedBoxSize = 4;

  private Color dXORColor = Color.black;
  private boolean dUseXor = false;

  // takes point objects

  public Shape (int x1, int y1, int x2, int y2) {
    super ();
    
    if (GlobalDebug.isOn) 
      System.out.println ("Shape.Shape()");

    setCoordinates (x1,y1,x2,y2);

    enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    setXOROff ();
  }
/*setCoordinates is modified to set (x1,y1) to be topleft point 
  and (x2,y2) to be the bottomright point of the boundary box.
 */  
  public void setCoordinates (int x1, int y1, int x2, int y2) {
    setSize (Math.abs(x1-x2)+1, Math.abs (y1-y2)+1);
    this.x1=Math.min(x1,x2);
    this.x2=Math.max(x1,x2);
    this.y1=Math.min(y1,y2);
    this.y2=Math.max(y1,y2);
    
    setLocation(this.x1,this.y1);
/*
    if (((x1 > x2) && (y1 > y2)) ||
	((x1 > x2) && (y1 <= y2))) {
      // make sure this.x1 < this.x2
      this.x1 = x2; this.y1 = y2; this.x2 = x1; this.y2 = y1;
    }
    else {
      this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
    }
 */
  }
  
  public void moveBy(int dx, int dy){
      x1+=dx; y1+=dy; x2+=dx; y2+=dy;
      setLocation(x1,y1);
      repaint();
  }

  public Object clone () {
    System.err.println ("Shape.clone(): Internal Error: This method should not be getting called.  All shapes should override it.");
    return new Shape (x1,y1,x2,y2);
  }

  public void setSelectState (boolean selected) {
    dSelected = selected;
  }

  public void toggleSelectState () {
    dSelected = !dSelected;
  }

  public boolean getSelectState () {
    return dSelected;
  }

  public void setXOROn () {
    dXORColor = Color.white;
    dUseXor = true;
  }

  public void setXOROff () {
    dUseXor = false;
  }

  // drawKnobs();

  // abstract isInside (Point p)

  public void paint (Graphics g) {
    
    g.setColor (Color.black);

    if (dUseXor) 
      g.setXORMode (dXORColor);

    super.paint (g);
    
    if (dSelected) {
      g.fillRect (0,0,dSelectedBoxSize,dSelectedBoxSize);
      g.fillRect (0,getHeight()-1-dSelectedBoxSize,dSelectedBoxSize,dSelectedBoxSize);
      g.fillRect (getWidth()-1-dSelectedBoxSize,0,dSelectedBoxSize,dSelectedBoxSize);
      g.fillRect (getWidth()-1-dSelectedBoxSize,
		  getHeight()-1-dSelectedBoxSize,
		  dSelectedBoxSize,
		  dSelectedBoxSize);
    }
  }

  public Dimension getPreferredSize () {
    return new Dimension (Math.abs(x1-x2), Math.abs (y1-y2));
  }

  public Dimension getMinimumSize () {
    return new Dimension (Math.abs(x1-x2), Math.abs (y1-y2));
  }

  public Dimension getMaximumSize () {
    return new Dimension (Math.abs(x1-x2), Math.abs (y1-y2));
  }

  public  AWTEvent coalesceEvents(AWTEvent existingEvent, AWTEvent
				  newEvent) {
    return super.coalesceEvents (existingEvent,newEvent);
  }

  public  void firePropertyChange(String propertyName, Object oldValue,
				  Object newValue) {
    super.firePropertyChange (propertyName, oldValue, newValue);
  }

  public  String paramString() {
    return super.paramString();
  }

  public void processComponentEvent(ComponentEvent e) {
    super.processComponentEvent (e);
  }
  
  public  void processEvent(AWTEvent e) {
    super.processEvent (e);
  }

  public  void processFocusEvent(FocusEvent e) {
    super.processFocusEvent (e);
  }

  public  void processInputMethodEvent(InputMethodEvent e) {
    super.processInputMethodEvent(e);
  }

  public  void processKeyEvent(KeyEvent e) {
    super.processKeyEvent (e);
  }

  public void processMouseEvent(MouseEvent e) {
    e.translatePoint(x1,y1);  
    super.processMouseEvent (e);
  }

  public  void processMouseMotionEvent(MouseEvent e) {
    e.translatePoint(x1,y1);
    super.processMouseMotionEvent(e);
  }
  
}
