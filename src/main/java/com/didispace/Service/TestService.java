package com.didispace.Service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    /**
     * serviceid
     */
    public String serviceid;
    /**
     * servicename
     */
    public String servicename;

    public String getServiceid(){
        return serviceid;
    }

    public String getservicename(){
        return servicename;
    }

    public void setServiceid(String _serviceid){
        serviceid=_serviceid;
    }

    public void setservicename(String _servicename){
        servicename=_servicename;
    }
}
