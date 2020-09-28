package com.didispace.web;

import com.didispace.Service.TestService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class HelloController {

    private final Logger logger= Logger.getLogger(getClass());

    @Qualifier("eurekaRegistration")

    @Autowired
    private Registration registration;

    @Autowired
    TestService testservice;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/hello/{id}",method = RequestMethod.GET)
    public String index(@PathVariable String id){
        List<ServiceInstance> list=client.getInstances(registration.getServiceId());
        if (list!=null && list.size()>0){
            for (ServiceInstance item : list) {
                logger.info("/hello,host:"+item.getHost()+",service_id:"+item.getServiceId()+",id"+id);
            }
        }
        return "Hello World id"+id;
    }

    @GetMapping(path = "/testget")
    @ResponseBody
    public String TestGetName(@RequestParam(name = "id",required = true) Integer id,String name){
        return id+name;
    }

    @PostMapping("/from")
    @ResponseBody
    public String handleFormUpload(String name, MultipartFile file) throws IOException{
        if (!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            InputStream ins=file.getInputStream();

            //处理上传的内容
            return "success";
        }
        return "failure";
    }

}
