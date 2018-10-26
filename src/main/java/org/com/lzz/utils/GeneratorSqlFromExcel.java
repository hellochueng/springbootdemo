package org.com.lzz.utils;


//import com.wineworld.hkshop.exception.GenericException;
//import com.wineworld.hkshop.modules.saleproduct.entity.SaleProductType;
//import jxl.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @Author pengzhiqun
 * @Date 2017/3/6.11:56
 * @Discription 批量导入工具类
 */
public class GeneratorSqlFromExcel {
    /**
     * 导入报表Excel数据，生成用户表的数据库导入语句
     *
     * @param formFile
     * @return list ArrayList
     * @throws Exception
     */
//    public static ArrayList<String[]> generateSql(MultipartFile formFile)
//            throws Exception {
//        FileInputStream in = (FileInputStream) formFile.getInputStream();
//        Workbook wb = null;   //jxl的核心对象
//        ArrayList<String[]> list = new ArrayList<String[]>();
//
//        try {
//            if (formFile == null) {
//                throw new GenericException("fail", "文件为空！");
//            }
//            wb = Workbook.getWorkbook(in);          //从输入流中获取WorkBook对象，加载选中的excel文件
//
//            Sheet sheet[] = wb.getSheets();//通过workbook对象获取sheet对象，此时sheet对象是一个数组，就是一张excel工作簿，可能有多张工作簿
//            if (sheet != null) {
//                for (int i = 0; i < sheet.length; i++) {    //循环工作簿，一张工作簿有多条数据
//                    int count = i + 1;
//                    for (int j = 1; j < sheet[i].getRows(); j++) {//使用sheet对象用来获取每1行，从1开始表示要去掉excel的标题
//                        String[] valStr = new String[28];//用数组来存放每一行的数据，20表示每一行的数据不能超过28，可以<=28
//                        for (int k = 0; k < sheet[i].getColumns(); k++) {//使用sheet对象用来获取每1列，从0开始表示从第1列开始
//                            Cell cell = sheet[i].getCell(k, j);//k表示列的号，j表示行的号
//                            String content = "";
//                            //如果是日期
//                            if (cell.getType() == CellType.DATE) {
//                                DateCell dateCell = (DateCell) cell;
//                                java.util.Date importdate = dateCell.getDate();/**如果excel是日期格式的话需要减去8小时*/
//                                long eighthour = 8 * 60 * 60 * 1000;
//                                SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                /**在当前日期上减8小时*/
//                                long time = importdate.getTime() - eighthour;
//                                java.util.Date date = new java.util.Date();
//                                date.setTime(time);
//                                content = simpledate.format(date);
//                            } else {
//                                String tempcontent = (cell.getContents() == null ? "" : cell.getContents());
//                                content = tempcontent.trim();
//                            }
//                            valStr[k] = content;//将excel获取到的值赋值给String类型的数组
//
//                        }
//                        list.add(valStr);
//                    }
//                }
//            }
//
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            if (wb != null) {
//                wb.close();
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 导入报表Excel数据，生成用户表的数据库导入语句
//     *
//     * @param formFile
//     * @return list ArrayList
//     * @throws Exception
//     */
////    public static ArrayList<String[]> generateSql(MultipartFile formFile, SaleProductType saleProductType)
////            throws Exception {
////        FileInputStream in = (FileInputStream) formFile.getInputStream();
////        Workbook wb = null;   //jxl的核心对象
////        ArrayList<String[]> list = new ArrayList<String[]>();
////
////        try {
////            if (formFile == null) {
////                throw new GenericException("fail", "文件为空！");
////            }
////            wb = Workbook.getWorkbook(in);          //从输入流中获取WorkBook对象，加载选中的excel文件
////
////            Sheet sheet[] = wb.getSheets();//通过workbook对象获取sheet对象，此时sheet对象是一个数组，就是一张excel工作簿，可能有多张工作簿
////            String[] names = wb.getSheetNames();
////
////            if (saleProductType == SaleProductType.OVERSEA && !names[0].contains("海外直购")) {
////                throw new GenericException("fail", "不符合海外直购销售商品模板，请确保选择的excel是海外直购商品数据文档，并将excel中的sheet工作表重命名为海外直购");
////            }
////            if (saleProductType == SaleProductType.FUTURE && !names[0].contains("期酒")) {
////                throw new GenericException("fail", "不符合期酒销售商品模板，请确保选择的excel是期酒商品数据文档，并将excel中的sheet工作表重命名为期酒");
////            }
////            if (saleProductType == SaleProductType.SPOT && !names[0].contains("现货")) {
////                throw new GenericException("fail", "不符合现货销售商品模板，请确保选择的excel是现货商品数据文档，并将excel中的sheet工作表重命名为现货");
////            }
////
////
////            if (sheet != null) {
////                //获取标题
////                int length = sheet[0].getColumns();
////                String[] title = new String[length];
////                for (int t = 0; t < length; t++) {
////                    Cell cell = sheet[0].getCell(t, 0);
////                    String tempcontent = (cell.getContents() == null ? "" : cell.getContents());
////                    title[t] = tempcontent;
////                }
////                //校验标题
////                checkTitleIsTrue(title, saleProductType);
////                for (int i = 0; i < sheet.length; i++) {    //循环工作簿，一张工作簿有多条数据
////                    int count = i + 1;
////                    for (int j = 1; j < sheet[i].getRows(); j++) {//使用sheet对象用来获取每1行，从1开始表示要去掉excel的标题
////                        String[] valStr = new String[28];//用数组来存放每一行的数据，20表示每一行的数据不能超过28，可以<=28
////                        for (int k = 0; k < sheet[i].getColumns(); k++) {//使用sheet对象用来获取每1列，从0开始表示从第1列开始
////                            Cell cell = sheet[i].getCell(k, j);//k表示列的号，j表示行的号
////                            String content = "";
////                            //如果是日期
////                            if (cell.getType() == CellType.DATE) {
////                                DateCell dateCell = (DateCell) cell;
////                                java.util.Date importdate = dateCell.getDate();/**如果excel是日期格式的话需要减去8小时*/
////                                long eighthour = 8 * 60 * 60 * 1000;
////                                SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                                /**在当前日期上减8小时*/
////                                long time = importdate.getTime() - eighthour;
////                                java.util.Date date = new java.util.Date();
////                                date.setTime(time);
////                                content = simpledate.format(date);
////                            } else {
////                                String tempcontent = (cell.getContents() == null ? "" : cell.getContents());
////                                content = tempcontent.trim();
////                            }
////                            valStr[k] = content;//将excel获取到的值赋值给String类型的数组
////
////                        }
////                        list.add(valStr);
////                    }
////                }
////            }
////
////            return list;
////        } catch (Exception e) {
////            e.printStackTrace();
////            throw e;
////        } finally {
////            if (wb != null) {
////                wb.close();
////            }
////            if (in != null) {
////                try {
////                    in.close();
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//
//
//    public static ArrayList<String[]> generateSql(MultipartFile formFile, String type) throws Exception{
//        FileInputStream in = (FileInputStream) formFile.getInputStream();
//        Workbook wb = null;   //jxl的核心对象
//        ArrayList<String[]> list = new ArrayList<String[]>();
//
//        try{
//            if (formFile == null) {
//                throw new GenericException("fail", "文件为空！");
//            }
//            wb = Workbook.getWorkbook(in);          //从输入流中获取WorkBook对象，加载选中的excel文件
//
//            Sheet sheet[] = wb.getSheets();//通过workbook对象获取sheet对象，此时sheet对象是一个数组，就是一张excel工作簿，可能有多张工作簿
//            String[] names = wb.getSheetNames();
//
//            if (type.equals("WineWorldProduct") && !names[0].contains("主站商品")) {
//                throw new GenericException("fail", "不符合主站商品模板，请确保选择的excel是主站商品数据文档，并将excel中的sheet工作表重命名为主站商品");
//            }
//            if (type.equals("BatchPrice") && !names[0].contains("批量修改商品价格")) {
//                throw new GenericException("fail", "不符合批量修改商品价格，请确保选择的excel是批量修改商品价格文档，并将excel中的sheet工作表重命名为批量修改商品价格");
//            }
//            if (type.equals("OVERSEA") && !names[0].contains("海外直购")) {
//                throw new GenericException("fail", "不符合海外直购销售商品模板，请确保选择的excel是海外直购商品数据文档，并将excel中的sheet工作表重命名为海外直购");
//            }
//            if (type.equals("FUTURE")&& !names[0].contains("期酒")) {
//                throw new GenericException("fail", "不符合期酒销售商品模板，请确保选择的excel是期酒商品数据文档，并将excel中的sheet工作表重命名为期酒");
//            }
//            if (type.equals("SPOT")&& !names[0].contains("现货")) {
//                throw new GenericException("fail", "不符合现货销售商品模板，请确保选择的excel是现货商品数据文档，并将excel中的sheet工作表重命名为现货");
//            }
//
//            if (sheet != null) {
//                //获取标题
//                int length = sheet[0].getColumns();
//                String[] title = new String[length];
//                for (int t = 0; t < length; t++) {
//                    Cell cell = sheet[0].getCell(t, 0);
//                    String tempcontent = (cell.getContents() == null ? "" : cell.getContents());
//                    title[t] = tempcontent;
//                }
//                //校验标题
//                checkTitle(title, type);
//
//                for (int i = 0; i < sheet.length; i++) {    //循环工作簿，一张工作簿有多条数据
//                    int count = i + 1;
//                    for (int j = 1; j < sheet[i].getRows(); j++) {//使用sheet对象用来获取每1行，从1开始表示要去掉excel的标题
//                        String[] valStr = new String[28];//用数组来存放每一行的数据，20表示每一行的数据不能超过28，可以<=28
//                        for (int k = 0; k < sheet[i].getColumns(); k++) {//使用sheet对象用来获取每1列，从0开始表示从第1列开始
//                            Cell cell = sheet[i].getCell(k, j);//k表示列的号，j表示行的号
//                            String content = "";
//                            //如果是日期
//                            if (cell.getType() == CellType.DATE) {
//                                DateCell dateCell = (DateCell) cell;
//                                java.util.Date importdate = dateCell.getDate();/**如果excel是日期格式的话需要减去8小时*/
//                                long eighthour = 8 * 60 * 60 * 1000;
//                                SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                /**在当前日期上减8小时*/
//                                long time = importdate.getTime() - eighthour;
//                                java.util.Date date = new java.util.Date();
//                                date.setTime(time);
//                                content = simpledate.format(date);
//                            } else {
//                                String tempcontent = (cell.getContents() == null ? "" : cell.getContents());
//                                content = tempcontent.trim();
//                            }
//                            valStr[k] = content;//将excel获取到的值赋值给String类型的数组
//
//                        }
//                        list.add(valStr);
//                    }
//                }
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            if (wb != null) {
//                wb.close();
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    public static void checkTitleIsTrue(String[] title, SaleProductType saleProductType) throws GenericException {
//        if (saleProductType == SaleProductType.OVERSEA) {
//            if (title.length != 27) {
//                throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的海外直购导入模板");
//            }
//        }
//        if (saleProductType == SaleProductType.FUTURE) {
//            if (title.length != 18) {
//                throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的期酒导入模板");
//            }
//        }
//        if (saleProductType == SaleProductType.SPOT) {
//            if (title.length != 18) {
//                throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的现货导入模板");
//            }
//        }
//        //region 模板序号
////        0	酒款名称（简体）
////        1	酒款名称（英文）
////        2	适饮温度
////        3	所属品牌（简体）
////        4	所属产区（简体）
////        5	所属酒庄（简体）
////        6	酒款级别（简体）
////        7	葡萄品种（简体）
////        8	图片名称
////        9	酒款综述（简体）
////        10	酒款综述（英文）
////        11	年份
////        12	年产量
////        13	库存
////        14	销售单价
////        15	容量
////        16	酒的类别
////        17	sku（酒业系统商品id）
////        18	起售数量
////        19	叠加数量
////        20	供应商编号
////        21	供应商商品名称
////        22	供应商价格
////        23	供应商最少购货量
////        24	销售库存阈值
////        25	供应商地址
////        26	供应商电话
//        //endregion
//        ArrayList<String> list = new ArrayList<String>() {
//            {
//                add("酒款名称（简体）");
//                add("酒款名称（英文）");
//                add("适饮温度");
//                add("所属品牌（简体）");
//                add("所属产区（简体）");
//                add("所属酒庄（简体）");
//                add("酒款级别（简体）");
//                add("葡萄品种（简体）");
//                add("图片名称");
//                add("酒款综述（简体）");
//                add("酒款综述（英文）");
//                add("年份");
//                add("年产量");
//                add("库存");
//                add("销售单价");
//                add("容量");
//                add("酒的类别");
//                add("sku（酒业系统商品id）");
//                add("起售数量");
//                add("叠加数量");
//                add("供应商编号");
//                add("供应商商品名称");
//                add("供应商价格");
//                add("供应商最少购货量");
//                add("销售库存阈值");
//                add("供应商地址");
//                add("供应商电话");
//            }
//        };
//        for (int i = 0; i < title.length; i++) {
//            if (!title[i].equals(list.get(i))) {
//                throw new GenericException("fail", "导入数据文件列错误，错误列：" + title[i] + "列名称错误或位置错误");
//            }
//        }
//    }
//
//    private static void checkTitle(String[] title,String type)throws GenericException{
//
//        switch (type){
//            case "BatchPrice":
//                if (title.length != 2) {
//                    throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的批量修改商品价格导入模板");
//                }
//                break;
//            case "WineWorldProduct":
//                if (title.length != 3) {
//                    throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的主站商品导入模板");
//                }
//                break;
//            case "OVERSEA":
//                if (title.length != 27) {
//                    throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的海外直购导入模板");
//                }
//                break;
//            case "FUTURE":
//                if (title.length != 18) {
//                    throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的期酒导入模板");
//                }
//                break;
//            case "SPOT":
//                if (title.length != 18) {
//                    throw new GenericException("fail", "导入数据文件列列数错误，请使用最新的现货导入模板");
//                }
//                break;
//            default:
//                throw new GenericException("fail", "导入模板模板错误");
//        }
//        ArrayList<String> list = new ArrayList<String>();
//        if(type.equals("WineWorldProduct")){
//            list.add("主站商品Id");
//            list.add("主站商品名称(中文)");
//            list.add("香港商城商品名称(中文)");
//        }else if(type.equals("BatchPrice")){
//            list.add("销售商品ID");
//            list.add("最新销售价格");
//        }else if(type.equals("OVERSEA")||type.equals("FUTURE")||type.equals("SPOT")){
//
//            //region 模板序号
////        0	酒款名称（简体）
////        1	酒款名称（英文）
////        2	适饮温度
////        3	所属品牌（简体）
////        4	所属产区（简体）
////        5	所属酒庄（简体）
////        6	酒款级别（简体）
////        7	葡萄品种（简体）
////        8	图片名称
////        9	酒款综述（简体）
////        10	酒款综述（英文）
////        11	年份
////        12	年产量
////        13	库存
////        14	销售单价
////        15	容量
////        16	酒的类别
////        17	sku（酒业系统商品id）
////        18	起售数量
////        19	叠加数量
////        20	供应商编号
////        21	供应商商品名称
////        22	供应商价格
////        23	供应商最少购货量
////        24	销售库存阈值
////        25	供应商地址
////        26	供应商电话
//            //endregion
//
//            list.add("酒款名称（简体）");
//            list.add("酒款名称（英文）");
//            list.add("适饮温度");
//            list.add("所属品牌（简体）");
//            list.add("所属产区（简体）");
//            list.add("所属酒庄（简体）");
//            list.add("酒款级别（简体）");
//            list.add("葡萄品种（简体）");
//            list.add("图片名称");
//            list.add("酒款综述（简体）");
//            list.add("酒款综述（英文）");
//            list.add("年份");
//            list.add("年产量");
//            list.add("库存");
//            list.add("销售单价");
//            list.add("容量");
//            list.add("酒的类别");
//            list.add("sku（酒业系统商品id）");
//            list.add("起售数量");
//            list.add("叠加数量");
//            list.add("供应商编号");
//            list.add("供应商商品名称");
//            list.add("供应商价格");
//            list.add("供应商最少购货量");
//            list.add("销售库存阈值");
//            list.add("供应商地址");
//            list.add("供应商电话");
//        }else{
//            throw new GenericException("fail", "导入模板模板错误");
//        }
//
//        for (int i = 0; i < title.length; i++) {
//            if (!title[i].equals(list.get(i))) {
//                throw new GenericException("fail", "导入数据文件列错误，错误列：" + title[i] + "列名称错误或位置错误");
//            }
//        }
//    }
//

}




