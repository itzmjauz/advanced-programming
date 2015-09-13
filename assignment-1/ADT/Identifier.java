package assignment1;

interface Identifier {
  /** Constructor
    * PRO :
    * The constructor requires a valid non-empty string starting with a letter to be entered
    *
    * POST :
    * local string will be initiliazed
    **/

  /**
    * PRE :
    * No PRE
    *
    * POST :
    * will return a non-empty String
    **/
  public String get();
  /**
    * PRE :
    * none
    *
    * POST :
    * resets the identifier to string
    **/
  public void init(String string);

}
