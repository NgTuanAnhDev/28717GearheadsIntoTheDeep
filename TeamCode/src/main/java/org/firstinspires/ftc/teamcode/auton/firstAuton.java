package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.TankDrive

@Autonomous(name = "firstAuton")
public class firstAuton extends LinearOpMode {

    public enum FirstAutonState {
        MOVING_TO_SAMPLE,
        MOVING_TO_POINT,
    }
    DcMotor verticalSlideMotor;
    Servo verticalArmServo;
    Servo verticalClawServo;
    DcMotor leftMotor;
    DcMotor rightMotor;
    ElapsedTime verticalTimer = new ElapsedTime();

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
        FirstAutonState currentState = FirstAutonState.MOVING_TO_POINT;
        while (opModeIsActive()) {
            switch (currentState) {
                case MOVING_TO_SAMPLE:
                    telemetry.addData("state","Moving to sample");
                    telemetry.update();
                    moveForward(1000);
                    sleep(2000);
                    takesample();
                    currentState = FirstAutonState.MOVING_TO_POINT;
                    break;
                case MOVING_TO_POINT:
                    telemetry.addData("State", "Moving to POINT");
                    telemetry.update();
                    dropsample();
                    currentState = FirstAutonState.MOVING_TO_SAMPLE;
                    break;
            }

            sleep(500);
        }
    }

    private void moveForward(long timeInMillis) {
        leftMotor.setPower(-0.5);
        rightMotor.setPower(-0.5);
        sleep(timeInMillis);
        stopMotor();
    }

    private void moveBackward(long timeInMillis) {
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        sleep(timeInMillis);
        stopMotor();
    }
    private void stopMotor() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    private void takesample() {
        verticalArmServo.setPosition();
        verticalClawServo.setPosition();
    }
    private  void dropsample(){
        moveForward(1000);
        verticalArmServo.setPosition();
        moveBackward(500);
        verticalClawServo.setPosition(0.75);
        moveBackward(1000);
        verticalArmServo.setPosition();
        verticalClawServo.setPosition();

    }

}
