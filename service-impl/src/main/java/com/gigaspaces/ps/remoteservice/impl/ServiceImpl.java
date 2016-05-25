package com.gigaspaces.ps.remoteservice.impl;

import com.gigaspaces.ps.remoteservice.common.CallInfo;
import com.gigaspaces.ps.remoteservice.common.Service;
import org.openspaces.core.GigaSpace;
import org.openspaces.remoting.RemotingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * User: jpletka
 * Date: 5/25/16
 * Time: 12:17 PM
 */
@RemotingService
public class ServiceImpl implements Service {

    @Autowired
    GigaSpace gigaSpace;

    public CallInfo startNewCall(String callId) {
        CallHolder template = new CallHolder(callId);
        CallHolder savedObj = gigaSpace.read(template);
        if(savedObj != null){
            return savedObj.getCallInfo();
        }
        template.setCallStartTime(System.currentTimeMillis());
        CallInfo thisCallInfo = new CallInfo();
        Random rand = new Random();
        thisCallInfo.setHostId(rand.nextLong());
        thisCallInfo.setSlotId(rand.nextLong());
        template.setCallInfo(thisCallInfo);
        gigaSpace.write(template);
        return thisCallInfo;

    }

    /**
     * Remove the CallHolder from the space, found by callId
     * @param callId
     * @return true if the object existed, or false if it did not
     */
    public boolean releaseCall(String callId) {
        CallHolder savedObj = gigaSpace.takeById(CallHolder.class, callId);
        if(savedObj != null){
            return true;
        }else{
            return false;
        }
    }
}
