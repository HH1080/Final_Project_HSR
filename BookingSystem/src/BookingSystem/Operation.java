package BookingSystem;

import java.util.Date; //�ϥ�Date�B�z�q���ɶ�

public class Operation {
	
	public String Booking(String UID, Date Ddate, Date Rdate, // Ddate�X�o�ɶ�, Rdate��{�ɶ�
			String SStation, String DStation, //S�l��, D�׾�
			int normalT, int concessionT, int studentT, //�@�벼, �u�ݲ�, �j�ǥͲ�
			int AorW, boolean BorS) // ���Dor�a��, �ӰȩμзǨ��[
	{
		if ((normalT+concessionT+studentT > 10) || ((Rdate != new Date(0))&&(normalT+concessionT+studentT > 5))) {
			return "���ѡA�]�q��w�w�L�h����(�C���̦h10�i�A�Ӧ^�����W�߭p��)";
		}
		
		if (Ddate.after(null) || Rdate.after(null)) {
			return "���ѡA�]�|����w��";
		}
		
		/*�U����ӭn�Ѧҧڭ̫��B�z���
		 * �j�P�W�p�U
		 * 1. ��list�x�s�Z���s��
		 * 2. �ˬd�U�ر��� (�U�Ӳ���, AorW, �ɶ�)�A�N�ŦX���󪺯Z���s�Jlist
		 * 3. �z�Llist��X�U�Z����T
		 */
		
		/* �C�X�Ӯɬq�ŦX���󪺨���*/
		/* ���ѡA�]���Ӯɬq�����y��w���j*/
		
		return null;
	}
}
