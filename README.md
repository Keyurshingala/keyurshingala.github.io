
//Dialog with Snackbar


    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/transparent"
        android:orientation="vertical">

        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="@dimen/_16sdp"
            app:cardCornerRadius="@dimen/_4sdp"
            app:cardElevation="@dimen/_2sdp"
            app:cardUseCompatPadding="true">

            <RelativeLayout

                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <ProgressBar
                    android:id="@+id/pb"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_centerHorizontal="true"
                    android:layout_centerVertical="true"
                    android:visibility="gone"
                    tools:visibility="visible" />

                <LinearLayout
                    android:id="@+id/ll"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:clipToPadding="false"
                    android:orientation="vertical"
                    android:paddingStart="@dimen/_12sdp"
                    android:paddingTop="@dimen/_8sdp"
                    android:paddingEnd="@dimen/_12sdp"
                    android:paddingBottom="@dimen/_8sdp"
                    android:visibility="invisible"
                    tools:visibility="visible">          

                    <ImageView
                        android:id="@+id/ivClose"
                        android:layout_width="@dimen/_40sdp"
                        android:layout_height="@dimen/_24sdp"
                        android:layout_gravity="center_horizontal"
                        android:layout_marginBottom="@dimen/_minus8sdp"
                        android:src="@drawable/ic_close_wallet"
                        app:tint="@color/gray_d" />
                </LinearLayout>

                <androidx.coordinatorlayout.widget.CoordinatorLayout
                    android:id="@+id/clVisibleRoot"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_alignBottom="@id/ll" />
            </RelativeLayout>
        </com.google.android.material.card.MaterialCardView>

    </LinearLayout>

    private void showWallet() {
        dialog = new Dialog(this);
        bind = WalletDialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(bind.getRoot());

        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(true);

        bind.ivClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();

        //network call if needed
    }
    
    //if network all failed show option to try again
    public void tryAgain(String s, String method) {
        Snackbar snackbar = Snackbar.make(bind.clVisibleRoot, s, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Try Again", view -> {
            switch (method) {
                case method:
                    method();
                    break;
            }
        });
        snackbar.show();
    }
    
    



//Bitmap to File
        
      public File getFileOfBit(Bitmap mBitmap) {

        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), getString(R.string.app_name));

        if (!myDir.exists()) myDir.mkdirs();

        String fileName = System.currentTimeMillis() / 1000 + ".jpeg";

        File file = null;

        try {
            file = new File(myDir, fileName);
            if (file.exists())
                file.delete();

            FileOutputStream out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 40, out);
            out.flush();
            out.close();

            Log.e(TAG, "getFileOfBit: " + file.getPath());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e(TAG, "getFileOfBit: " + file.getPath());
        return file;
    }
    

//todo AsyncTask Alternative

            progress.show();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                public void run() {
                    //do in background
                    getContactList();
                    System.out.println("Asynchronous task");
    

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            Log.i(TAG, "run: " + contactList.size());
                            //post Execute
                        }
                    });
                }
            });
            // executorService.shutdown();
            
//selector for Rg

        <?xml version="1.0" encoding="utf-8"?>
        <selector xmlns:android="http://schemas.android.com/apk/res/android">

            <item android:state_checked="true">
                <shape>
                    <solid android:color="@color/blue" />
                    <corners android:radius="@dimen/_12sdp" />
                    <padding android:bottom="@dimen/_8sdp" android:left="@dimen/_18sdp" android:right="@dimen/_18sdp" android:top="@dimen/_8sdp" />
                </shape>
            </item>

            <item android:state_pressed="true">
                <shape>
                    <solid android:color="#200C1947" />
                    <corners android:radius="@dimen/_12sdp" />
                    <padding android:bottom="@dimen/_8sdp" android:left="@dimen/_18sdp" android:right="@dimen/_18sdp" android:top="@dimen/_8sdp" />
                </shape>

            </item>

            <item android:state_checked="false">
                <shape>
                    <solid android:color="@color/btn_def" />
                    <corners android:radius="@dimen/_12sdp" />
                    <padding android:bottom="@dimen/_8sdp" android:left="@dimen/_18sdp" android:right="@dimen/_18sdp" android:top="@dimen/_8sdp" />
                </shape>
            </item>
        </selector>
            
//Image Blure With Picasso

        implementation 'com.squareup.picasso:picasso:2.71828'
        
       
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.renderscript.Allocation;
        import android.renderscript.Element;
        import android.renderscript.RenderScript;
        import android.renderscript.ScriptIntrinsicBlur;
        import com.squareup.picasso.Transformation;

        public class BlurTransformation implements Transformation {

            RenderScript rs;

            public BlurTransformation(Context context) {
                super();
                rs = RenderScript.create(context);
            }

            @Override
            public Bitmap transform(Bitmap bitmap) {
                // Create another bitmap that will hold the results of the filter.
                Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

                // Allocate memory for Renderscript to work with
                Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
                Allocation output = Allocation.createTyped(rs, input.getType());

                // Load up an instance of the specific script that we want to use.
                ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
                script.setInput(input);

                // Set the blur radius
                script.setRadius(10);

                // Start the ScriptIntrinisicBlur
                script.forEach(output);

                // Copy the output to the blurred bitmap
                output.copyTo(blurredBitmap);

                bitmap.recycle();

                return blurredBitmap;
            }

            @Override
            public String key() {
                return "blur";
            }
        }
        
        Picasso.get().load(R.drawable.black_background)
        .transform(new BlurTransformation(context))
        .into(bind.blureView);


//Backround Blur

        implementation 'com.eightbitlab:blurview:1.6.6'

         <eightbitlab.com.blurview.BlurView
                        android:id="@+id/blureView"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent" />

        View decorView = activity.getWindow().getDecorView();
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        Drawable windowBackground = decorView.getBackground();

        bind.blureView.setupWith(bind.getRoot())
                 .setFrameClearDrawable(windowBackground)
                 .setBlurAlgorithm(new RenderScriptBlur(activity))
                 .setBlurRadius(10f) //radius
                 .setBlurAutoUpdate(true)
                 .setHasFixedTransformationMatrix(false); // Or false if it's in a scrolling container or might be animated
                 
                 
//Full Screen
             
     //in values/themes.xml
     <style name="Theme.App" parent="Theme.MaterialComponents.Light.NoActionBar.Bridge">
        <!--        status bar-->
        <item name="colorPrimaryDark">@color/st</item>
        <!--        button-->
        <item name="colorPrimary">@color/btn</item>
        <!--        courser  switch  checkBox-->
        <item name="colorAccent">@color/accent</item>
     </style>

    <style name="Theme.WindowFullscreen" parent="Theme.App">
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowFullscreen">true</item>
    </style>
    
    //in AndroidManifest.xml
    <application
        ...
        android:theme="@style/Theme.App">
                 
        <activity
            android:name=".SplashActivity"
            android:exported="true"
            android:theme="@style/Theme.WindowFullscreen">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity
            android:name=".LogInActivity"
            android:exported="true"
            android:theme="@style/Theme.WindowFullscreen">
        </activity>
      
    </application>
    
