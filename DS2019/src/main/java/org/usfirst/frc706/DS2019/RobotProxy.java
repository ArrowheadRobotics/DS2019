// package org.usfirst.frc706.DS2019;

// import java.io.IOException;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;

// import edu.wpi.first.wpilibj.DriverStation;

// /**
//  * Proxy class for sending data to and receiving data from the roborio
//  * Created by Ben Bernard on 6/17/2016.
//  */
// public class RobotProxy {
//     DatagramSocket outgoingSocket;

//     private int _piPort = 27002;

//     private RobotThread robotThread;

//     public RobotProxy(int piPort) {
//         try {
//             _piPort = piPort;
//         } catch (Exception e) {
//         }

//         robotThread = new RobotThread(this);

//         new Thread(robotThread).start();

//     }

//     String[] visionData = {};

//     public double getXError() {
//     if (visionData!=new String[]{}) {
//         return Double.parseDouble(visionData[0]);
//     }
//     return 0;
//     }

//     public double getThetaError() {
//         if (visionData!=new String[]{}) {
//             return Double.parseDouble(visionData[1]);
//         }
//         return 0;
//         }

//     public class RobotThread implements Runnable {
//         private RobotProxy robot;

//         public RobotThread(RobotProxy robot) {
//             this.robot = robot;
//         }

//         @Override
//         public void run() {
//             // Create the listener
//             DatagramSocket incomingSocket;
//             byte[] receiveData = new byte[1024];
//             DriverStation.reportError("RUNNING", false);
//             try {
                
//                 incomingSocket = new DatagramSocket();
//                 while (true) {
//                     DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                     incomingSocket.receive(receivePacket);
//                     String raw = new String(receivePacket.getData());
//                     DriverStation.reportError("THIS IS TERRIBLE", false);
                    
//                     //synchronized (this) {
//                         DriverStation.reportError("RAW DATA IS: " + raw, false);
//                         visionData = raw.split(";");
//                     //}
//                 }

//             } catch (Exception ioe) {
//                 DriverStation.reportError("IO ERROR: " + ioe.getMessage(),false);
//             }
//         }
//     }

// }