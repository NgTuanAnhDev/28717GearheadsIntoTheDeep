package org.firstinspires.ftc.teamcode.robot;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class robot
{
    DcMotor horizontalSlideMotor;
    DcMotor verticalSlideMotor;

    Servo verticalArmServo;
    Servo horizontalArmServo;

    Servo horizontalClawServo;
    Servo verticalClawServo;
    public void init(@NonNull HardwareMap hardwareMap)
    {
        horizontalSlideMotor = hardwareMap.dcMotor.get("horizontalSlide");
        verticalSlideMotor = hardwareMap.dcMotor.get("verticalSlide");

        horizontalSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        verticalArmServo = hardwareMap.servo.get("verticalArm");
        horizontalArmServo = hardwareMap.servo.get("horizontalArm");

        horizontalClawServo = hardwareMap.servo.get("horizontalClaw");
        verticalClawServo = hardwareMap.servo.get("verticalClaw");
    }
}
