package com.gwk;


import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HelloController{
    @Value("${server.port}")
    String port;
    @Autowired
    private Aliyun aliyun;
    @RequestMapping("hello")
    String hello() {
       //HelloController.validate(result);
        return "Hello World!"+port+"|" + aliyun.toString();
    }
//BindingResult错误信息

    @RequestMapping("authorize")
    public void authorize(@Valid AuthorizeIn authorize, BindingResult result){
        //有字段错误 判断是否有自断错误
        if(result.hasFieldErrors()){
            //getFieldErrors取出错误集合放在errorList中
            List<FieldError> errorList = result.getFieldErrors();
            //通过断言抛出参数不合法的异常
            //遍历list，，，stream将list转换成流式输出   -> 之后的内容是for循环内的聂荣
            //Assert是一种断言机制，也就是抛出异常的机制，isTrue是判断内部的内容是否为true，不为则抛出
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }
    //检验函数，检验变量
    protected void validate(BindingResult result){
        if(result.hasFieldErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }
}
