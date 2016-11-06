package com.luliru.dubbo;

import com.luna.demo.service.BizException;
import com.luna.demo.service.Member;
import com.luna.demo.service.MemberService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2016/11/5.
 */
public class ThriftCustomer {

    private static Logger logger = LoggerFactory.getLogger(ThriftCustomer.class);

    public static void main(String[] args) throws TException, BizException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:thrift_customer.xml");
        MemberService.Iface memberService = ctx.getBean(MemberService.Iface.class);
        Member member = memberService.findById(1);
        System.out.println( member.getUsername() );
        memberService.create(member);
    }
}
