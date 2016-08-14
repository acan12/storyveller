package core.component;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

/**
 * Created by arysuryawan on 8/13/16.
 */
public class ProgressAction {
    private static int progressBarStatus = 0;
    private static Handler progressBarbHandler = new Handler();

    public static void onProgressStart(final int interval, Context context) {
        final ProgressDialog progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);
        progressBar.setMessage("Loading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < interval) {
//                    progressBarStatus = downloadFile();
                    progressBarStatus++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if (progressBarStatus >= interval) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }
            }
        }).start();
    }
}
