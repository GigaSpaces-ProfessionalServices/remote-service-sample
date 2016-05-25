package com.gigaspaces.ps.remoteservice.common;

import java.io.Serializable;

/**
 * User: jpletka
 * Date: 5/25/16
 * Time: 12:01 PM
 */
public class CallInfo implements Serializable{

    private long hostId;
    private long slotId;

    public CallInfo(){}

    public CallInfo(long hostId, long slotId){
        this.hostId = hostId;
        this.slotId = slotId;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallInfo callInfo = (CallInfo) o;

        if (hostId != callInfo.hostId) return false;
        return slotId == callInfo.slotId;

    }

    @Override
    public int hashCode() {
        int result = (int) (hostId ^ (hostId >>> 32));
        result = 31 * result + (int) (slotId ^ (slotId >>> 32));
        return result;
    }
}
