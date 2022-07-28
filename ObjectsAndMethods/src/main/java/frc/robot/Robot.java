// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /*DigitalInput button_a = new DigitalInput(0);
  DigitalOutput yellow_led = new DigitalOutput(3);
  DigitalInput button_b = new DigitalInput(1);
  DigitalOutput red_led = new DigitalOutput(2);
  //DigitalInput button_c = new DigitalInput(2);
  //DigitalOutput green_led = new DigitalOutput(1);
  */

  

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final RomiDrivetrain m_drivetrain = new RomiDrivetrain();
  int runs = 0;
  double targetspeed = .3;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    //yellow_led.set(false);
    //red_led.set(true);
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    m_drivetrain.resetEncoders();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    runs=0;
    targetspeed=.3;
    //yellow_led.set(true);
    //red_led.set(false);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if(runs<70){
      targetspeed = targetspeed +.01;
      m_drivetrain.arcadeDrive(targetspeed,0);
      

    }
    else if (runs>=70&&runs<=140){
      targetspeed = targetspeed -.01;
      m_drivetrain.arcadeDrive(targetspeed,0);
    }
    
    else if(runs>140){
      m_drivetrain.arcadeDrive(0,0);
    }
    runs=runs+1;
    
    
    /*if(button_a.get()){
      yellow_led.set(true);
    }
    else{
      yellow_led.set(false);
    }
    if(button_b.get()){
      red_led.set(true);
    }
    else{
      red_led.set(false);
    }
    /*if(button_c.get()){
      green_led.set(true);
    }
    else{
      green_led.set(false);
    }*/
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
