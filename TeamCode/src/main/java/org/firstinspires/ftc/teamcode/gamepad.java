package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class gamepad extends OpMode {
    @Override
    public void init(){

    }
    public void loop(){
        //reverses the up/down input on left and right stick
        double speedForwardL = -gamepad1.left_stick_y;
        double speedForwardR = -gamepad1.right_stick_y;

        telemetry.addData("lX", gamepad1.left_stick_x);
        telemetry.addData("lY", speedForwardL);
        telemetry.addData("lX", gamepad1.right_stick_x);
        telemetry.addData("lY", speedForwardR);
        telemetry.addData("a button", gamepad1.a);
        telemetry.addData("b button", gamepad1.b);
    }
}
