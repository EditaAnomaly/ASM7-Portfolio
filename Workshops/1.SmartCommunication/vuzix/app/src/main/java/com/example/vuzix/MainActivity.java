package com.example.vuzix;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //       Activity myActivity = this;
//
//        VuzixSpeechClient sc = new VuzixSpeechClient(myActivity);
//
//        // Surround the creation of the VuzixSpeechClient with a try/catch for non-Vuzix hardware
//        VuzixSpeechClient sc;
//        try {
//            sc = new VuzixSpeechClient(myActivity);
//        }
//        catch(RuntimeException e) {
//            if(e.getMessage().equals("Stub!")) {
//                // This is not being run on Vuzix hardware (or the Proguard rules are incorrect)
//                // Alert the user, or insert recovery here.
//            }
//            else {
//                // Other RuntimeException to be handled
//            }
//        }
//// Surround all speech client commands with try/catch for unsupported interfaces
//        try {
//            // sc.anySdkCommandHere();
//        }
//        catch(NoClassDefFoundError e) {
//            // The hardware does not support the specific command expected by the Vuzix Speech SDK.
//            // Alert the user, or insert recovery here.
//        }
    }
}