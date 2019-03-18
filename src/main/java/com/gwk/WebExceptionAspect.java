package com.gwk;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
@Component
public class WebExceptionAspect {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(WebExceptionAspect.class);

    //凡是注解了RequestMapping的方法都被拦截   pointcut切入点，也就是定义切入规则，到底切入拦截谁
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {
    }

    /**
     * 拦截web层异常，记录异常日志，并返回友好信息到前端 目前只拦截Exception，是否要拦截Error需再做考虑
     *
     * @param e
     *            异常对象
     */
    //抛出异常时执行
    // 声明e时指定的类型会限制目标方法必须抛出指定类型的异常，此处将e的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
    //pintcut属性值可以写类限定，也可以写方法限定   切入位置
    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")
    //
//        public void handleThrowing(Exception e) {
//        e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
//        logger.error("发现异常！" + e.getMessage());
//        logger.error(JSON.toJSONString(e.getStackTrace()));
//        //这里输入友好性信息
//        writeContent("出现异常");
//    }

    public void afterThrowing(Exception e) throws Throwable {
        logger.debug("exception 来了！");
        System.out.println("exception 来了！");
        //isNotBlank判断字符串是否非空，非空格、制表、换行符号
        if(StringUtils.isNotBlank(e.getMessage())){
            writeContent(e.getMessage());
        }else{
            writeContent("参数错误！");
        }

    }



    /**
     * 将内容输出到浏览器
     *
     * @param content
     *            输出内容
     */
    private void writeContent(String content) {
//        返回前端页面
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;//向文本输出流打印对象的格式化表示形式
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(content);
        writer.flush();
        writer.close();
    }
}
