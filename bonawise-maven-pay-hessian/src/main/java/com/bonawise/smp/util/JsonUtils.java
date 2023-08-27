//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bonawise.smp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static <T> T stringToObject(Class<T> clazz, String data) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException var3) {
            var3.printStackTrace();
            throw new RuntimeException("json格式转java对象报错", var3);
        }
    }

    public static Map<String, Object> stringToMap(String data) {
        try {
            return (Map)objectMapper.readValue(data, Map.class);
        } catch (IOException var2) {
            var2.printStackTrace();
            throw new RuntimeException("json格式转java对象报错", var2);
        }
    }

    public static Map<String, String> objectMapToStringMap(Map<String, Object> map) {
        HashMap data = new HashMap();

        try {
            Iterator var3 = map.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, Object> entry = (Entry)var3.next();
                data.put((String)entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
            }

            return data;
        } catch (NumberFormatException var4) {
            var4.printStackTrace();
            throw new RuntimeException("map类型转换异常", var4);
        }
    }

    public static String objectToString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            throw new RuntimeException("object转为String出错", var2);
        }
    }

    public static String toJson(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof String) {
            return string2Json((String)o);
        } else if (o instanceof Boolean) {
            return boolean2Json((Boolean)o);
        } else if (o instanceof Number) {
            return number2Json((Number)o);
        } else if (o instanceof Map) {
            return map2Json((Map)o);
        } else if (o instanceof Collection) {
            return collection2Json((Collection)o);
        } else if (o instanceof Object[]) {
            return array2Json((Object[])o);
        } else if (o instanceof int[]) {
            return intArray2Json((int[])o);
        } else if (o instanceof boolean[]) {
            return booleanArray2Json((boolean[])o);
        } else if (o instanceof long[]) {
            return longArray2Json((long[])o);
        } else if (o instanceof float[]) {
            return floatArray2Json((float[])o);
        } else if (o instanceof double[]) {
            return doubleArray2Json((double[])o);
        } else if (o instanceof short[]) {
            return shortArray2Json((short[])o);
        } else if (o instanceof byte[]) {
            return byteArray2Json((byte[])o);
        } else if (o instanceof Object) {
            return object2Json(o);
        } else {
            throw new RuntimeException("不支持的类型: " + o.getClass().getName());
        }
    }

    static String string2Json(String s) {
        StringBuffer sb = new StringBuffer(s.length() + 20);
        sb.append('"');

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch(c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '"':
                    sb.append("\\\"");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }

        sb.append('"');
        return sb.toString();
    }

    static String number2Json(Number number) {
        return number.toString();
    }

    static String boolean2Json(Boolean bool) {
        return bool.toString();
    }

    static String collection2Json(Collection c) {
        Object[] arrObj = c.toArray();
        return toJson(arrObj);
    }

    static String map2Json(Map map) {
        if (map.isEmpty()) {
            return "{}";
        } else {
            StringBuffer sb = new StringBuffer(map.size() << 4);
            sb.append('{');
            Set keys = map.keySet();
            Iterator iterator = keys.iterator();

            while(iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                sb.append('"');
                sb.append(key);
                sb.append('"');
                sb.append(':');
                sb.append(toJson(value));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, '}');
            return sb.toString();
        }
    }

    static String array2Json(Object[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                Object o = array[i];
                sb.append(toJson(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String intArray2Json(int[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                int o = array[i];
                sb.append(Integer.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String longArray2Json(long[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                long o = array[i];
                sb.append(Long.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String booleanArray2Json(boolean[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                boolean o = array[i];
                sb.append(Boolean.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String floatArray2Json(float[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                float o = array[i];
                sb.append(Float.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String doubleArray2Json(double[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                double o = array[i];
                sb.append(Double.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String shortArray2Json(short[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                short o = array[i];
                sb.append(Short.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    static String byteArray2Json(byte[] array) {
        if (array.length == 0) {
            return "[]";
        } else {
            StringBuffer sb = new StringBuffer(array.length << 4);
            sb.append('[');

            for(int i = 0; array != null && i < array.length; ++i) {
                byte o = array[i];
                sb.append(Byte.toString(o));
                sb.append(',');
            }

            sb.setCharAt(sb.length() - 1, ']');
            return sb.toString();
        }
    }

    public static String object2Json(Object bean) {
        if (bean == null) {
            return "{}";
        } else {
            Method[] methods = bean.getClass().getMethods();
            StringBuffer sb = new StringBuffer(methods.length << 4);
            sb.append('{');

            for(int i = 0; methods != null && i < methods.length; ++i) {
                Method method = methods[i];

                try {
                    String name = method.getName();
                    String key = "";
                    if (name.startsWith("get")) {
                        key = name.substring(3);
                        String[] arrs = new String[]{"Class"};
                        boolean bl = false;

                        for(int j = 0; arrs != null && j < arrs.length; ++j) {
                            String s = arrs[j];
                            if (s.equals(key)) {
                                bl = true;
                            }
                        }

                        if (bl) {
                            continue;
                        }
                    } else if (name.startsWith("is")) {
                        key = name.substring(2);
                    }

                    if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
                        if (key.length() == 1) {
                            key = key.toLowerCase();
                        } else if (!Character.isUpperCase(key.charAt(1))) {
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        }

                        Object elementObj = method.invoke(bean);
                        sb.append('"');
                        sb.append(key);
                        sb.append('"');
                        sb.append(':');
                        sb.append(toJson(elementObj));
                        sb.append(',');
                    }
                } catch (Exception var11) {
                    throw new RuntimeException("在将bean封装成JSON格式时异常：" + var11.getMessage(), var11);
                }
            }

            if (sb.length() == 1) {
                return bean.toString();
            } else {
                sb.setCharAt(sb.length() - 1, '}');
                return sb.toString();
            }
        }
    }
}
