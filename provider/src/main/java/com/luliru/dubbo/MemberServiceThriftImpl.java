package com.luliru.dubbo;

import org.apache.thrift.TException;

import com.luna.demo.service.BizException;
import com.luna.demo.service.Member;
import com.luna.demo.service.MemberService;

/**
 * Created by luliru on 2016/11/5.
 */
public class MemberServiceThriftImpl implements MemberService.Iface {

    @Override
    public Member findById(long id) throws TException {
        System.out.println("findById");
        Member member = new Member();
        member.setId(id);
        member.setUsername("hello");
        member.setStatus(0);
        return member;
    }

    @Override
    public void create(Member new_member) throws BizException, TException {
        System.out.println("create");
    }
}
