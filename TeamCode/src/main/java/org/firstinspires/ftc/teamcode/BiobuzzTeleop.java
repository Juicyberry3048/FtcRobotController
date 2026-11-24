package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class BiobuzzTeleop extends OpMode {
    private DcMotor FR; //port 0
    private DcMotor BR; //port 1
    private DcMotor FL; //port 2
    private DcMotor BL; //port 3
    DcMotor intake; //EH port 0
    private DcMotorEx flywheel; //EH port 1
    private Servo nGate; //EH servo port 4
    private Servo pGate; //EH servo port 5
    private GoBildaPinpointDriver pinpoint; // EH I2C port 0
    private static final double MOTOR_TICKS_PER_REV = 28.0;
    private double headingOffset = 0;
    private boolean previousResetButton = false;
    @Override
    public void init() {
        FR = hardwareMap.get(DcMotor.class, "FR");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BL = hardwareMap.get(DcMotor.class, "BL");
        intake = hardwareMap.get(DcMotor.class, "intake");
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        nGate = hardwareMap.get(Servo.class, "nGate");
        pGate = hardwareMap.get(Servo.class, "pGate");
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        pinpoint.setOffsets(-67, -125, DistanceUnit.MM);
        pinpoint.resetPosAndIMU();

        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    @Override
    public void loop()
    {
        pinpoint.update();
        double ticksPerSec = flywheel.getVelocity();
        double motorRpm = ticksPerSec / MOTOR_TICKS_PER_REV * 60.0;
        telemetry.addData("Flywheel Speed", Math.abs(motorRpm));
        telemetry.addData("Flywheel speed encoders", flywheel.getVelocity());

        double pinpointX = pinpoint.getPosX(DistanceUnit.INCH);
        double pinpointY = pinpoint.getPosY(DistanceUnit.INCH);
        // Read raw Pinpoint heading
        double rawHeading = pinpoint.getHeading(AngleUnit.DEGREES);

// Read heading reset button
        boolean resetButton = gamepad1.y;

// Reset heading once per button press
        if (resetButton && !previousResetButton) {
            headingOffset = rawHeading;
        }

        previousResetButton = resetButton;

// Calculate corrected heading
        double heading = rawHeading - headingOffset;

        // Read controller inputs - start of chatgpt code
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = -gamepad1.right_stick_x;

        // Convert heading to radians
        double headingRadians =
                Math.toRadians(heading);

        // Field-centric transformation
        double rotX =
                x * Math.cos(headingRadians)
                        - y * Math.sin(headingRadians);

        double rotY =
                x * Math.sin(headingRadians)
                        + y * Math.cos(headingRadians);

        // Original mecanum equations
        double flPower = rotY - rotX - rx;
        double blPower = rotY + rotX - rx;
        double frPower = rotY + rotX + rx;
        double brPower = rotY - rotX + rx;

        // Normalize motor powers
        double max = Math.max(1.0,
                Math.max(
                        Math.max(Math.abs(flPower), Math.abs(blPower)),
                        Math.max(Math.abs(frPower), Math.abs(brPower))
                )
        );

        FL.setPower(flPower / max);
        BL.setPower(blPower / max);
        FR.setPower(frPower / max);
        BR.setPower(brPower / max);
        //end of chatgpt code
        if(gamepad2.right_bumper)
        {
            nGate.setPosition(0.5);
        }
        else
        {
            nGate.setPosition(1);
        }
        if(gamepad2.a)
        {
            flywheel.setVelocity(1640);
        }
        else
        {
            flywheel.setVelocity(1580);
        }
        if(gamepad2.left_bumper)
        {
            pGate.setPosition(0.4);
        }
        else
        {
            pGate.setPosition(0.5);
        }
        if (gamepad2.dpad_down)
        {
            intake.setPower(0.5);
        }
        else if (gamepad2.dpad_up)
        {
            intake.setPower(0);
        }
        else
        {
            intake.setPower(-0.5);
        }
        telemetry.addData("X Position", pinpointX);
        telemetry.addData("Y Position", pinpointY);
        telemetry.addData("Raw Heading", rawHeading);
        telemetry.addData("Heading Offset", headingOffset);
        telemetry.addData("Corrected Heading", heading);
        telemetry.update();
    }
}
