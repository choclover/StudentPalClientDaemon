package com.studentpal.model.rules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.studentpal.app.ResourceManager;
import com.studentpal.app.RuleScheduler;
import com.studentpal.app.RuleScheduler.RuleExecutor;
import com.studentpal.model.AccessCategory;
import com.studentpal.model.exception.STDException;
import com.studentpal.util.logger.Logger;

public class AccessRule {
  private static final String TAG = "@@ AccessRule";

  /*
   * Constants
   */
  public static final int ACCESS_DENIED = 0x01;
  public static final int ACCESS_PERMITTED = 0x02;
  
  /*
   * Member fields
   */
  private int access_type = ACCESS_DENIED;  
  
  private Recurrence      _recurrence;
  private AccessCategory  _adhereCate;
  private List<TimeRange> _timeRangeList;  

  private RuleExecutor    _ruleExecutor;

  public boolean isOccurringToday() {
    return _recurrence!=null && _recurrence.isOccurringToday();
  }
  public void setRecurrence(Recurrence recur) {
    this._recurrence = recur;
  }
  
  public AccessCategory getAdhereCategory() {
    return _adhereCate;
  }
  
  public int getActionInTimeRange() {
    return access_type; 
  }
  
  public int getActionOutofTimeRange() {
    return access_type==ACCESS_DENIED ? ACCESS_PERMITTED : ACCESS_DENIED;
  }
  
  public void setRuleExcutor(RuleExecutor executor) {
    this._ruleExecutor = executor;
  }
  public RuleExecutor getRuleExcutor() {
    return this._ruleExecutor;
  }
  
  public void addTimeRange(TimeRange range) {
    if (range == null || range.isValid()==false) {
      Logger.w(TAG, "Timerange is NULL or NOT valid!");
      return;
    }
    
    if (_timeRangeList == null) {
      _timeRangeList = new ArrayList<TimeRange>();  
    }
    _timeRangeList.add(range);
  }
  
  public List<TimeRange> getTimeRangeList() {
    return _timeRangeList;
  }

}
