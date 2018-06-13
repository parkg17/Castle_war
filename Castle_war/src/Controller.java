import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;

public class Controller {
	
	private ArrayList<EntityCastle> ea = new ArrayList<EntityCastle>();
	private LinkedList<EntityUnit> eb = new LinkedList<EntityUnit>();
	
	private LinkedList<EntityCastle> ea1 = new LinkedList<EntityCastle>(); 
	private LinkedList<EntityCastle> ea2 = new LinkedList<EntityCastle>();
	
	private LinkedList<EntityCastle> ea3 = new LinkedList<EntityCastle>(); 
	private LinkedList<EntityCastle> ea4 = new LinkedList<EntityCastle>();
	
	public EntityCastle enta;
	public EntityUnit entb;
	public EntityCastle enta1;
	public EntityCastle enta2;
	
	private Textures tex;
	private Game game;
	
	public static int click = 0;
	public static int beforeclick = 0;
	public static double click_x = 0;
	public static double click_y = 0;
	
	public int[][] clickArr = new int[6][6];
	public int[][] clickArrEnemy = new int[6][6];
	
	public static final double Castle1_x = 200;
	public static final double Castle1_y = 100;
	
	public static final double Castle2_x = 250;
	public static final double Castle2_y = 250;
	
	public static final double Castle3_x = 400;
	public static final double Castle3_y = 105;
	
	public static final double Castle4_x = 150;
	public static final double Castle4_y = 300;
	
	public static final double Castle5_x = 500;
	public static final double Castle5_y = 350;
	
	
	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
	}
	
	public void createCastle() {
		addEntity(new Castle(Castle1_x, Castle1_y, tex, this, game));
		addEntity(new Castle(Castle2_x, Castle2_y, tex, this, game));
		addEntity(new Castle2(Castle3_x, Castle3_y, tex, this, game));
		addEntity(new Castle2(Castle4_x, Castle4_y, tex, this, game));
		addEntity(new Castle(Castle5_x, Castle5_y, tex, this, game));
	}
	
	public void tick() {
		
		if(click != 0 && beforeclick != 0 && click != beforeclick) {
			enta1 = ea.get(beforeclick - 1);
			enta2 = ea.get(click - 1);
			if(enta1.check_identifier() == 1) {
				if(clickArr[beforeclick - 1][click - 1] == 0) {
					ea1.add(enta1);
					ea2.add(enta2);
					clickArr[beforeclick - 1][click - 1] = 1;
				} else {
					ea1.remove(enta1);
					ea2.remove(enta2);
					clickArr[beforeclick - 1][click - 1] = 0;
				}
			}
			click = 0;
			beforeclick = 0;
		}
		
		if(!ea1.isEmpty()) {
			int count = 0;
			for(int i = 0; i < ea.size(); i++) {
				for(int j = 0; j < ea1.size(); j++) {
					if((ea.get(i) == ea1.get(j)) && ea.get(i).check_unit_number() > 10 ) { 
						count++;
						addEntity(new Unit_archer(ea1.get(j).getX() + 16, ea1.get(j).getY() + 16, ea2.get(j).getX() + 16, ea2.get(j).getY() + 16, tex, this, game));
					}
				}
				if(count != 0) {
					for(int j = 0; j < count; j++) {
						ea.get(i).change_unit_number(5.0);
					}
					count = 0;
				}
			}
		}
		
		for(int i = 0; i < ea.size(); i++) {
			if(ea.get(i).check_enemynumber() >= 10 && ea.get(i).check_identifier() == 1) {
				EntityCastle temp_ent;
				double temp_x, temp_y;
				temp_x = ea.get(i).getX();
				temp_y = ea.get(i).getY();
				for(int j = 0; j < ea1.size(); j++) {
					if(ea1.get(j) == ea.get(i)) {
						ea1.remove(j);
						ea2.remove(j);
						j = j - 1;
					}
				}
				temp_ent = ea.get(i);
				ea.set(i, new Castle2(temp_x, temp_y, tex, this, game)); 
				removeEntity(temp_ent);
			}
			else if(ea.get(i).check_enemynumber() >= 10 && ea.get(i).check_identifier() == 2) {
				EntityCastle temp_ent;
				double temp_x, temp_y;
				temp_x = ea.get(i).getX();
				temp_y = ea.get(i).getY();
				for(int j = 0; j < ea2.size(); j++) { 
					if(ea2.get(j) == ea.get(i)) {
						ea1.remove(j);
						ea2.remove(j);
						j = j - 1;
					}
				}
				temp_ent = ea.get(i);
				ea.set(i, new Castle(temp_x, temp_y, tex, this, game)); 
				removeEntity(temp_ent);
			}
		}
		

		if(!ea1.isEmpty())
		{
			for(int i = 0; i < ea.size(); i++)
			{
				if(ea.get(i).check_identifier() == 2)
				{
					for(int j = 0; j < ea1.size(); j++)
					{
						for(int k = 0; k < ea.size(); k++)
						{
							if(ea.get(k).check_identifier() == 1)
							{
								if(ea.get(k) == ea1.get(j) && clickArrEnemy[i][k] == 0)
								{ 
									System.out.println(i +"가 "+ k + "를 공격");
									enta1 = ea.get(i);
									enta2 = ea.get(k);
									ea3.add(enta1);
									ea4.add(enta2);
									clickArrEnemy[i][k] = 1;
								}
							}
						}
					}
				}
			}
		}
		
		if(!ea3.isEmpty()) {
			int count1 = 0;
			for(int i = 0; i < ea.size(); i++) {
				for(int j = 0; j < ea3.size(); j++) {
					if((ea.get(i) == ea3.get(j)) && ea.get(i).check_unit_number() > 10 ) { 
						count1++;
						addEntity(new Unit_archer2(ea3.get(j).getX() + 16, ea3.get(j).getY() + 16, ea4.get(j).getX() + 16, ea4.get(j).getY() + 16, tex, this, game));
					}
				}
				if(count1 != 0) {
					for(int j = 0; j < count1; j++) {
						ea.get(i).change_unit_number(5.0);
					}
					count1 = 0;
				}
			}
		}
	
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.tick();
		}
		
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.tick();
		}
	}

	public void render(Graphics g) {
		
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.render(g);
		}
		
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.render(g);
		}	
	}
	
	public int find_Castle(double mouse_x, double mouse_y) {
		double castle_x, castle_y;
		for(int i = 0; i < ea.size(); i++) {
			castle_x = ea.get(i).getX();
			castle_y = ea.get(i).getY();
			
			if(mouse_x > castle_x && mouse_x < castle_x + 64 ) {
				if(mouse_y > castle_y && mouse_y < castle_y + 64 ) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public void addEntity(EntityCastle block) {
		ea.add(block);
	}
	
	public void removeEntity(EntityCastle block) {
		ea.remove(block);
	}
	
	public void addEntity(EntityUnit block) {
		eb.add(block);
	}
	
	public void removeEntity(EntityUnit block) {
		eb.remove(block);
	}
	
	public ArrayList<EntityCastle> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityUnit> getEntityB() {
		return eb;
	}
}
