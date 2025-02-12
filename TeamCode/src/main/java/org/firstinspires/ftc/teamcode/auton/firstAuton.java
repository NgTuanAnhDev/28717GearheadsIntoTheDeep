package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.TankDrive;

@Autonomous(name = "firstAuton")
public class firstAuton extends LinearOpMode {

    public enum FirstAutonState {
        MOVING_TO_SAMPLE,
        MOVING_TO_POINT,

    }
    int verticalSlidePosition;
    final int verticalSlideUpperLimit = 600; // chinh sau
    final int verticalSlideLowerLimit = 0;
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
                case MOVING_TO_POINT:
                    telemetry.addData("State", "Moving to POINT");
                    telemetry.update();
                    takesample();
                    moveForward(1000);
                    dropsample();
                    moveBackward(1000);
                    break;
            }

            sleep(500);
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

    private void keothanh() {
        verticalSlidePosition = verticalSlideMotor.getCurrentPosition();
        verticalSlideMotor.setPower(1);
        if (verticalSlidePosition == verticalSlideUpperLimit) {
            verticalSlideMotor.setPower(0);
        }
    }
    private  void hathanh() {
        verticalSlidePosition = verticalSlideMotor.getCurrentPosition();
        verticalSlideMotor.setPower(-1);
        if (verticalSlidePosition == verticalSlideLowerLimit) {
            verticalSlideMotor.setPower(0);
        }
    }
    private  void dropsample(){
        keothanh();
        verticalArmServo.setPosition(1);
        verticalClawServo.setPosition(0.85); // mở
        verticalClawServo.setPosition(0); // đóng
    }
    private void takesample() {
        hathanh();
        verticalArmServo.setPosition(0);
        verticalClawServo.setPosition(0.85); // mở
        verticalClawServo.setPosition(0); // đóng
    }
}