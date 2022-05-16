package com.example.fly.z_activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.fly.R;
import com.example.fly.databinding.ActivityStatusBinding;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StatusActivity extends BaseAc {

    ActivityStatusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                if (result.getData().getData().toString().contains(".Statuses"))
                    pref.putString("uri", result.getData().getData() + "");

                getContentResolver().takePersistableUriPermission(result.getData().getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                DocumentFile documentFile = DocumentFile.fromTreeUri(StatusActivity.this, result.getData().getData());

                if (documentFile != null) {

                    for (DocumentFile df : documentFile.listFiles()) {
                        final String path = df.getUri().getPath();
                        Glide.with(StatusActivity.this).asBitmap().load(df.getUri())
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                        if (!df.getName().endsWith(".nomedia")) {
                                            if (df.getName().endsWith(".mp4")) {
                                                saveVideoFromTreeUri(df.getUri());
                                            } else
                                                savePoster(resource, Bitmap.CompressFormat.PNG, 100);
                                        }
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });
                    }

                } else {
                    somethingWentWrong();
                }
            } else {
                somethingWentWrong();
            }
        });

        binding.btn.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                if (DocumentFile.fromTreeUri(StatusActivity.this, Uri.parse(pref.getString("uri"))).canRead()) {

                } else {
                    Uri finalUri = DocumentFile
                            .fromTreeUri(
                                    StatusActivity.this,
                                    Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses")
                            ).getUri();

                    launcher.launch(
                            new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
                                    .putExtra(DocumentsContract.EXTRA_INITIAL_URI, finalUri)
                                    .addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    );
                }
            }
        });
    }

    private void saveVideoFromTreeUri(Uri uri) {

        String videoFileName = "video_" + System.currentTimeMillis() + ".mp4";

        ContentValues valuesvideos;
        valuesvideos = new ContentValues();
        valuesvideos.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/");
        valuesvideos.put(MediaStore.Video.Media.TITLE, videoFileName);
        valuesvideos.put(MediaStore.Video.Media.DISPLAY_NAME, videoFileName);
        valuesvideos.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        valuesvideos.put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        valuesvideos.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
        valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 1);
        ContentResolver resolver = getContentResolver();
        Uri collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //all video files on primary external storage
        Uri uriSavedVideo = resolver.insert(collection, valuesvideos);

        ParcelFileDescriptor pfd;

        try {
            pfd = getContentResolver().openFileDescriptor(uriSavedVideo, "w");

            assert pfd != null;
            FileOutputStream out = new FileOutputStream(pfd.getFileDescriptor());

            // Get the already saved video as fileinputstream from here
            InputStream in = getContentResolver().openInputStream(uri);

            byte[] buf = new byte[8192];

            int len;
            int progress = 0;
            while ((len = in.read(buf)) > 0) {
                progress = progress + len;
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
            pfd.close();
            valuesvideos.clear();
            valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 0);
            valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 0); //only your app can see the files until pending is turned into 0

            getContentResolver().update(uriSavedVideo, valuesvideos, null, null);
            File file = new File(Environment.getExternalStorageDirectory() + "/Movies/" + videoFileName);
            Log.e(TAG, "saveVideo: " + file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "saveVideo: " + e.getMessage());
        }
    }
}