package labBClimateMonitoringClient;

public class ClientMain {

	public static void main(String[] args) {
		//GUI graphicInterface = new GUI();
		int port = 6066; 
		ClimateMonitoring monitor = new ClimateMonitoring(port);
	}
}