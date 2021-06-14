package com.example.application.ui.login;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import android.app.Service;
        import android.content.Intent;
        import android.os.IBinder;
        import android.util.Log;
        import android.widget.Toast;

    class BackGroundControl extends Service {
    private boolean isRunning;

    Thread mThread;

    ArrayList<String> mPackageFilter;
    private static boolean mPassApp = false;

    private final static String TAG = "App_log";

    public void runLog() {

        Process process = null;
        Log.i(TAG, "run");
        try {
            Runtime.getRuntime().exec("/system/bin/logcat -c"); // 로그 플러쉬
            process = Runtime.getRuntime().exec(
                    "/system/bin/logcat -b main -s ActivityManager:I");
            Log.i(TAG, getPackageName());
        } catch (IOException e) {
            Log.e(getPackageName(), e.toString());
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream())); // 프로세스 실행정보..
            Log.i(TAG, "" + reader);
            String line;
            while (!isRunning) {
                line = reader.readLine();
                Log.i(TAG, line);

                if (!line.contains("Starting: Intent")) {
                    mPassApp = true;
                } else {
                    for (int i = 0; i < mPackageFilter.size(); i++) {
                        if (line.contains("cmp=" + mPackageFilter.get(i))) {
                            mPassApp = true;
                            break;
                        }
                    }
                }

                if (!mPassApp) {
                    popupLock();
                    Log.i(TAG, "background");
                    mPassApp = true;
                } else {
                    mPassApp = false;
                }
            }
        } catch (IOException e) {
            Log.e(getPackageName(), e.toString());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                Log.e(getPackageName(), e.toString());
            }
            process.destroy();
            process = null;
            reader = null;
        }
    }

    public void popupLock() {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

    }

    Runnable worker = new Runnable() {
        public void run() {
            runLog();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(BackGroundControl.this, "제어를 종료합니다.", Toast.LENGTH_LONG)
                .show();
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "Service Start");
        // run
        //mPackageFilter = intent.getStringArrayListExtra("PACKAGE_FILTER");
        // 실행가능한 패키지 리스트 가져옴


        mPackageFilter = new ArrayList<String>();
        mPackageFilter.add("com.android.browser");
        mPackageFilter.add("com.android.launcher");
        mPackageFilter.add("com.android.settings/.Settings");


        mThread = new Thread(worker); // 스레드 생성
        mThread.setDaemon(true); // 데몬으로 설정
        mThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}