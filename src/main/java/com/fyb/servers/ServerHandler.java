//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                              佛系少年 

package com.fyb.servers;

import com.fyb.protocol.Packet;
import com.fyb.protocol.PacketCodeC;
import com.fyb.protocol.reponse.LoginResponsePacket;
import com.fyb.protocol.reponse.MessageResponsePacket;
import com.fyb.protocol.request.LoginRequestPacket;
import com.fyb.protocol.request.MessageRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf requestByteBufer = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBufer);

        if (packet instanceof LoginRequestPacket){
            System.out.println(new Date()+": 收到客户端登陆请求。。。。");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacke = new LoginResponsePacket();
            loginResponsePacke.setVersion(loginRequestPacket.getVersion());
            if (valid(loginRequestPacket)){
                loginResponsePacke.setSuccess(true);
                System.out.println("登陆成功");
            }else {
                loginResponsePacke.setReason("账号密码登陆失败");
                loginResponsePacke.setSuccess(false);
                System.out.println("登陆失败");
            }
            //登陆响应
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginResponsePacke);
            ctx.channel().writeAndFlush(byteBuf);
        }else if (packet instanceof MessageRequestPacket){
            //客户端发来小心
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() +": 客户端发来消息： "+ messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复{"+ messageRequestPacket.getMessage()+"}");
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),messageResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }else {
            System.out.println("gg");
        }

    }


    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }














}
