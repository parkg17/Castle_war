package EntityPackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityCastle {

	public void tick(); // ������ �� �Լ� �ȿ��� �̷����
	public void render(Graphics g); // �̹����� �׷������� ǥ���ϴ°� �� �Լ��ȿ��� �̷����
	public Rectangle getBounds(); // ������ ���(���߿� ���� �����Ҷ� �̿�)
	
	public double getX(); // ������ x��ǥ�� ����
	public double getY(); // ������ y��ǥ�� ����
	
	public int check_identifier(); // �Ʊ����� �������� Ȯ��
	public int check_enemynumber(); // ���ȿ� �����ϴ� ������ �� �ľ�
	public int check_unit_number(); // ���ȿ� ������� �Ʊ��� �� �ľ�
	public String check_unit_number_String(); // �� �ȿ� �����ϴ� �Ʊ��� �� ��� �� ��� 
	public void change_enemynumber(); // ���ȿ� �����ϴ� ������ �� ���� - �߰�
	public void change_unit_number(double num); // �Ʊ��� ���������鼭 ���ȿ� �����ϴ� �Ʊ��� �� ����
	
}
