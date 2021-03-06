
/** The GlobalDebug class contains a data member which indicates whether
    or not global debugging information should be generated.  This data
    member is not protected by any access control.  Any object can either
    turn the global debugging on or off.  Global debugging will typically
    be set to on or off at the beginning of a program, and most objects
    that are interested in generating debugging information will check
    the state of this data member to determine if they should output
    debugging messages. */

public class GlobalDebug {

  public static boolean isOn = false;
  
}
