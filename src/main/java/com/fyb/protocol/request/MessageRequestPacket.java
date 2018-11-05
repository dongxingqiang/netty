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

package com.fyb.protocol.request;

import com.fyb.protocol.Packet;
import com.fyb.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    public MessageRequestPacket(String message, String toUserId) {
        this.message = message;
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
