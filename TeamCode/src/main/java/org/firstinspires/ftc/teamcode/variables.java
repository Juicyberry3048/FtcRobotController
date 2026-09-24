package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class variables extends OpMode {
    @Override
    public void init(){
        int teamNumber = 31797; //whole number
        double motorSpeed = 0.6; //decimal numbers
        boolean flywheelReady = true; // true/false statements
        String teamName = "Loose screws"; //words

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Flywheel Ready", flywheelReady);
        telemetry.addData("Team Name", teamName);
    }

    public void loop() {

    }

}
