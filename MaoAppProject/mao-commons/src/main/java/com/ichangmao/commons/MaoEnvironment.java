package com.ichangmao.commons;

import android.os.Build;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MaoEnvironment extends Environment {
    private static MaoLog log = MaoLog.getLoger(MaoEnvironment.class);
    private static boolean sSdcardDataSame = IsSdCardDataSamePart();

    public static boolean isExternalStorageEmulated() {
        if (Build.VERSION.SDK_INT >= 11) {
            return Environment.isExternalStorageEmulated();
        }
        return false;
    }

    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Environment.isExternalStorageRemovable();
        }
        return false;
    }

    /**
     * 判断data区和sdcard区是否相同，若相同则为一体机
     *
     * @return
     */
    public static boolean isSdcardDataSame() {
        return sSdcardDataSame;
    }

    private static boolean IsSdCardDataSamePart() {
        if (!isExternalStorageEmulated()) {
            return false;
        }
        boolean bRes = CheckSdcardDataPartSame();
        return bRes;
    }

    private static boolean CheckSdcardDataPartSame() {

        boolean bRes = true;
        File mountsInfo = new File("/proc/self/mounts");

        if (mountsInfo.exists() && !mountsInfo.isDirectory()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(mountsInfo)));
                while (true) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] xs = line.split(" ");
                        if (xs != null && xs.length > 3) {
                            String mp = xs[1];
                            String fsFormat = xs[2];

                            if (fsFormat.startsWith("ext")) {
                                if (mp.startsWith("/storage_int")) {
                                    if (xs[0].startsWith("/dev/block")) {
                                        bRes = false;
                                        break;
                                    }
                                }
                            }

                            if (fsFormat.startsWith("ext")) {
                                if (mp.startsWith("/data/media")) {
                                    if (xs[0].startsWith("/dev/block")) {
                                        bRes = false;
                                        break;
                                    }
                                }
                            }

                        }
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                log.e(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        log.e(e);
                    }
                }
            }
        }
        return bRes;
    }
}
