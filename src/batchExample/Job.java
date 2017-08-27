package batchExample;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Job {
	Timer timer;

	public Job() {
	    Calendar calendar = Calendar.getInstance();

	    Date time = calendar.getTime();

	    timer = new Timer();
	    timer.schedule(new RemindTask(), 3000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Tarefa rodando!");
			ManipulaDados id = new ManipulaDados();			
			id.inserirDados();
			try {
				DecimalFormat df = new DecimalFormat("0.##");
				String vl = df.format(id.calculaMedia());
				System.out.println(vl);
				id.exibirClientes();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				super.cancel();
			}
		}
	}

	public static void main(String args[]) {
		new Job();
        System.out.println("Tarefa agendada.");
	
	}
}