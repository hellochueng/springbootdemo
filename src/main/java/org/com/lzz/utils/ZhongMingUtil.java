package org.com.lzz.utils;
//
//import com.google.common.collect.Lists;
//import com.wineworld.hkshop.framework.repository.SnowFlakeGenerator;
//import com.wineworld.hkshop.modules.account.entity.Account;
//import com.wineworld.hkshop.modules.account.repository.AccountRepository;
//import com.wineworld.hkshop.modules.pay.wechatpay.utils.MD5Util;
//import com.wineworld.hkshop.security.dao.LoginSubjectDao;
//import com.wineworld.hkshop.security.entity.LoginSubject;
//import net.sf.json.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.util.CharArrayBuffer;
//import org.apache.http.util.EntityUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.*;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///*
//    对接中民接口的util类
// */
//@Component
public class ZhongMingUtil {
//
//    //中民的网址
//    private static String URI = "https://wine-world2018.zhongmin.cn";
//
//    @Value("${spring.zhongMingUrl}")
//    public void setURI(String URI) {
//        ZhongMingUtil.URI = URI;
//    }
//
//    public static String getURI() {
//        return URI;
//    }
//
//    public static String getKey() {
//        return key;
//    }
//
//    //访问中民接口的加密key值
//
//    private static String key = "zhongmin.cn_wine";
//
//    @Value("${spring.zhongMingKey}")
//    public void setKey(String key) {
//        ZhongMingUtil.key = key;
//    }
//
//
//    private static String ZMJFURL = "https://service2.zm123.com/api/jf/";
//
//
//    @Value("${spring.ZMJFURL}")
//    public static void setZMJFURL(String ZMJFURL) {
//        ZhongMingUtil.ZMJFURL = ZMJFURL;
//    }
//
////    static final SnowFlakeGenerator generator = new SnowFlakeGenerator();
//
//    /*
//        判断大陆是否为VIp
//     */
//    public static JSONObject getAccountRankFromMain(String accountName) throws Exception {
//        String url = "https://api.wine-world.com/webservices/ZMRebateWebService.asmx/UserVipInfo";
//        String mainKey = "wineworld_hk.com";
//        String language = "java";
//        String username = aesEncrypt(accountName, mainKey);
//        Map<String, Object> map = new HashMap<>();
//        map.put("language", language);
//        map.put("userName", username);
//        JSONObject jsonObject = JSONObject.fromObject(Post(map, url));
//        return JSONObject.fromObject(jsonObject.get("d"));
//    }
//
//    /*
//        判断大陆是否为VIp
//     */
//    public static JSONObject getMainAccountFee(String accountName,double fee) throws Exception {
//        String url = "https://api.wine-world.com/webservices/ZMRebateWebService.asmx/UpdateHKVipGradeValue";
//        String mainKey = "wineworld_hk.com";
//        String language = "java";
//        String username = aesEncrypt(accountName, mainKey);
//        Map<String, Object> map = new HashMap<>();
//        map.put("language", language);
//        map.put("HKVipGradeValue",aesEncrypt(fee+"", mainKey));
//        map.put("userName", username);
//        JSONObject jsonObject = JSONObject.fromObject(Post(map, url));
//        return JSONObject.fromObject(jsonObject.get("d"));
//    }
//
//    /*
//        中民判断是否存在用户接口
//     */
//    public static Object isExistName(String name) throws Exception {
//        HashMap<String, String> para = new HashMap<>();
//        para.put("_user", name);
//        try {
//            para.put("_sign", ZhongMingUtil.aesEncrypt(name, key) + key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return HttpGet(para, "/UserInfoJava/UserExists");
//    }
//
//    /*
//        中民用户注册接口
//     */
//    public static Object userRegister(String name, String pwd) throws Exception {
//        HashMap<String, String> para = new HashMap<>();
//        para.put("_user", name);
//        para.put("_pwd", pwd);
//        try {
//            para.put("_sign", ZhongMingUtil.aesEncrypt(name, key) + ZhongMingUtil.aesEncrypt(pwd, key) + key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return HttpPsot(para, "/UserInfoJava/Register");
//    }
//
//    /*
//        中民用户修改密码
//     */
//    public static Object userReset(String name, String pwd) throws Exception {
//        HashMap<String, String> para = new HashMap<>();
//        para.put("_user", name);
//        para.put("_pwd", pwd);
//        try {
//            para.put("_sign", ZhongMingUtil.aesEncrypt(name, key) + ZhongMingUtil.aesEncrypt(pwd, key) + key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return HttpPsot(para, "/UserInfoJava/ResetPwd");
//    }
//
//    /*
//        中民判断用户登录接口
//     */
//    public static Object userLogin(String name, String pwd) throws Exception {
//        HashMap<String, String> para = new HashMap<>();
//        para.put("_user", name);
//        para.put("_pwd", pwd);
//        try {
//            para.put("_sign", ZhongMingUtil.aesEncrypt(name, key) + ZhongMingUtil.aesEncrypt(pwd, key) + key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return HttpPsot(para, "/UserInfoJava/Login");
//    }
//
//    /**
//     * 这是封装了访问中民的httpget请求
//     *
//     * @param parameter 参数聚合
//     * @param url       访问路径
//     * @return 返回结果
//     * @throws Exception
//     */
//    public static Object HttpGet(Map<String, String> parameter, String url) throws Exception {
//        String result = null;
//        StringBuffer URL = new StringBuffer(URI + url);
//        URL.append("?");
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建url
//        URIBuilder uriBuilder = new URIBuilder(URL.toString());
//        //创建参数集合
//        List<NameValuePair> params = Lists.newArrayList();
//        //加入参数
//        if (parameter.size() > 0){
//            for (Map.Entry<String, String> entry : parameter.entrySet()) {
//                URL.append(entry.getKey());
//                URL.append("=");
//                URL.append(aesEncrypt(entry.getValue(),key).replace("\r\n","").replace("+","%2B"));
//                URL.append("&");
//            }
//        }
//        URL.delete(URL.length()-1,URL.length());
//        HttpGet get = new HttpGet(URL.toString());
//
//        //设置请求头的编码为utf-8
//        get.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//        //设置返回的报文期望为utf-8编码
//        get.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//        //创建response对象
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(get);
//            if (response != null && response.getStatusLine().getStatusCode() == 200) {
//                HttpEntity entity = response.getEntity();
//                result = EntityUtils.toString(entity, "utf-8");
//            }
//            //返回结果
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 分装httppost请求的方法
//     * @param parameter 请求的参数集合
//     * @param url   请求的地址
//     * @return
//     * @throws Exception
//     */
//    public static Object HttpPsot(Map<String, String> parameter, String url) throws Exception {
//        String result;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        StringBuffer URL = new StringBuffer(URI + url);
//        //创建一个post对象
//        HttpPost post = new HttpPost(URL.toString());
//        //创建一个Entity。模拟一个表单
//        if (parameter.size() > 0) {
//            JSONObject jsonParam = new JSONObject();
//            for (Map.Entry<String, String> entry : parameter.entrySet())
//                jsonParam.put(entry.getKey(), aesEncrypt(entry.getValue(), key));
//            //奇怪的问题 JSONObject字符串超过一定长度会自动加上 \r\n 这里去掉这个
//           jsonParam.put("_sign",jsonParam.get("_sign").toString().replace("\r\n",""));
//            //包装成一个Entity对象
//            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
//            //设置请求的内容
//            post.setEntity(entity);
//        }
//
//        //执行post请求
//        CloseableHttpResponse response = httpClient.execute(post);
//        result = EntityUtils.toString(response.getEntity());
//        response.close();
//        httpClient.close();
//        return result;
//    }
//
//    /*
//        将返回的httpentity对象转换为string对象进行返回
//     */
//    private static String entityToString(HttpEntity entity) throws IOException {
//        String result = null;
//        if (entity != null) {
//            long lenth = entity.getContentLength();
//            if (lenth != -1 && lenth < 2048) {
//                result = EntityUtils.toString(entity, "UTF-8");
//            } else {
//                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
//                CharArrayBuffer buffer = new CharArrayBuffer(2048);
//                char[] tmp = new char[1024];
//                int l;
//                while ((l = reader1.read(tmp)) != -1) {
//                    buffer.append(tmp, 0, l);
//                }
//                result = buffer.toString();
//            }
//        }
//        return result;
//    }
//
//    /*
//        对接中民接口的aes加密算法
//     */
//    public static String aesEncrypt(String str, String key) throws Exception {
//        if (str == null || key == null) return null;
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
//        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
//        return new BASE64Encoder().encode(bytes);
//    }
//
//    /*
//        对接中民接口的aes解密算法
//     */
//    public static String aesDecrypt(String str, String key) throws Exception {
//        if (str == null || key == null) return null;
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
//        byte[] bytes = new BASE64Decoder().decodeBuffer(str);
//        bytes = cipher.doFinal(bytes);
//        return new String(bytes, "utf-8");
//    }
//
//
//    /*
//        设置对应中民账户的积分
//     */
//    public static JSONObject setZhongMingAccountPoint(String accountName,Integer point,String ip,String orderid,Date orderTime) throws Exception {
//        String url = ZMJFURL + "JFOrderHandle";
//        String username = AES.encrypt(accountName);
//        HashMap<String, Object> map = new HashMap<>();
//        Date now = new Date();
//        String signSource = accountName + orderid + CommonUtils.formatDateToString(now,"yyyyMMddHHmmss") + "zm123InsureWine";
//        String sign = MD5Util.MD5Encode(MD5Util.MD5Encode(signSource,"").toUpperCase(),"").toLowerCase();
//        map.put("username", username);
//        map.put("orderid", orderid);
//        map.put("orderdate", CommonUtils.formatDateToString(orderTime,"yyyy-MM-dd HH:mm:ss"));
//        map.put("amount", new BigDecimal(point));
//        map.put("operType", 1);
//        map.put("typeid", 16);
//        map.put("ip",ip);
//        map.put("fromtype",2);
//        map.put("explanation", "香港商城补送历史订单积分");
//        map.put("sign", sign);
//        map.put("opertime", CommonUtils.formatDateToString(now,"yyyy-MM-dd HH:mm:ss"));
//        Object ab = Post(map, url);
//
//        JSONObject jsonObject = JSONObject.fromObject(ab);
//
//        return jsonObject;
//    }
//
//    /*
//        扣除对应中民账户的积分
//     */
//    public static JSONObject setZhongMingAccountPointByGiveUP(String accountName,Integer point,String ip,String orderid,Date orderTime) throws Exception {
//        String url = ZMJFURL + "JFOrderCancelHandle";
//        String username = AES.encrypt(accountName);
//        HashMap<String, Object> map = new HashMap<>();
//        Date now = new Date();
//        String signSource = accountName + orderid + CommonUtils.formatDateToString(now,"yyyyMMddHHmmss") + "zm123InsureWine";
//        String sign = MD5Util.MD5Encode(MD5Util.MD5Encode(signSource,"").toUpperCase(),"").toLowerCase();
//        map.put("username", username);
//        map.put("orderid", orderid);
//        map.put("orderdate", CommonUtils.formatDateToString(orderTime,"yyyy-MM-dd HH:mm:ss"));
//        map.put("amount", new BigDecimal(point));
//        map.put("operType", 0);
//        map.put("typeid", 1);
//        map.put("ip",ip);
//        map.put("fromtype",2);
//        map.put("explanation", "撤单撤回积分");
//        map.put("sign", sign);
//        map.put("opertime", CommonUtils.formatDateToString(now,"yyyy-MM-dd HH:mm:ss"));
//        Object ab = Post(map, url);
//
//        JSONObject jsonObject = JSONObject.fromObject(ab);
//
//        return jsonObject;
//    }
//
//    /*
//        查询对应中民账户的积分
//     */
//    public static JSONObject getUserJf(String accountName) throws Exception {
//        String url = ZMJFURL + "GetUserJF";
//        String username = AES.encrypt(accountName);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username", URLEncoder.encode(username));
//        Object result = Get(map,url);
//        JSONObject jsonObject = JSONObject.fromObject(result);
//        return jsonObject;
//    }
//
//    public static String getJFList(String username,Integer currPage){
//        Integer pageSize=20 ;
//        String resultStr = "";
//        if(currPage==null){
//            currPage = 0;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date operTime = new Date();
//        Date operTime = CommonUtils.formatStringToDate("2018-10-10 10:05:40","yyyy-MM-dd HH:mm:ss");
//        String signTimeStr = sdf.format(operTime);
//        String paramTimeStr = sdf2.format(operTime);
//
//        try {
//            if(username!=null){
//                paramTimeStr = URLEncoder.encode(paramTimeStr,"UTF-8");
//                String sign= com.wineworld.hkshop.utils.MD5Util.md5To32((com.wineworld.hkshop.utils.MD5Util.md5To32(username + signTimeStr + "zm123InsureWine").toUpperCase())).toLowerCase();
//
//                username = URLEncoder.encode(AES.encrypt(username),"UTF-8");
//
//                String param = "?username="+username+"&pageSize="+pageSize+"&currentPage="+currPage+"&type="+0+"&sign="+sign+"&operTime="+paramTimeStr;
//                String url = ZMJFURL + "GetJFlist"+param;
//                String result = null;
//                try {
//                    CloseableHttpClient httpClient = HttpClients.createDefault();
//                    HttpGet get = new HttpGet(url);
//                    //执行post请求
//                    CloseableHttpResponse response = httpClient.execute(get);
//                    result = EntityUtils.toString(response.getEntity());
//                    response.close();
//                    httpClient.close();
//                    return result;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return "where：controller  == > https Request Fail";
//                }
//            }
//        } catch (UnsupportedEncodingException e) {
//            resultStr = "where：controller  == > URLEncoder.encode Fail";
//        }
//        return resultStr;
//    }
//
//    public static Object Post(Map<String, Object> parameter,String URL) throws Exception {
//        String result;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建一个post对象
//        HttpPost post = new HttpPost(URL.toString());
//        //创建一个Entity。模拟一个表单
//        if (parameter.size() > 0) {
//            JSONObject jsonParam = new JSONObject();
//            for (Map.Entry<String, Object> entry : parameter.entrySet())
//                jsonParam.put(entry.getKey(),entry.getValue());
//            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
//            //设置请求的内容
//            post.setEntity(entity);
//        }
//
//        //执行post请求
//        CloseableHttpResponse response = httpClient.execute(post);
//        result = EntityUtils.toString(response.getEntity());
//        response.close();
//        httpClient.close();
//        return result;
//    }
//
//    public static Object Get(Map<String, Object> parameter,String URL) throws Exception {
//        String result;
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建一个post对象
//
//        //创建一个Entity。模拟一个表单
//        StringBuilder params = null;
//        if (parameter.size() > 0) {
//            params = new StringBuilder("?");
//            for (Map.Entry<String, Object> entry : parameter.entrySet())
//                params.append(entry.getKey() + "=" + entry.getValue()+"&");
//            params.deleteCharAt(params.length() - 1);
//        }
//        HttpGet get = new HttpGet(URL.toString() + params.toString());
//        //执行post请求
//        CloseableHttpResponse response = httpClient.execute(get);
//        result = EntityUtils.toString(response.getEntity());
//        response.close();
//        httpClient.close();
//        return result;
//    }
//
//    public static void setNotSetPointAccount(String username){
//        String path = "D:\\hk_shop\\src\\main\\resources\\static\\not.txt";
//        BufferedReader is = null;
//        Map<String,String> m = new HashMap<>();
//        try {
//
//            is = new BufferedReader(new FileReader(path));
//            String str = null;
//            while((str = is.readLine()) != null) {
//                m.put(str.split(":")[0],str.split(":")[1]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        CommonUtils.println(m);
//    }
//
//    /**
//     * 向未加积分的历史用户添加中民积分
//     * @param var1
//     * @param url
//     * @param loginSubjectDao
//     * @param accountRepository
//     */
//    public static void batchSetZMPoint(String var1, String url, LoginSubjectDao loginSubjectDao, AccountRepository accountRepository){
//        File excelFile = new File(url); //替换你文档地址
//        PrintWriter fw = null;
//        try {
//            fw = new PrintWriter("C:\\Users\\Administrator\\Desktop\\not1.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedWriter bw = new BufferedWriter (fw);
//
//        XSSFWorkbook wb = null,wb1 = null;
//        try {
//            wb = new XSSFWorkbook(new FileInputStream(excelFile));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int numberOfSheets = wb.getNumberOfSheets();
//        String str = "";
//        for (int x = 0; x < numberOfSheets; x++) {
//            XSSFSheet sheet = wb.getSheetAt(x);
//            int columnNum = 0;
//            if (sheet.getRow(0) != null) {
//                columnNum = sheet.getRow(0).getLastCellNum()
//                        - sheet.getRow(0).getFirstCellNum();
//            }
//            if (columnNum > 0) {
//                for(int xx = 1; xx < sheet.getLastRowNum() + 1; xx ++){
//                    Row row = sheet.getRow(xx);
//                    String[] singleRow = new String[columnNum];
//                    Cell cell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
//                    Cell cell1 = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
//                    cell1.setCellType(Cell.CELL_TYPE_STRING);
//                    String username = cell.getStringCellValue();
//                    String point = cell1.getStringCellValue();
//
//                    if(CommonUtils.isNotEmpty(username) && CommonUtils.isNotEmpty(point)){
//                        try {
//                            //存在中民 加积分
//                            LoginSubject loginSubject = loginSubjectDao.findByUsername(username);
//                            if(CommonUtils.isEmpty(loginSubject)){
//                                CommonUtils.println(username);
//                            }
//                            Account account = loginSubject.getAccount();
//                            if(CommonUtils.isEmpty(account)){
//                                CommonUtils.println(username);
//                            }
//                            account.setPoint((Integer.parseInt(account.getPoint() == null ? "0" : account.getPoint()) + Integer.parseInt(point))+"");
////                            setZhongMingAccountPoint(username,Integer.parseInt(point),"",var1+generator.generateLongId(),new Date());
//                            accountRepository.save(loginSubject.getAccount());
//                            CommonUtils.println(username + "已加");
////                            if("0".equals(isExistName(username).toString())){
////                                LoginSubject loginSubject = loginSubjectDao.findByUsername(username);
////                                if(CommonUtils.isEmpty(loginSubject)){
////                                    CommonUtils.println(username);
////                                }
////                                Account account = loginSubject.getAccount();
////                                if(CommonUtils.isEmpty(account)){
////                                    CommonUtils.println(username);
////                                }
////                                account.setPoint((Integer.parseInt(account.getPoint() == null ? "0" : account.getPoint()) + Integer.parseInt(point))+"");
////                                setZhongMingAccountPoint(username,Integer.parseInt(point),"",var1+generator.generateLongId(),new Date());
//////                                accountRepository.save(loginSubject.getAccount());
////                                CommonUtils.println(username + "已加");
////                            } else {    //不存在加到xlsx
////                                bw.write(username+":"+point+"\n");
////                                CommonUtils.println(username+":"+ isExistName(username).toString() + "不存在");
////                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        try {
//            bw.flush();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//        miain函数进行方法测试
//     */
//    public static void main(String as[]) throws Exception {
////        getMainAccountFee("13260056691",100.0D);
////        System.out.println(getAccountRankFromMain("13260056691"));
////        System.out.println(userReset("13260056691","123456"));
////        System.out.println(userLogin("13911756860", "123456"));
////        System.out.println(10200 == (Integer)getUserJf("13260056691").get("code"));1000000721825074
////        Date b = new Date();
////        Date a = CommonUtils.formatStringToDate("2018-10-10 14:57:12.722","yyyy-MM-dd HH:mm:ss.SSS");
////        System.out.println(setZhongMingAccountPoint("13260056691",1,"","QJ58808722547713",CommonUtils.formatStringToDate("2018-10-10 14:57:12.722","yyyy-MM-dd HH:mm:ss")));
////        System.out.println(setZhongMingAccountPoint("13260056691",1,"127.0.0.1","101534343845888",CommonUtils.formatStringToDate("2018-10-10 14:57:12.722","yyyy-MM-dd HH:mm:ss")));
////        System.out.println(getUserJf("13260056691"));
////        System.out.println(getUserJf("18223618687"));
////        CommonUtils.println(setZhongMingAccountPointByGiveUP("18688883422",109920,"","ZX"+generator.generateLongId(),new Date()).toString());
////        System.out.println(getAccountRankFromMain("13911756860"));
////        System.out.println(getJFList("18223618687",0));
////        System.out.println(getUserJf("18223618687"));
////        System.out.println(getJFList("15768891141",0));
//
//        //1jAtg6sJDdYYLK1dHU2a69xMMjzHT9L20mvSsrZRXWc=
//        //Stm8OSvMEwEblcUVRjmPY/LdQn2ZK+slXtGtDCfqlnAxmDWHD9OIpX0Q2yv2+rC8gNfdJHLu+NVGGVkqZclmHg==
////        System.out.println(ZhongMingUtil.aesDecrypt("0HnwiVNArA/hPmLDpto4d9xMMjzHT9L20mvSsrZRXWc=", key));
////        System.out.println(ZhongMingUtil.aesDecrypt("jiwBFXY1xYDA2DfdWfbh/3aOCwQ9j6Frr20G8aKoqCMxmDWHD9OIpX0Q2yv2+rC8gNfdJHLu+NVGGVkqZclmHg==", key));
////        System.out.println(ZhongMingUtil.aesDecrypt("1jAtg6sJDdYYLK1dHU2a69xMMjzHT9L20mvSsrZRXWc=", key));
////        System.out.println(ZhongMingUtil.aesDecrypt("Stm8OSvMEwEblcUVRjmPY/LdQn2ZK+slXtGtDCfqlnAxmDWHD9OIpX0Q2yv2+rC8gNfdJHLu+NVGGVkqZclmHg==", key));
////        System.out.println(isExistName("972356881@qq.com"));
////        String user = aesEncrypt("972356889@qq.com",key);
////        System.out.println("_user"+user);
////        System.out.println("_sign"+aesEncrypt(user+key,key));
////        System.out.println(userRegister("172356863@qq.com","123456"));
//        //{"_pwd":"jcXygQKcOON1OhWEFxxjOw==","_user":"SNLhcK1MMnVfZluXBqnVedxMMjzHT9L20mvSsrZRXWc=","_sign":"2V6vzGvHExqkJkebQuJE6Wlz7MpgvT/qm3ZNFBft6l9SrdkWopXfFZAQCNlbTH0qibhdgA1HE6QjMeeevdXXsE6uvg7lBNZD/IfN+2lZWCl22XG6NM5hfEqOBO2mGlpa"}
//        //SNLhcK1MMnVfZluXBqnVedxMMjzHT9L20mvSsrZRXWc=           2V6vzGvHExqkJkebQuJE6Wlz7MpgvT/qm3ZNFBft6l8xmDWHD9OIpX0Q2yv2+rC8gNfdJHLu+NVGGVkqZclmHg==
////        System.out.println(isExistName("13951078424"));
////        System.out.println(userLogin("172356883@qq.com","123456"));
//
////        batchSetZMPoint("ZX");
//        setNotSetPointAccount("");
//    }
}
