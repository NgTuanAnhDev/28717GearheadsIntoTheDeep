package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "luckyAuton")
public class luckyAuton extends LinearOpMode {

    int verticalSlidePosition;
    final int verticalSlideUpperLimit = 600; //Adjust after
    final int verticalSlideLowerLimit = 0;
    DcMotor verticalSlideMotor;
    Servo verticalArmServo;
    Servo verticalClawServo;
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        verticalSlideMotor = hardwareMap.dcMotor.get("verticalSlide");
        verticalSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        verticalArmServo = hardwareMap.servo.get("verticalArm");
        verticalClawServo = hardwareMap.servo.get("verticalClaw");

        waitForStart();
        telemetry.addData("Statu", "RUNNING");
        telemetry.update();
        upper();
        verticalArmServo.setPosition(1);
        moveForward(1000);
        verticalClawServo.setPosition(1);
        lower();
        verticalArmServo.setPosition(0);
        verticalClawServo.setPosition(0);
        moveBackward(1000);
        telemetry.addData("Statu", "DONE");
        telemetry.update();
    }
    private void upper() {
        verticalSlidePosition = verticalSlideMotor.getCurrentPosition();
        verticalSlideMotor.setPower(1);
        if (verticalSlidePosition == verticalSlideUpperLimit) {
            verticalSlideMotor.setPower(0);
        }
    }
    private void lower() {
        verticalSlidePosition = verticalSlideMotor.getCurrentPosition();
        verticalSlideMotor.setPower(-1);
        if (verticalSlidePosition == verticalSlideLowerLimit) {
            verticalSlideMotor.setPower(0);
        }
    }

    private void moveForward(long timeInMillis) {
        leftMotor.setPower(-1);
        rightMotor.setPower(-1);
        sleep(timeInMillis);
        stopMotor();
    }

    private void moveBackward(long timeInMillis) {
        leftMotor.setPower(1);
        rightMotor.setPower(1);
        sleep(timeInMillis);
        stopMotor();
    }

    private void stopMotor() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
