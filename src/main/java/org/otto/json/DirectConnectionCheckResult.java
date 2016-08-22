package org.otto.json;

/**
 * Created by tomek on 2016-08-20.
 */
public class DirectConnectionCheckResult {
    private int depSid;
    private int arrSid;
    private boolean directBusRoute;

    public DirectConnectionCheckResult(int depSid, int arrSid, boolean hasDirectBusRoute) {
        this.depSid = depSid;
        this.arrSid = arrSid;
        this.directBusRoute = hasDirectBusRoute;
    }

    public int getDepSid() {
        return depSid;
    }

    public void setDepSid(int depSid) {
        this.depSid = depSid;
    }

    public int getArrSid() {
        return arrSid;
    }

    public void setArrSid(int arrSid) {
        this.arrSid = arrSid;
    }

    public boolean isDirectBusRoute() {
        return directBusRoute;
    }

    public void setDirectBusRoute(boolean directBusRoute) {
        this.directBusRoute = directBusRoute;
    }

    @Override
    public String toString() {
        return "DirectConnectionCheckResult{" +
                "depSid=" + depSid +
                ", arrSid=" + arrSid +
                ", directBusRoute=" + directBusRoute +
                '}';
    }
}