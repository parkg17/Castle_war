import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		double mx = e.getX();
		double my = e.getY();
		
		if(Game.State == Game.STATE.MENU) {
			if(mx >= Game.WIDTH / 2 + 40 && mx <= Game.WIDTH / 2 + 140) {
				if(my >= 350 && my <= 400) {
					Game.State = Game.STATE.GAME;
				}
			}
			if(mx >= Game.WIDTH / 2 + 200 && mx <= Game.WIDTH / 2 + 300) {
				if(my >= 350 && my <= 400) {
					System.exit(1);
				}
			}

		} else if(Game.State == Game.STATE.GAME) {
			if(mx >= Controller.Castle1_x && mx <= Controller.Castle1_x  + 64) {
				if(my >= Controller.Castle1_y && my <= Controller.Castle1_y + 64) {
					System.out.println("1번째 성 클릭");
					Controller.beforeclick = Controller.click;
					Controller.click = 1;
					Controller.click_x = mx;
					Controller.click_y = my;
					return;
				}
			}
			
			if(mx >= Controller.Castle2_x  && mx <= Controller.Castle2_x  + 64) {
				if(my >= Controller.Castle2_y && my <= Controller.Castle2_y + 64) {
					System.out.println("2번째 성 클릭");
					Controller.beforeclick = Controller.click;
					Controller.click = 2;
					Controller.click_x = mx;
					Controller.click_y = my;
					return;
				}
			} 
			
			if(mx >= Controller.Castle3_x  && mx <= Controller.Castle3_x  + 64) {
				if(my >= Controller.Castle3_y && my <= Controller.Castle3_y + 64) {
					System.out.println("3번째 성 클릭");
					Controller.beforeclick = Controller.click;
					Controller.click = 3;
					Controller.click_x = mx;
					Controller.click_y = my;
					return;
				}
			} 
			
			if(mx >= Controller.Castle4_x  && mx <= Controller.Castle4_x  + 64) {
				if(my >= Controller.Castle4_y && my <= Controller.Castle4_y + 64) {
					System.out.println("4번째 성 클릭");
					Controller.beforeclick = Controller.click;
					Controller.click = 4;
					Controller.click_x = mx;
					Controller.click_y = my;
					return;
				}
			} 
			
			if(mx >= Controller.Castle5_x  && mx <= Controller.Castle5_x  + 64) {
				if(my >= Controller.Castle5_y && my <= Controller.Castle5_y + 64) {
					System.out.println("5번째 성 클릭");
					Controller.beforeclick = Controller.click;
					Controller.click = 5;
					Controller.click_x = mx;
					Controller.click_y = my;
					return;
				}
			} 
			System.out.println("클릭 초기화");
			Controller.beforeclick = 0;
			Controller.click = 0;
			Controller.click_x = mx;
			Controller.click_y = my;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
