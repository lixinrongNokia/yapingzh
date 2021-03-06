package cn.yapin.gzh.yilianyunprint;


import java.io.IOException;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 易联云接口工具类
 */

public class Methods {
    /**
     * 易联云颁发给开发者的应用ID 非空值
     */
    public static String CLIENT_ID;

    /**
     * 易联云颁发给开发者的应用secret 非空值
     */
    public static String CLIENT_SECRET;
    /**
     * code
     */
    public static String CODE;
    /**
     * 易联云打印机终端号
     */
    public static String MACHINE_CODE;
    /*终端密钥*/
    public static String MSIGN;
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Methods.class.getResourceAsStream("/yilianyunprint.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CLIENT_ID = properties.getProperty("yilianyunprint.client_id");
        CLIENT_SECRET = properties.getProperty("yilianyunprint.client_secret");
        MACHINE_CODE = properties.getProperty("yilianyunprint.machine_code");
        MSIGN = properties.getProperty("yilianyunprint.msign");
    }

    private Methods() {
    }

    private static class SingleMethods {
        private static final Methods COCOS_MANGER = new Methods();
    }

    public static final Methods getInstance() {
        return SingleMethods.COCOS_MANGER;
    }

    /**
     * 开放式初始化
     *
     * @param client_id
     * @param client_secret
     * @param code
     */
    public void init(String client_id, String client_secret, String code) {
        CLIENT_ID = client_id;
        CLIENT_SECRET = client_secret;
        CODE = code;
    }

    /**
     * 自有初始化
     *
     * @param client_id
     * @param client_secret
     */
    public void init(String client_id, String client_secret) {
        CLIENT_ID = client_id;
        CLIENT_SECRET = client_secret;
    }

    /**
     * 开放应用
     */
    public String getToken() {
        String result = LAVApi.getToken(CLIENT_ID,
                "authorization_code",
                LAVApi.getSin(),
                "all",
                String.valueOf(System.currentTimeMillis() / 1000),
                LAVApi.getuuid());
        JSONObject json = JSONObject.parseObject(result);
        JSONObject body = json.getJSONObject("body");
        /*token = body.getString("access_token");
        refresh_token = body.getString("refresh_token");*/
        return body.toJSONString();
    }

    /**
     * 自有应用服务
     */
    public JSONObject getFreedomToken() {
        String result = LAVApi.getToken(CLIENT_ID,
                "client_credentials",
                LAVApi.getSin(),
                "all",
                String.valueOf(System.currentTimeMillis() / 1000),
                LAVApi.getuuid());
        JSONObject json = JSONObject.parseObject(result);
        JSONObject body = json.getJSONObject("body");
        return body;
    }

    /**
     * 刷新token
     */
    public JSONObject refreshToken(String refresh_token) {
        String result = LAVApi.refreshToken(CLIENT_ID,
                "refresh_token",
                "all",
                LAVApi.getSin(),
                refresh_token,
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
        JSONObject json = JSON.parseObject(result);
        JSONObject body = json.getJSONObject("body");
        /*token = body.getString("access_token");
        refresh_token = body.getString("refresh_token");*/
        return body;
    }

    /**
     * 添加终端授权 开放应用服务模式不需要此接口 ,自有应用服务模式所需参数
     */
    public String addPrinter(String token) {
        return LAVApi.addPrinter(CLIENT_ID,
                MACHINE_CODE,
                MSIGN,
                token,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 极速授权
     */
    public String speedAu(String machine_code, String qr_key) {
        return LAVApi.speedAu(CLIENT_ID,
                machine_code,
                qr_key,
                "all",
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 打印
     */
    public String print(String token, String machine_code, String content, String origin_id) {
        return LAVApi.print(CLIENT_ID,
                token,
                machine_code,
                content,
                origin_id,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 删除终端授权
     */
    public String deletePrinter(String token, String machine_code) {
        return LAVApi.deletePrinter(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 添加应用菜单
     */
    public String addPrintMenu(String token, String machine_code, String content) {
        return LAVApi.addPrintMenu(CLIENT_ID,
                token,
                machine_code,
                content,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 关机重启接口
     */
    public String shutDownRestart(String token, String machine_code, String response_type) {
        return LAVApi.shutDownRestart(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 声音调节
     */
    public String setSound(String token, String machine_code, String response_type, String voice) {
        return LAVApi.setSound(CLIENT_ID,
                token,
                machine_code,
                response_type,
                voice,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 获取机型打印宽度接口
     */
    public String getPrintInfo(String token, String machine_code) {
        return LAVApi.getPrintInfo(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 获取机型软硬件版本接口
     */
    public String getVersion(String token, String machine_code) {
        return LAVApi.getVersion(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 取消所有未打印订单
     */
    public String cancelAll(String token, String machine_code) {
        return LAVApi.cancelAll(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 取消单条未打印订单
     */
    public String cancelOne(String token, String machine_code, String order_id) {
        return LAVApi.cancelOne(CLIENT_ID,
                token,
                machine_code,
                order_id,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 设置logo
     */
    public String setIcon(String token, String machine_code, String img_url) {
        return LAVApi.setIcon(CLIENT_ID,
                token,
                machine_code,
                img_url,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 删除logo
     */
    public String deleteIcon(String token, String machine_code) {
        return LAVApi.deleteIcon(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 打印方式
     */
    public String btnPrint(String token, String machine_code, String response_type) {
        return LAVApi.btnPrint(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 接单拒单设置接口
     */
    public String getOrder(String token, String machine_code, String response_type) {
        return LAVApi.getOrder(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

}
