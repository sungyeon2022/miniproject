//package monster;
//
//import imgSize.Gap;
//import imgSize.ViewDirect;
//import imgSize.WormSize;
//
//public class MonsterAttacki {
//
//	//공격 체크
//	public void attackCheck(int direct, int range) {
////		int xDistance = getIssac().getXPlayerCenter() - getXPlayerCenter();
////		int yDistance = getIssac().getYPlayerCenter() - getYPlayerCenter();
////		System.out.println(getIssac().getXPlayerCenter() + "  " + range + " " + getYPlayer());
//		if(getIssac().getXPlayerCenter()-range < 30 || getIssac().getYPlayerCenter()-range < 30) {
//			System.out.println("공격");
////			System.out.println(direct);
//			System.out.println(range);
////			System.out.println(getIssac().getXPlayerCenter());
////			System.out.println(getIssac().getYPlayerCenter());
////			System.out.println(getXPlayerCenter());
////			System.out.println(getYPlayerCenter());
//			setPlayerAttacking(true);
//			
//			monsterSpeed = 4;
//			switch(direct) {
//			 case 3:
//				 while(range >= 100)
//				 moveUp();
//				 System.out.println("위로 공격");
//				 break;
//			 case 1:
//				 while(range <= 440)
//				 moveDown();
//				 System.out.println("아래로 공격");
//				 break;
//			 case 2:
//				 while(range >= 130)
//				 moveLeft();
//				 System.out.println("왼쪽으로 공격");
//				 break;
//			 case 4:
//				 while(range <= 790)
//				 moveRight();
//				 System.out.println("오른쪽로 공격");
//				 break;
//			 default:
//				 monsterSpeed = 2;
//				 setPlayerAttacking(false); 
//			}
//			attackMotion(direct - 1);
//							
//		}
//	}
//
//	
//	//공격 체크
//	@Override
//	public void attack() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				switch (getViewDirect()) {
//				case ViewDirect.DOWN:
//					attackCheck(ViewDirect.DOWN, getXPlayerCenter());
//					break;	
//				case ViewDirect.LEFT:
//					attackCheck(ViewDirect.LEFT, getYPlayerCenter());
//					break;
//				case ViewDirect.UP:
//					attackCheck(ViewDirect.UP, getXPlayerCenter());
//					break;
//				case ViewDirect.RIGHT:
//					attackCheck(ViewDirect.RIGHT, getYPlayerCenter());
//					break;
//				}
//			} 
//		}).start();
//	}
//	public void attackMotion(int direct) {
//		getSsMonster().setXPos((WormSize.WIDTH * direct) + (Gap.COLUMGAP * direct));
//		getSsMonster().setYPos(WormSize.HEIGHT * 4 + Gap.ROWGAP * 4);
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		getSsMonster().setXPos(0);
//		getSsMonster().setYPos(WormSize.HEIGHT * direct + Gap.ROWGAP * direct);
//		getSsMonster().drawObj(getXPlayer(), getYPlayer());
//		
//		
//		getIssac().setLife(getIssac().getLife() - 1);	// 플레이어 생명력 1감소
//		getIssac().dead();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}}
//
//}
