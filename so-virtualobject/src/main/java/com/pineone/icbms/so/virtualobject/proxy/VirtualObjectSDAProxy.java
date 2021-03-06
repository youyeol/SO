package com.pineone.icbms.so.virtualobject.proxy;

import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualObjectSDAProxy implements VirtualObjectProxy{
    /**
     * SDA에 DeviceID와 Service로 Functionality를 얻는다.
     */

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectSDAProxy.class);

    private ClientService clientService = new ClientService();

    @Autowired
    ContextAddress contextAddress;

    @Override
    public String findFunctionality(String deviceId, String deviceService){
        logger.info(LogPrint.outputInfoLogPrint());
        logger.debug("Device ID = " + deviceId + " DeviceService = " + deviceService);
        //
        String requestUri = contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.SDA_DEVICE;
        JSONObject obj = new JSONObject();
        obj.put("deviceId", deviceId);
        obj.put("deviceService", deviceService);
        String requestData = obj.toString();

        return clientService.requestPostServiceReceiveString(requestUri, requestData);
    }

}
