#ifndef NEBULA_SDK_H
#define NEBULA_SDK_H

typedef enum {
    eNebulaMsg_Push,        /// 推送 (路由器只需关心这一类消息)
    eNebulaMsg_Presence,    /// Presence
    eNebulaMsg_Sms,         /// 网络短信
    eNebulaMsg_Mms,         /// 网络彩信
    eNebulaMsg_Max,
} NebulaMsgType;

#ifdef __cplusplus
extern "C" {
#endif

    /** 初始化网络连接
     * @device_tag: 硬件信息 (手机端为IMEI)
     * @verify_pwd: 鉴权密码 (手机端为SN)
     * @host: 服务端域名, 例如: https://p.meizu.com (结尾不要带"/", 前面要加上https)
     * @file: 用于读取/存储持久化数据的文件名, 要求目录存在, 且有创建文件权限。
     * @return: 如果文件打开成功, 返回0; 否则返回-1, 文件打开失败原因记录在错误码errno中.
     * @只能调用一次, 重复调用无效并返回-1.
     */
    int NebulaInit(const char* device_tag, const char* host, const char* file);

    /** 设置订阅列表
     *
     *
     */
    void NebulaSubScribe(int count, const char** app_name);

    /** 消息处理回调函数
     * @app: App名
     * @msg: 消息内容
     */
    typedef void (*NebulaMsgCallback)(const char* app, const char* msg);

    /** 注册消息处理回调函数
     * @type: 消息类型
     * @cb: 回调函数
     * @return: 旧的回调函数
     * @一次只能注册一个回调函数, 重复注册会覆盖.
     */
    NebulaMsgCallback NebulaRegister(NebulaMsgType type, NebulaMsgCallback cb);

    /** 启动
     *
     */
    int NebulaStart();

#ifdef __cplusplus
}
#endif

#endif //NEBULA_SDK_H

