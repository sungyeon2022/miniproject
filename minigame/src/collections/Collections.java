package collections;

import SpriteSheet.SpriteSheet;
import lombok.Data;
import main.miniApp;

@Data

public class Collections {
	private miniApp app;
	private SpriteSheet ssCollection;
	private int collectionX;
	private int collectionY;
	
	
	public Collections(miniApp app) {
		this.app = app;
		init();
		setting();
		batch();
		motion();
		position();
	}
	public void init() {} // 레이어의 좌표, 이미지 적용
	public void setting() {} // collection적용시 캐릭터 스텟 및 변경 사항 적용
	public void batch() {} // 레이어를 프레임에 배치
	public void motion() {} // 레이어들의 모션 설정
	public void position() {} // 레이어들이 캐릭터의 움직임에 따라 이동 
//	모션과 위치 메서드 분리이유 : 모션은 레이어의 프레임에 맞춰 Thread.sleep을 사용 하지만 위치는 캐릭터의 이동속도에 맞추기 때문에 최적화를 위해 분리   
}
