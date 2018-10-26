/**
 * 系统数据导出Excel 生成器
 * @version 1.0
 */
package org.com.lzz.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.OutputStream;
import java.util.ArrayList;

/**
 * @Author pengzhiqun
 * @Date 2017/2/28.10:06
 * @Discription 导出订单
 */
public class ExcelFileGenerator {
	private ArrayList<String> fieldName = null; //excel标题数据集

	private ArrayList<ArrayList<String>> fieldData = null; //excel数据内容	

	private HSSFWorkbook workBook = null;

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param fieldData 结果集
	 */
	public ExcelFileGenerator(ArrayList<String> fieldName, ArrayList<ArrayList<String>> fieldData) {

		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createWorkbook() {

		workBook = new HSSFWorkbook();//创建一个工作薄对象
		int rows = fieldData.size();//总的记录数

		HSSFSheet sheet = workBook.createSheet("Page");//使用workbook对象创建sheet对象
		HSSFRow headRow = sheet.createRow((short) 0); //创建行，0表示第一行（本例是excel的标题）
		for (int j = 0; j < fieldName.size(); j++) {//循环excel的标题
			HSSFCell cell = headRow.createCell( j);//使用行对象创建列对象，0表示第1列
			//添加样式
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(fieldName.get(j) != null){
				//将创建好的样式放置到对应的单元格中
				/**cell.setCellStyle(cellStyle);*/
				cell.setCellValue((String) fieldName.get(j));//为标题中的单元格设置值
			}else{
				cell.setCellValue("-");
			}
		}
		for (int k = 0; k < rows ; k++) {
			HSSFRow row = sheet.createRow((short) (k + 1));//创建1行
			ArrayList<String> oneFileData = fieldData.get(k);
			for (int n = 0; n < oneFileData.size(); n++) {
				HSSFCell cell = row.createCell( n);//使用行创建列对象
				if(oneFileData.get(n) != null){
					cell.setCellValue((String) oneFileData.get(n).toString());
				}else{
					cell.setCellValue("");
				}
			}
		}
		return workBook;
	}

	public void exportExcel(OutputStream outputStream) throws Exception {
		workBook = createWorkbook();
		workBook.write(outputStream);//将excel中的数据写到输出流中，用于文件的输出
		outputStream.close();
	}

}
