package cn.hanamizu.campushelp.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtil {
    public Map<String, Object> message(boolean status, String msg, String oMsg, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", msg);
        if (!msg.equals("") && o != null) {
            map.put(oMsg, o);
        }
        return map;
    }
    public Map<String, Object> message(boolean status, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", msg);
        return map;
    }
}
