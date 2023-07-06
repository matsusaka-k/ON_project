package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MatchingBefore extends AppCompatActivity {

    private TextView timer;
    private TextView message;
    private TextView userid;
    private TextView username;
    private Button sleepButton;
    private Button wakeButton;
    private Handler handler;
    private Runnable runnable;
    private long startTime;
    private long timeInMillis;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_before);

                timer= findViewById(R.id.timer);
                message = findViewById(R.id.message);
                userid = findViewById(R.id.userId);
                username = findViewById(R.id.userName);
                sleepButton = findViewById(R.id.sleep);
                wakeButton = findViewById(R.id.wake);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        timeInMillis = System.currentTimeMillis() - startTime;
                        updateTimer();
                        handler.postDelayed(this, 1000);
                    }
                };

                sleepButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        sleepTimer();
                    }
                });

                wakeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        wakeTimer();
                    }
                });

            }

            private void sleepTimer() {
                if (!isRunning) {
                    startTime = System.currentTimeMillis() - timeInMillis;
                    handler.postDelayed(runnable, 0);
                    isRunning = true;
                }
            }

            private void wakeTimer() {
                if (isRunning) {
                    handler.removeCallbacks(runnable);
                    isRunning = false;
                    timeInMillis = 0;
                    updateTimer();
                }
            }

            private void updateTimer() {
                int seconds = (int) (timeInMillis / 1000) % 60;
                int minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
                int hours = (int) ((timeInMillis / (1000 * 60 * 60)) % 24);

                String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timer.setText(timeFormatted);
            }
        }