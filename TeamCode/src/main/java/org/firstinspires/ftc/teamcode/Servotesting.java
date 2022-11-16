package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class Servotesting extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;


    private CRServo Right;
    private CRServo Left;
    private DcMotor Crain;
    private DcMotor Spin;


    public void runOpMode() throws InterruptedException {



        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        Right = hardwareMap.get(CRServo.class,"Rights");
        Left = hardwareMap.get(CRServo.class, "Lefts");
        Crain = hardwareMap.get(DcMotor.class, "Crain");
        Spin = hardwareMap.get(DcMotor.class, "Spin");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            double turn;
            double throttle;
            boolean strafeLeft;
            boolean strafeRight;

            boolean pickup;
            boolean dropoff;
            double crainpower;
            boolean turning;

            throttle = gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            strafeLeft = gamepad1.left_bumper;
            strafeRight = gamepad1.right_bumper;

            crainpower = gamepad2.left_stick_y;
            pickup = gamepad2.x;
            dropoff = gamepad2.y;
            turning = gamepad2.b;

            if (strafeLeft) {
                frontLeft.setPower(-0.75);
                frontRight.setPower(0.75);
                backLeft.setPower(0.75);
                backRight.setPower(-0.75);
            } else if (strafeRight) {
                frontLeft.setPower(-0.75);
                frontRight.setPower(0.75);
                backLeft.setPower(0.75);
                backRight.setPower(-0.75);
            }


            frontLeft.setPower(throttle);
            frontRight.setPower(throttle);
            backLeft.setPower(throttle);
            backRight.setPower(throttle);

            frontLeft.setPower(-turn);
            frontRight.setPower(turn);
            backLeft.setPower(-turn);
            backRight.setPower(turn);

            Crain.setPower(crainpower);

            if (pickup==true) {
                Right.setPower(1);
                Left.setPower(-1);
            }

            if (dropoff==true){
                Right.setPower(-1);
                Left.setPower(1);

            }

            if (dropoff==false && pickup==false){
                Right.setPower(0);
                Left.setPower(0);

            }


            if (turning){
                Spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Crain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Spin.setTargetPosition(0);
                Crain.setTargetPosition(0);
                Spin.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Crain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Spin.setPower(1);
                Crain.setPower(1);
                while(Spin.isBusy()){

                }
                Crain.setTargetPosition(1);
                Crain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Crain.setPower(1);
                while (Crain.isBusy()){

                }
            }

        }


    }
}