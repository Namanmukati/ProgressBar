package com.example.progresbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStartProgress;
    ProgressDialog progressBar;
    private  int progressBarStatus=0;
    private Handler progressBarHandler = new Handler();
    private int fileSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonClick();
    }

    private void addListenerOnButtonClick() {
        btnStartProgress = (Button) findViewById(R.id.button);
        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = new ProgressDialog(view.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("File Downloading");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                //reset progress bar and filesize status
                progressBarStatus = 0;
                fileSize = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressBarStatus<100){
                            //performing operation
                            progressBarStatus = doOperation();
                            try{
                                Thread.sleep(10000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            //Updating he progress bar
                            progressBarHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }
                        //performing operation if file is downloading
                        if(progressBarStatus>=100){
                            //sleeping for 1 second after operation completed
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            //close the progress bar
                            progressBar.dismiss();
                        }
                    }
                }).start();

            }//end of onClick method
        });
    }
    //checking how much file is downloaded and updating the filesize
    public int doOperation(){
        //the range of progressDialog starts from 0 to 10000
        while(fileSize <= 10000){
            fileSize++;
            if(fileSize==1000){
                return 10;
            }else if(fileSize==2000){
                return 20;
            }else if(fileSize == 3000){
                return 30;
            } else if (fileSize == 4000) {
                return 40;
            } else if (fileSize == 5000) {
                return 50;
            } else if (fileSize == 6000) {
                return 60;
            } else if (fileSize == 7000) {
                return 70;
            } else if (fileSize== 8000) {
                return 80;
            } else if (fileSize== 9000) {
                return 90;
            }else return 100;
        }//end of while
        return 100;
    }//end of do operation
}