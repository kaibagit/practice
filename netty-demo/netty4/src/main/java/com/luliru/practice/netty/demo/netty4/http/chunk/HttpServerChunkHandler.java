package com.luliru.practice.netty.demo.netty4.http.chunk;//package com.luliru.netty4.codec.http.chunk;
//
//import com.luliru.netty4.codec.http.ByteBufToBytes;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.codec.http.multipart.Attribute;
//import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
//import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
//import io.netty.handler.codec.http.multipart.InterfaceHttpData;
//import io.netty.util.CharsetUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.Map;
//
//import static io.netty.handler.codec.http.HttpHeaders.Names.*;
//import static io.netty.handler.codec.http.HttpResponseStatus.OK;
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//
///**
// * Created by luliru on 2017/8/10.
// */
//public class HttpServerChunkHandler extends ChannelInboundHandlerAdapter {
//    private static Logger logger  = LoggerFactory.getLogger(HttpServerChunkHandler.class);
//    private ByteBufToBytes reader;
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof HttpRequest) {
//        }
//
//        if (msg instanceof HttpContent) {
//            DefaultHttpResponse res = new DefaultHttpResponse(HTTP_1_1, OK);
//            res.headers().set(HttpHeaders.Names.TRANSFER_ENCODING, HttpHeaders.Values.CHUNKED);
//            ctx.write(res);
//
//            Chunk
//
//            HttpChunk chunk = new DefaultHttpChunk(ChannelBuffers.wrappedBuffer("hello".getBytes(CharsetUtil.UTF_8)));
//            ctx.write(chunk);
//
//            chunk = new DefaultHttpChunk(ChannelBuffers.wrappedBuffer("world".getBytes(CharsetUtil.UTF_8)));
//            ctx.write(chunk);
//
//            ctx.flush();
//        }
//    }
//
//
//
//}
