package com.example.flashlight;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        final boolean hasTorch = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);



        if (hasTorch){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isOn) {
                        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        try {
                            String cameraId = cameraManager.getCameraIdList()[0];
                            cameraManager.setTorchMode(cameraId, true);
                        } catch (CameraAccessException e) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No available camera", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), "On", Toast.LENGTH_SHORT);
                        toast.show();
                        isOn = true;
                    }else {
                        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        try {
                            String cameraId = cameraManager.getCameraIdList()[0];
                            cameraManager.setTorchMode(cameraId, false);
                        } catch (CameraAccessException e) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No available camera", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), "Off", Toast.LENGTH_SHORT);
                        toast.show();
                        isOn = false;
                    }
                }
            });
        }


        else{
            Toast toast = Toast.makeText(getApplicationContext(), "No Flash", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
