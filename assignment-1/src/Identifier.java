package assignment1;

interface Identifier {
  /**
    * PRO :The constructor requires a valid non-empty string starting with a letter to be entered
    * PRE : local sstring will be initiliazed
    *
    * PRE : No PRE
    * PRO : will return a non-empty String
    **/
  public String get();
  /**
    * PRE : none
    * OOST : resets the identifier to string
    **/
  public void init(String string);

}
