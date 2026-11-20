package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@Disabled
@TeleOp(name = "Pinpoint Heading Test")
public class TestTeleop extends OpMode {

    private GoBildaPinpointDriver pinpoint;

    @Override
    public void init() {

        pinpoint = hardwareMap.get(
                GoBildaPinpointDriver.class, "pinpoint"
        );

        pinpoint.setEncoderResolution(
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        );

        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED
        );

        pinpoint.setOffsets(-67, -125, DistanceUnit.MM);

        pinpoint.resetPosAndIMU();
    }

    @Override
    public void loop() {

        pinpoint.update();

        double heading =
                pinpoint.getHeading(AngleUnit.DEGREES);

        telemetry.addData("Heading", heading);
        telemetry.addData("X Position",
                pinpoint.getPosX(DistanceUnit.INCH));
        telemetry.addData("Y Position",
                pinpoint.getPosY(DistanceUnit.INCH));

        telemetry.update();
    }
}