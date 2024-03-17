package com.sa.safetynet.emergency.fire.sensor;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.emergency.service.EmergServiceInterface;

public class SensorActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		
		serviceReference = context.getServiceReference(EmergServiceInterface.class.getName());
		
		EmergServiceInterface emergencyServices = (EmergServiceInterface)context.getService(serviceReference);
		System.out.println("Fire Detection Sensor is Online!");
		
		//Run test class to simulate an emergency situation
		runTest(emergencyServices);
		
	}
	
	public void runTest(EmergServiceInterface emergencyServices) {
		
				//Variables
				String emrgType = "fire";
				String userInput;
				
				String location;
				Boolean runAgain = false;
				

				
				//Scanner object
				Scanner sc = new Scanner(System.in);
				
				
				System.out.println("Emeregency location : " );
				
				location = sc.next();
				
				emergencyServices.sendNotification(emrgType);
				emergencyServices.activateEmergencyProtocol(location);
				

				System.out.println("Do you want to deactivate emergency system now?(Y/N)");
				userInput = sc.next();
				
				if(userInput.equals("Y") || userInput.equals("y")) {
					
					emergencyServices.switchOffEmergencySystem();
				}
				
				//Close the sc Object to avoid the resource leaks
				sc.close();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Fire detection sensor is now offline...!!" );
		context.ungetService(serviceReference);
	}

}
