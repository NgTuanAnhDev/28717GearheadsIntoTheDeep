package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "firstAuton")
public class firstAuton extends LinearOpMode
{
    public enum FirstAutonState
    {
        MOVING_TO_ADJUST_POSITION,
        ADJUSTING,

    }
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor leftMotor = hardwareMap.dcMotor.get("left_motor");
        DcMotor rightMotor = hardwareMap.dcMotor.get("right_motor");

        DcMotor verticalSlideMotor = hardwareMap.dcMotor.get("verticalSlide");

        Servo verticalArmServo = hardwareMap.servo.get("verticalArm");
        Servo verticalClawServo = hardwareMap.servo.get("verticalClaw");

    }
}
