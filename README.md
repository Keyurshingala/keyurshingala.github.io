


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

