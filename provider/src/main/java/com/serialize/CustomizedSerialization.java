package com.serialize;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectInput;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by luliru on 2017/9/15.
 */
public class CustomizedSerialization implements Serialization {


    @Override
    public byte getContentTypeId() {
        return 2;
    }

    @Override
    public String getContentType() {
        return "x-application/hessian2";
    }

    @Override
    public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
        return new CustomizedObjectOutput(output);
    }

    @Override
    public ObjectInput deserialize(URL url, InputStream input) throws IOException {
        return new CustomizedObjectInput(input);
    }
}
