package cn.lightina.managebooks.controller;

import cn.lightina.managebooks.pojo.ProcessResult;
import cn.lightina.managebooks.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managebooks")
public class emailController {
    @Autowired
    MailService mailService;


    @RequestMapping(value = "/{validation}/email",
            method = RequestMethod.GET)
    public ProcessResult remindReturn(@PathVariable(value = "validation")Integer val){
        ProcessResult pr=null;
        if(val==null)return new ProcessResult(false);
        if(!val.equals("czctalent")&&!val.equals("czhtalent"))return new ProcessResult(false);
        if(val.equals("czctalent")) {
            /*提醒还书*/
            try {
                mailService.processReturnReminder();
                pr = new ProcessResult(true);
            } catch (Exception e) {
                pr = new ProcessResult(false);
            }
        }else if(val.equals("czhtalent")){
            /*提醒还书*/
            try {
                mailService.processResReminder();
                pr = new ProcessResult(true);
            } catch (Exception e) {
                pr = new ProcessResult(false);
            }
        }
        return pr;
    }



}
