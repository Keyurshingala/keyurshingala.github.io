


//todo AsyncTask Alternative
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
