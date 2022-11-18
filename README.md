//download with notification

    private fun enqueueDownload(context: Context) {
        val fileName = "${System.currentTimeMillis()}.png"
        val destination = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).path}/$fileName"

        val file = File(destination)
        if (file.exists()) file.delete()

        val request = DownloadManager.Request(Uri.parse(url))
                .setMimeType("*/*")
                .setTitle(fileName)
                .setDescription("downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationUri(Uri.parse("file://$destination"))

        (getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(request)
        "downloading".tosL()
    }


//check if srvice is running or not

        fun isServiceRunning(ctx: Context, serviceClass: Class<*>): Boolean {
            for (service in (ctx.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager).getRunningServices(Int.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) return true
            }
            return false
        }
        
        

//check net vpn proxy

        public boolean hasInternetConnect() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getNetworkCapabilities(cm.getActiveNetwork()).hasTransport(NetworkCapabilities.TRANSPORT_VPN)) return false;

        if (cm.getDefaultProxy() != null) return false;

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        if (isCertificateInstall()) return false;

        if (haveConnectedWifi || haveConnectedMobile) {

        } else {
            tos("Network Not Available");
        }

        return haveConnectedWifi || haveConnectedMobile;
        }
    
        public boolean isCertificateInstall() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
            return false;
        }

        return false;
        }
        

//emulator detection
    
    //by facebook
    @JvmStatic
    fun isEmulator(): Boolean {
      return Build.FINGERPRINT.startsWith("generic") ||
          Build.FINGERPRINT.startsWith("unknown") ||
          Build.MODEL.contains("google_sdk") ||
          Build.MODEL.contains("Emulator") ||
          Build.MODEL.contains("Android SDK built for x86") ||
          Build.MANUFACTURER.contains("Genymotion") ||
          Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
          "google_sdk" == Build.PRODUCT
    }

//root detedction

    //by oneSignal
    static boolean isRooted() {
        String[] places = new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/",                                                  "/system/bin/failsafe/", "/data/local/"};

        try {
            String[] var1 = places;
            int var2 = places.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String where = var1[var3];
                if ((new File(where + "su")).exists()) {
                    return true;
                }
            }
        } catch (Throwable var5) {
        }
        return false;
    }
    
    //by crashlytics
    public static boolean isRooted(Context context) {
        boolean isEmulator = isEmulator(context);
        String buildTags = Build.TAGS;
        if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            return true;
        } else {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            } else {
                file = new File("/system/xbin/su");
                return !isEmulator && file.exists();
            }
        }
    }
    
    

//image loading with progressbasr or gif

            val cpd = CircularProgressDrawable(this)
            cpd.strokeWidth = 5f
            cpd.centerRadius = 30f
            cpd.setColorSchemeColors(Color.GREEN,Color.CYAN,Color.LTGRAY)
            cpd.start()

            Glide.with(this)
                    .load("https://preview.redd.it/c3uhsgo1vx541.jpg?auto=webp&s=a45b583ebf921d3ad1649e77ad05e55226140120")
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(cpd)                                    // comment when applying gif
    //              .thumbnail(Glide.with(activity).load("https://i.gifer.com/ZKZx.gif"))   //for gif
    //              .fitCenter()                                         //may be optional
                    .error(R.drawable.liked)
                    .into(bind.iv)



//pick font and copy to catch dir

                         val fontLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                                if (result.resultCode == Activity.RESULT_OK) {

                                    val uri: Uri = result.data!!.data!!
                                   // val source = File(FileUtils.getPath(requireActivity(), uri))
                                    val destination = File(requireActivity().cacheDir.absolutePath + "/font/" + uri.path!!.split("/").last())

                                    if (destination.exists() && destination.length() != 0L)
                                        "File exists".tos()

                                    else {
                                        File(destination.parent!!).let { if (!it.exists()) it.mkdir() }
                                        destination.createNewFile()

                                        requireActivity().contentResolver.openInputStream(uri).use { inputStream ->
                                            destination.outputStream().use { output ->
                                                inputStream!!.copyTo(output)
                                            }
                                        }
                                    }
                                }
                            }

                            bind.btnAddFont.setOnClickListener {
                                fontLauncher.launch(
                                        Intent(Intent.ACTION_GET_CONTENT)
                                                .setType("*/*")
                                                .putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("font/ttf", "font/otf", "application/vnd.ms-fontobject",   "font/woff", "font/woff2"))
                                )
                            }
                

//make list of time interval

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)

            val dateStart = sdf.parse("${startDate()} ${startHm()}") as Date
            val dateEnd = sdf.parse("${startDate()} 24:00") as Date

            ("Date Start: $dateStart").log()
            ("Date End: $dateEnd").log()

            var list = arrayListOf<String>()

            var dif: Long = dateStart.time
            while (dif < dateEnd.time) {
                val slot = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(Date(dif))
                "Hour Slot --> $slot".log()
                list.add(slot)
                dif += 1800000 //for 1 hour make it 3600000
            }
            
            // currentDate
            fun startDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())

            //gives HH:00 or HH:30 only (Ex. 01:30, 02:00 etc..)
            fun startHm(): String {
                val h = SimpleDateFormat("HH", Locale.ENGLISH).format(Date())

                SimpleDateFormat("mm", Locale.ENGLISH).format(Date()).let {
                    return if (it.toInt() > 30) (h+1)+":00" else "$h:30"
                }
            }
            
            //extras
            fun getSpecificTimeInMillis() : Long = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH).parse(startDate() + " 11:30:00 pm")!!.time


// AlarmManager

    public void startAlert() {
        int second = Integer.parseInt(binding.et.getText().toString());

        ((AlarmManager) getSystemService(ALARM_SERVICE))
                .set(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + (second * 1000L),
                        PendingIntent.getBroadcast(
                                getApplicationContext(),
                                111,
                                new Intent(this, MyBroadcastReceiver.class),
                                FLAG_IMMUTABLE)
                );
        Toast.makeText(this, "Alarm set in " + second + " seconds", Toast.LENGTH_SHORT).show();
    }

      public class MyBroadcastReceiver extends BroadcastReceiver {

          @Override
          public void onReceive(Context context, Intent intent) {
              rcv();
          }

          private void rcv() {
              new Handler(Looper.getMainLooper()).postDelayed(() -> {
                  Log.i("TAG", "run: ");
                  rcv();
              },2000);
          }
      }

      in AndroidManifest.xml

      <receiver android:name="MyBroadcastReceiver" />



//App link assitant steps

      upload .well-known/assetlinks.json folder in website
      iside assetlinks.json put following	
      [{
        "relation": ["delegate_permission/common.handle_all_urls"],
        "target": {
          "namespace": "android_app",
          "package_name": "com.example.yourapp",
          "sha256_cert_fingerprints":
          ["98:56:DB:F3:C0:FC:BA: -------------  sha 256 ------------ :B5:13:73:36:4E:53:A6:F3"]
        }
      }]

      now in android studio tools>App Link Assistant> click

      1 Add URL intent filters -> put path of assetlinks.json in Check URL Mapping
            Ex. = https://YourHost.com/.well-known/assetlinks.json

            add activity that will onpen on link click

            and make sure below is in menifst of added activity

            <intent-filter>
                  <action android:name="android.intent.action.VIEW" />

                      <category android:name="android.intent.category.DEFAULT" />
                      <category android:name="android.intent.category.BROWSABLE" />

                      <data
                          android:host="YourHost.com"
                          android:scheme="https" />
               </intent-filter>

      2 Add logica t handle the intent -> put below in selected activity

            // ATTENTION: This was auto-generated to handle app links.
              Intent appLinkIntent = getIntent();
              String appLinkAction = appLinkIntent.getAction();
              Uri appLinkData = appLinkIntent.getData();

      3 Associate website -> you will know what to do

      4 Test on device or emulator -> in URL must start with "YourHost.com" after put whatever you want
            Ex. https://YourHost.com/whatever you want

            That's It!!

//for light mode only

      //themes.xml
      //Base application theme
      <style name="Theme.App" parent="Theme.MaterialComponents.Light.NoActionBar.Bridge">
      
      <item name="android:forceDarkAllowed" tools:targetApi="q">false</item>
      
      //AndroidManifest.xml
      <activity
      android:configChanges="uiMode">
      ...
      </activity>

//pager adapter

     class FGPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
              private var fragmentList: MutableList<Fragment> = ArrayList()

              fun add(fragment: Fragment) {
                  fragmentList.add(fragment)
              }

              override fun getItem(position: Int): Fragment {
                  return fragmentList[position]
              }

              override fun getCount() = fragmentList.size
          }



//save vide from tree uri

          private void saveVideoFromTreeUri(Uri uri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            String videoName = System.currentTimeMillis() + ".mp4";

            ContentValues valuesvideos = new ContentValues();
            valuesvideos.put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
            valuesvideos.put(MediaStore.Video.Media.TITLE, videoName);
            valuesvideos.put(MediaStore.Video.Media.DISPLAY_NAME, videoName);
            valuesvideos.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
            valuesvideos.put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
            valuesvideos.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
            valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 1);
            ContentResolver resolver = getContentResolver();
            //for  [DCIM, Movies, Pictures] use MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY) as insert uri
            Uri uriSavedVideo = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, valuesvideos);

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
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + videoName);

                MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, new String[]{"video/*"}, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//open Dir with Doc Tree

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
                      
                      

//Api Client In Test Be Carefull

          public class ApiClient<T> {

              Activity activity;
              Dialog progress;
              DialogNoNet dialogNoNet;

              public ServiceGenerator<T> serviceGenerator;

              public interface ServiceGenerator<T> {
                  void OnSuccess(T obj);
              }

              public void callApiWithDialog(Activity activity, View root, Call<T> apiCall, ServiceGenerator<T> serviceGenerator) {
                  this.activity = activity;
                  this.serviceGenerator = serviceGenerator;

                  if (hasInternetConnect()) {
                      dismissNoNet();
                      showProgress();
                      apiCall.clone().enqueue(new Callback<T>() {
                          @Override
                          public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                              new Handler(Looper.getMainLooper()).postDelayed(() -> dismissProgress(), 200);

                              if (response.isSuccessful() && response.body() != null) {
                                  serviceGenerator.OnSuccess(response.body());

                              } else {
                                  Snackbar.make(activity.findViewById(android.R.id.content), somethingWentWrong, Snackbar.LENGTH_INDEFINITE)
                                          .setAction("Try Again", view -> callApiWithDialog(activity, root, apiCall, serviceGenerator)).show();
                              }
                          }

                          @Override
                          public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                              dismissProgress();
                              t.printStackTrace();

                              Snackbar.make(activity.findViewById(android.R.id.content), somethingWentWrong, Snackbar.LENGTH_INDEFINITE)
                                      .setAction("Try Again", view -> callApiWithDialog(activity, root, apiCall, serviceGenerator)).show();

                          }
                      });

                  } else {
                      showNoNet();
                      dialogNoNet.setOnRetryClickListener(new DialogNoNet.RootClickEvent() {
                          @Override
                          public void onRetryClick(@NonNull Dialog dialog) {
                              callApiWithDialog(activity, root, apiCall, serviceGenerator);
                          }
                      });
                  }
              }

              public void callApiWithBar(Activity activity, View root, ProgressBar pb, Call<T> apiCall, ServiceGenerator<T> serviceGenerator) {
                  this.activity = activity;
                  this.serviceGenerator = serviceGenerator;

                  if (hasInternetConnect()) {
                      dismissNoNet();
                      pb.setVisibility(View.VISIBLE);
                      apiCall.clone().enqueue(new Callback<T>() {
                          @Override
                          public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                              pb.setVisibility(View.GONE);

                              if (response.isSuccessful() && response.body() != null) {
                                  serviceGenerator.OnSuccess(response.body());

                              } else {
                                  Snackbar.make(activity.findViewById(android.R.id.content), somethingWentWrong, Snackbar.LENGTH_INDEFINITE)
                                          .setAction("Try Again", view -> callApiWithBar(activity, root, pb, apiCall, serviceGenerator)).show();
                              }
                          }

                          @Override
                          public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                              pb.setVisibility(View.GONE);
                              t.printStackTrace();

                             Snackbar.make(
                              root, t instanceof SocketTimeoutException ? "Time out" : somethingWentWrong, Snackbar.LENGTH_INDEFINITE
                              ).setAction(
                                   t instanceof SocketTimeoutException ? "Try again" : "dismiss", view -> {
                                if (t instanceof SocketTimeoutException) {
                                    callApiWithBar(activity, root, pb, apiCall, serviceGenerator);
                                }
                            }).show();
                              Snackbar.make(activity.findViewById(android.R.id.content), somethingWentWrong, Snackbar.LENGTH_INDEFINITE)
                                      .setAction("Try Again", view -> callApiWithBar(activity, root, pb, apiCall, serviceGenerator)).show();

                          }
                      });

                  } else {
                      showNoNet();
                      dialogNoNet.setOnRetryClickListener(new DialogNoNet.RootClickEvent() {
                          @Override
                          public void onRetryClick(@NonNull Dialog dialog) {
                              callApiWithBar(activity, root, pb, apiCall, serviceGenerator);
                          }
                      });
                  }
              }


              public static Api getService() {
                  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();     //todo remove this line in production
                  interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);               //todo remove this line in production

                  OkHttpClient okClient = new OkHttpClient.Builder()
                          .addInterceptor(chain -> {
                              Request originalRequest = chain.request();
                              Request.Builder requestBuilder = originalRequest.newBuilder()
                                      .addHeader("Cache-Control", "no-cache")
                                      .method(originalRequest.method(), originalRequest.body());
                              Request request = requestBuilder.build();
                              return chain.proceed(request);
                          })
                          .addInterceptor(interceptor)                                    //todo remove this line in production
                          .connectTimeout(10, TimeUnit.SECONDS)
                          .readTimeout(10, TimeUnit.SECONDS)
                          .build();


                  return new Retrofit.Builder()
                          .baseUrl(cons.BASE_URL)
                          .addConverterFactory(GsonConverterFactory.create())
                          .client(okClient)
                          .build()
                          .create(Api.class);
              }

              private void initNoNetDialog() {
                  dialogNoNet = new DialogNoNet(activity);
              }

              private void showNoNet() {
                  if (dialogNoNet == null) initNoNetDialog();
                  dialogNoNet.show();
              }

              private void dismissNoNet() {
                  if (dialogNoNet != null) dialogNoNet.dismiss();
              }

              private void initProgressDialog() {
                  progress = new Dialog(activity);
                  progress.setContentView(DialogProgressBinding.inflate(activity.getLayoutInflater()).getRoot());
                  int widthAndHeight = WindowManager.LayoutParams.MATCH_PARENT;
                  progress.getWindow().setLayout(widthAndHeight, widthAndHeight);
                  progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                  progress.setCancelable(false);
              }

              public void showProgress() {
                  if (progress == null) initProgressDialog();
                  if (!progress.isShowing()) progress.show();
              }

              public void dismissProgress() {
                  if (progress != null && progress.isShowing()) progress.dismiss();
              }

              public boolean hasInternetConnect() {
                  boolean isWifiConnected = false;
                  boolean isMobileConnected = false;

                  NetworkInfo[] netInfo = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE)).getAllNetworkInfo();

                  for (NetworkInfo ni : netInfo) {
                      if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                          if (ni.isConnected()) isWifiConnected = true;
                      if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                          if (ni.isConnected()) isMobileConnected = true;
                  }
                  return isWifiConnected || isMobileConnected;
              }
          }



//view to bitmap 

     /**
     *if dose not work call this method inside View.post() method
     **/
    public Bitmap bitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(new Canvas(b));
        return b;
    }



//simple animation

            public void slideUpFad(View v) {
                    v.setVisibility(View.VISIBLE);
                    v.setTranslationY(v.getHeight());
                    v.setAlpha(0f);
                    v.animate().setDuration(600)
                            .translationY(0)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                }
                            }).alpha(1f).start();
                }

//Unsplash Api Calling

            private const val BASE_URL = "https://api.unsplash.com/"

            const val AccessKey = "akYdbrQ-RcwiLMcEkHuunsplash_2FUTsHJ25k42aaaO67N4"

            interface Api {
            @GET("search/photos")
            suspend fun getSearchPhotos(@Query("client_id") clientId: String,
                                        @Query("orientation") orientation: String,
                                        @Query("query") searchItem: String?,
                                        @Query("per_page") itemPerPage: Int): Response<SearchPhoto>


            companion object {
                private var okClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(
                        Interceptor { chain: Interceptor.Chain ->
                            val originalRequest = chain.request()
                            chain.proceed(
                                    originalRequest.newBuilder()
                                            .addHeader("Cache-Control", "no-cache")
                                            .method(originalRequest.method, originalRequest.body).build()
                            )
                        })
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) //todo remove this line in production
                        .connectTimeout(12, TimeUnit.SECONDS)
                        .readTimeout(12, TimeUnit.SECONDS)
                        .build()

                val service: Api = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okClient)
                        .build().create(Api::class.java)
                }
            }



//read asset file from asset file path

            public String readAssetsFile(String assetFilePath) {
                    String jsonString = "";
                    try {
                        InputStream is = getAssets().open(assetFilePath);
                        byte[] buffer = new byte[is.available()];
                        is.read(buffer);
                        is.close();
                        jsonString = new String(buffer, StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                    return jsonString;
                }



//save pdf from bitmap

            public File savePdf(Bitmap bitmap) {
                    PdfDocument document = new PdfDocument();
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
                    PdfDocument.Page page = document.startPage(pageInfo);

                    Canvas canvas = page.getCanvas();
                    Paint paint = new Paint();
                    paint.setColor(Color.parseColor("#ffffff"));
                    canvas.drawPaint(paint);

                    bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

                    paint.setColor(Color.BLUE);
                    canvas.drawBitmap(bitmap, 0, 0, null);
                    document.finishPage(page);

                    File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), getString(R.string.app_name));

                    if (!myDir.exists()) myDir.mkdirs();

                    String targetPdf = System.currentTimeMillis() + ".pdf";

                    File filePath = new File(myDir, targetPdf);

                    if (filePath.exists()) filePath.delete();

                    try {
                        document.writeTo(new FileOutputStream(filePath));
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }

                    document.close();

                    return filePath;
                }






//To Get Pic From Camera Full Size

            manifest
            <application...
             <provider
                        android:name="androidx.core.content.FileProvider"
                        android:authorities="${applicationId}.provider"
                        android:exported="false"
                        android:grantUriPermissions="true">
                        <meta-data
                            android:name="android.support.FILE_PROVIDER_PATHS"
                            android:resource="@xml/file_paths" />
                    </provider>
             </application>

           MainActivity
           private static final int REQUEST_IMAGE_CAPTURE = 111;
           String currentPhotoPath;

           private void dispatchTakePictureIntent() {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photoFile = null;
                    try {
                        photoFile = File.createTempFile("Img"/*File Name prefix */, ".jpg"/*Extension suffix */);
                        currentPhotoPath = photoFile.getAbsolutePath();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Log.i(TAG, currentPhotoPath); //  /data/user/0/com.example.xyz/cache/cam_img**some_auto_genreted_numers**.jpg
                    ((ImageView) findViewById(R.id.iv)).setImageURI(Uri.fromFile(new File(currentPhotoPath)));
                }
            }

        res>xml>file_paths.xml
        <?xml version="1.0" encoding="utf-8"?>
        <paths xmlns:android="http://schemas.android.com/apk/res/android">
            <external-files-path name="my_images" path="Pictures" />
        </paths>


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
        
      public File saveBitmap(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), getString(R.string.app_name));

        if (!myDir.exists()) myDir.mkdirs();

        String fileName;
        if (format == Bitmap.CompressFormat.JPEG)
            fileName = System.currentTimeMillis() + ".jpeg";
        else
            fileName = System.currentTimeMillis() + ".png";

        File file = new File(myDir, fileName);
        if (file.exists()) file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(format, quality, out);
            out.flush();
            out.close();

            MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, new String[]{"image/jpeg", "image/png"}, null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return file;
    }
    

//todo AsyncTask Alternative

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                public void run() {
                    //do in background

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //post Execute
                        }
                    });
                }
            });
            
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
