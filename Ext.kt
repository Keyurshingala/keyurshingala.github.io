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
    printStackTrace()
    (message + " | " + stackTrace[0].toString() + " | " + javaClass.name).log()
}

fun <T> T.log() {
    try {
        Log.wtf(TAG, "$this")
    } catch (e: Exception) {
        e.print()
    }
}

//use it inside BaseAc
fun <T> T.tos() {
        Toast.makeText(this@Base, "$this", Toast.LENGTH_SHORT).show()
    }



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




