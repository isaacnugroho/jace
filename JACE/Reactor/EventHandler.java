/*************************************************
 *
 * = PACKAGE
 *    JACE.Reactor
 *
 * = FILENAME
 *    EventHandler.java
 *
 *@author Prashant Jain
 *
 *************************************************/
package JACE.Reactor;

import JACE.ASX.TimeValue;

/**
 *     Provides an abstract interface for handling timer events.
 * <P>
 *     Classes implementing this interface handle a timer's
 *     expiration.
 * <P>
 * Users of C++ ACE will notice that this defines a substantially
 * smaller interface than the C++ counterpart.  Signal events are
 * absent due to the complete absence of this feature from Java itself.
 * Moreover, at this point
 * there is still some question regarding whether or not the I/O
 * portion will make any sense or fit into the Java model for I/O.
 *
 *@see JACE.Reactor.TimerQueue
 *@see JACE.Reactor
 */
public interface EventHandler
{
  /** 
   * Called when timer expires.
   *@param tv Time Value for which timer was set
   *@param obj An arbitrary object that was passed to the Timer Queue
   * (Asynchronous Completion Token)
   */
  public int handleTimeout (TimeValue tv, Object obj);
}

// Note that more methods will be added as needed
