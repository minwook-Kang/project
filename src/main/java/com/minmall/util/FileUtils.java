package com.minmall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;


//웹 프로젝트 외부 영역에 존재하는 파일작업을 하기 위한 유틸
public class FileUtils {
	
	//날짜 폴더 경로 설정 메소드
	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
	
		//년/월/일 형태의 날짜 경로설정
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		System.out.println("calcPath result : " + datePath);
		
		//경로 별 모든 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	
	//폴더 생성 메소드
	private static void makeDir(String uploadPath, String... paths) {
		
		if(new File(paths[paths.length -1]).exists()) {
			return;
		}
		
		for(String path: paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	//이미지 파일의 썸네일 생성 메소드
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,120);
		
		//썸네일 생성 준비
		String thumbNailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbNailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		//생성한 썸네일 경로의 subString을 반환
		System.out.println("makeThumbNail() thumbNail : " + thumbNailName);
		return thumbNailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//일반 파일의 아이콘 생성 메소드
	private static String makeIcon(String uploadPath, String Path, String fileName) throws Exception {
		
		String iconName = uploadPath + Path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	//파일 업로드
	public static String uploadFile(String uploadPath, String originName, byte[] fileData) throws Exception {
		
		System.out.println("uploadFile() execute...");
		
		UUID uuid = UUID.randomUUID(); //파일명 중복방지
		String savedName = uuid.toString() + "_" + originName;
		//파일 경로 설정
		String savedPath = calcPath(uploadPath);
		//설정한 정보로 빈 파일 생성
		File target = new File(uploadPath + savedPath, savedName);
		//만든 파일에 데이터 씀
		FileCopyUtils.copy(fileData, target);
		//확장자 명만 가져옴
		String formatName = originName.substring(originName.lastIndexOf(".") + 1);
		String uploadFileName = null;
		
		//이미지 파일인지 일반 파일인지 확인
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savedPath, savedName);
		}else {
			uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadFileName;
		
	}

	//파일 가져옴
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType type = MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			in = new FileInputStream(uploadPath + fileName);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	//이미지 파일 삭제
	public static void deleteFile(String uploadPath, String fileName) {
		
		String front = fileName.substring(0, 12);
		String end = fileName.substring(14);
		String origin = front + end;
		
		new File(uploadPath + origin.replace('/', File.separatorChar)).delete();
		new File(uploadPath + fileName.replace('/',File.separatorChar)).delete();
	}
	
	//썸네일 파일명 -> 원래 파일명
	public static String thumbToOriginName(String thumbnailName) {
		String front = thumbnailName.substring(0, 12);
		String end = thumbnailName.substring(14);
		
		return front+end;
	}
	
}
