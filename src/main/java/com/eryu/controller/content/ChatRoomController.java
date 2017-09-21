package com.eryu.controller.content;

import com.eryu.common.helper.ParamHelper;
import com.eryu.content.client.ChatRoomClient;
import com.eryu.content.client.MessageClient;
import com.eryu.content.params.BaseChatRoomParams;
import com.eryu.content.params.ChatRoomMessageWarnParams;
import com.eryu.content.params.ChatRoomUpdateParams;
import com.eryu.core.service.content.ChatRoomService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 房间信息API
 */
@RestController
@RequestMapping(value = "/content/chatroom")
//@Secured({"ROLE_ADMIN", "ROLE_CHATROOM"})
public class ChatRoomController implements ParamHelper {

    @Resource
    private ChatRoomService chatRoomService;

    @Resource
    private ChatRoomClient chatRoomClient;

    @Resource
    private MessageClient messageClient;

    /**
     * 在线聊天室列表
     *
     * @param creator     用户ID
     * @param creatorName 用户昵称
     * @param roomName    房间名称
     * @param typeId      类型
     * @return 结果集
     */
    @RequestMapping("/list/online")
    public SimpleResponse listOnline(String creator, String creatorName, String roomName, String typeId) {
        return SimpleResponse.builder().data(chatRoomService.listOnline(creator, creatorName, roomName, typeId)).build();
    }

    /**
     * 聊天室列表
     *
     * @param creator     用户ID
     * @param creatorName 用户昵称
     * @param roomName    房间名称
     * @param typeId      类型
     */
    @RequestMapping("/list")
    public SimpleResponse list(String creator, String creatorName, String roomName, String typeId) {
        return SimpleResponse.builder().data(chatRoomService.list(creator, creatorName, roomName, typeId)).build();
    }

    /**
     * 开启聊天室
     */
    @RequestMapping("/open")
    public SimpleResponse open(String chatRoomId) {
        BaseChatRoomParams params = new BaseChatRoomParams();
        params.setChatRoomId(chatRoomId);
        return chatRoomClient.open(params);
    }

    /**
     * 关闭聊天室
     */
    @RequestMapping("/close")
    public SimpleResponse close(String chatRoomId) {
        BaseChatRoomParams params = new BaseChatRoomParams();
        params.setChatRoomId(chatRoomId);
        return chatRoomClient.close(params);
    }

    /**
     * 置顶聊天室
     */
    @RequestMapping("/top/add")
    public SimpleResponse topAdd(String chatRoomId) {
        BaseChatRoomParams params = new BaseChatRoomParams();
        params.setChatRoomId(chatRoomId);
        return chatRoomClient.topAdd(params);
    }

    /**
     * 取消置顶聊天室
     */
    @RequestMapping("/top/remove")
    public SimpleResponse topRemove(String chatRoomId) {
        BaseChatRoomParams params = new BaseChatRoomParams();
        params.setChatRoomId(chatRoomId);
        return chatRoomClient.topRemove(params);
    }

    /**
     * 修改聊天室
     */
    @RequestMapping("/update")
    public SimpleResponse update(String chatRoomId, String name, String type) {
        ChatRoomUpdateParams params = new ChatRoomUpdateParams();
        params.setChatRoomId(chatRoomId);
        params.setName(name);
        params.setType(type);
        return chatRoomClient.update(params);
    }

    /**
     * 警告聊天室
     */
    @RequestMapping("/warn")
    public SimpleResponse warn(String creator, String content) {
        empty(creator, "房主不能为空！");
        empty(content, "警告内容为空！");
        ChatRoomMessageWarnParams params = new ChatRoomMessageWarnParams();
        params.setReceiver(creator);
        params.setContent(content);
        messageClient.warn(params);
        return SimpleResponse.builder().build();
    }
}
