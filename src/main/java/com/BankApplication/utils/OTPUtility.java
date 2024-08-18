package com.BankApplication.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OTPUtility {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public Integer sendOTP(){
        logger.info("inside sendOTP() method of {}",this.getClass());
        return 123456;
    }

}
