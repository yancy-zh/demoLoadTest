package com.example.demoloadtest;
import com.example.demoloadtest.TestIfcEventHandler;

import android.app.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicTestSupport extends Activity {
    static final String LOG_TAG = "BasicTestSupport";
    public final int TESTSTATUS_NONE = 1;
    public final int TESTSTATUS_EXECUTING = 2;
    public final int TESTSTATUS_DONE = 3;
    TestStatus mTestStatus;
    EvtHandlerXXX mEventHandler;
    //todo check the functionality of the test


    public class TestStatus {
        public int mStatus;
        public Matcher mMatcher;
        public boolean mFound;
        public int mFoundIndex;
        public String mFoundText;
        public TestStatus(){
            mStatus = TESTSTATUS_NONE;

        }
    }

    public class EvtHandlerXXX extends TestIfcEventHandler {
        Pattern[] mPattern;
        public EvtHandlerXXX(){

        }
        public void setRegExList(String[] regExList) {
            int i;

            mPattern = new Pattern[regExList.length];
            for (i = 0; i < regExList.length; i++) {
                mPattern[i] = Pattern.compile(regExList[i]);
            }
        }

        @Override
        public void onStdOut(String line) {
            for (int i = 0; i < mPattern.length; i++) {
                Matcher matcher = mPattern[i].matcher(line);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    mTestStatus.mMatcher = matcher;
                    mTestStatus.mFound = true;
                    mTestStatus.mFoundIndex = i;
                    // added an attribute to output the matched lines
                    mTestStatus.mFoundText = line;
                }
            }
        }

        public void onExecDone(int arg0) {
            synchronized (mTestStatus) {
                mTestStatus.mStatus = TESTSTATUS_DONE;
                mTestStatus.notifyAll();
            }
        }
    }
}
