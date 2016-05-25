package com.gigaspaces.ps.remoteservice.impl;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.ps.remoteservice.common.CallInfo;

import java.io.Serializable;

/**
 * User: jpletka
 * Date: 5/25/16
 * Time: 12:19 PM
 */
@SpaceClass
public class CallHolder implements Serializable {

    String callId;
    long callStartTime;
    CallInfo callInfo;

    public CallHolder(){}

    public CallHolder(String callId){
        this.callId = callId;
    }

    @SpaceId(autoGenerate = false)
    @SpaceRouting
    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(long callStartTime) {
        this.callStartTime = callStartTime;
    }

    public CallInfo getCallInfo() {
        return callInfo;
    }

    public void setCallInfo(CallInfo callInfo) {
        this.callInfo = callInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallHolder that = (CallHolder) o;

        if (callStartTime != that.callStartTime) return false;
        if (!callId.equals(that.callId)) return false;
        return !(callInfo != null ? !callInfo.equals(that.callInfo) : that.callInfo != null);

    }

    @Override
    public int hashCode() {
        int result = callId.hashCode();
        result = 31 * result + (int) (callStartTime ^ (callStartTime >>> 32));
        result = 31 * result + (callInfo != null ? callInfo.hashCode() : 0);
        return result;
    }
}
