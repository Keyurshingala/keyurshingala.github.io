class MyAdapter<T>(private val ctx: Context, private val list: List<T>, private val onClick: (T) -> Unit) : RecyclerView.Adapter<MyAdapter.VH>() {

    override fun onBindViewHolder(holder: VH, pos: Int) {
        val bind = holder.binding
        val data = list[pos] as Abc
        
        val img = data.s
        
        bind.root.setOnClickListener {
            onClick(list[pos])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(RvLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    class VH(var binding: RvLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = list.size
}

data class Abc(var s: String)

//-----
data class Tp<out Z, out B, out C>(val first: Z, val second: B, val third: C) //just Demo Exp. cals can be made this way also
//-----



val g = Gson()

fun Any.toGson() {
    g.toJson(this)
}

fun Exception.print() {
    if (BuildConfig.DEBUG) {
        printStackTrace()
        "$message | ${stackTrace[0]} | ${javaClass.name}".log()
    }
}

fun <T> T.log() {
    if (BuildConfig.DEBUG)
        try {
            Log.wtf(TAG, "$this")
        } catch (e: Exception) {
            e.print()
        }
}

//use it inside BaseAc
fun <T> T.tos() = Toast.makeText(this, "$this", Toast.LENGTH_SHORT).show()


fun View.visible() {
    this.visibility = VISIBLE
}

fun View.gon() {
    this.visibility = GONE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun ImageView.load(any: Any?, withCrossFade: Boolean = false) {
    Glide.with(this).load(any).placeholder(R.drawable.ic_img_loading).error(R.drawable.ic_error).apply {
        if (withCrossFade)
            transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
        else
            into(this@load)
    }
}

/** if dose not work call this method inside View.post() method **/
fun View.toBmp(): Bitmap {
    val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    layout(left, top, right, bottom)
    draw(Canvas(b))
    return b
}


 private fun pickAndAddImg() {
        launcher.launch(Intent.createChooser(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), "Select Picture"))
 }

private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val uri: Uri = result.data!!.data!!

            val destination = File(filesDir.absolutePath + "/MyLogo/" + uri.path!!.split("/").last())

            if (destination.exists() && destination.length() != 0L)
                tos("Logo already added")

            else {
                File(destination.parent!!).let { if (!it.exists()) it.mkdir() }

                destination.createNewFile()
                destination.setLastModified(System.currentTimeMillis())

                contentResolver.openInputStream(uri).use { inputStream ->
                    destination.outputStream().use { output ->
                        inputStream!!.copyTo(output)

                        MyManager.setMyLogo(destination.path)
                        setMyLogo()
                        tos("Logo Added")
                    }
                }
            }
        }
    }



