package com.gigaspaces.ps.remoteservice.common;

/**
 * User: jpletka
 * Date: 5/25/16
 * Time: 12:01 PM
 */
public interface Service {

    public CallInfo startNewCall(String callId);
    public boolean releaseCall(String callId);

}
