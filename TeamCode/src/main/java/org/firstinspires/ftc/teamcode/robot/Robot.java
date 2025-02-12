package org.firstinspires.ftc.teamcode.robot;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Dictionary;
import java.util.Enumeration;

public class Robot
{
    Dictionary<ArmPosition.HorizontalPosition, Double> horizontalArmPosition =
            new Dictionary<ArmPosition.HorizontalPosition, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public Enumeration<ArmPosition.HorizontalPosition> keys() {
                    return null;
                }

                @Override
                public Enumeration<Double> elements() {
                    return null;
                }

                @Override
                public Double get(Object o) {
                    return 0.0;
                }

                @Override
                public Double put(ArmPosition.HorizontalPosition horizontalPosition, Double aDouble) {
                    return 0.0;
                }

                @Override
                public Double remove(Object o) {
                    return 0.0;
                }
            };
    Dictionary<ArmPosition.VerticalPosition, Double> verticalArmPosition =
            new Dictionary<ArmPosition.VerticalPosition, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public Enumeration<ArmPosition.VerticalPosition> keys() {
                    return null;
                }

                @Override
                public Enumeration<Double> elements() {
                    return null;
                }

                @Override
                public Double get(Object o) {
                    return 0.0;
                }

                @Override
                public Double put(ArmPosition.VerticalPosition verticalPosition, Double aDouble) {
                    return 0.0;
                }

                @Override
                public Double remove(Object o) {
                    return 0.0;
                }
            };

    private DcMotor horizontalSlideMotor;
    private DcMotor verticalSlideMotor;

    private Servo verticalArmServo;
    private Servo horizontalArmServo;

    private Servo horizontalClawServo;
    private Servo verticalClawServo;
    public Robot(Dictionary<ArmPosition.HorizontalPosition, Double> horizontalPosition,
                 Dictionary<ArmPosition.VerticalPosition, Double> verticalPosition)
    {
        //horizontalArmPosition = horizontalPosition;
        //verticalArmPosition = verticalPosition;
    }
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
