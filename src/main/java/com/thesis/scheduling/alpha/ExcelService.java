package com.thesis.scheduling.alpha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

	@Autowired
	TutorialRepository repository;

	public void testsave(MultipartFile file) {
		try {

			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			int count = 0;
			
			List<Integer> list = new ArrayList<>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			list.add(8);
			list.add(9);
			list.add(10);
			list.add(11);
			list.add(12);
			list.add(12);
			list.add(14);
			/*
			String input1 = workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
			System.out.println(input1.substring(input1.lastIndexOf("ที่") + 4, input1.length()).split(" / ")[0]);
			System.out.println(input1.substring(input1.lastIndexOf("ที่") + 4, input1.length()).split(" / ")[1]);
			*/
			for (int i = 2; i <= 15; i++) {
				
				for (int j = 2; j <= 94; j++) {
					if (workbook.getSheetAt(0).getRow(j).getCell(i).getCellType() == CellType.NUMERIC) {
						System.out.print(list.get(0) + " " + workbook.getSheetAt(0).getRow(j).getCell(0).getStringCellValue() + " ");
						for (int k = j; k > 0; k--) {
							if (workbook.getSheetAt(0).getRow(k).getCell(1).getStringCellValue().contains(" CPE")) {
								System.out.println(
										workbook.getSheetAt(0).getRow(k).getCell(1).getStringCellValue().substring(
												workbook.getSheetAt(0).getRow(k).getCell(1).getStringCellValue()
														.indexOf(" CPE"),
												workbook.getSheetAt(0).getRow(k).getCell(1).getStringCellValue()
														.length()));
								break;
							}
						}

					} else {

					}

				}
				list.remove(0);
			}

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public void save(MultipartFile file) {
		try {
			List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<Tutorial> getAllTutorials() {
		return repository.findAll();
	}
}
