package handler;

import java.io.File;

public class FileHandler {
	
	// 파일이름과 경로를 받아서 파일을 삭제하는 메서드
	// 리턴타입 int => 잘 삭제되었는지를 체크하기위 위한 변수 (isOk)
	public int deleteFile(String imageFileName, String savePath) {
		
		// 파일을 삭제하는 메서드의 리턴 타입이 boolean
		boolean isDel = false;
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir + File.separator + imageFileName);
		File removeThunmbFile = new File(fileDir + File.separator + "th_" + imageFileName);
		
		// 파일이 있는지(존재하는지) 확인 -> 삭제
		if(removeFile.exists() || removeThunmbFile.exists()) {
			isDel = removeFile.delete();
			
			if(isDel) {		// 메일 파일이 지워졌다면 썸네일 파일도 삭제
				isDel = removeThunmbFile.delete();
			}
		}
		
		return isDel ? 1 : 0;
	}
}
