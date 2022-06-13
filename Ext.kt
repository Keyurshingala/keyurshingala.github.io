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




val g = Gson()

fun Any.toGson() {
    g.toJson(this)
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

fun Exception.print() {
    this.printStackTrace()
    (this.message + " | " + this.stackTrace[1].toString() + " | " + this.javaClass.name).log()
}

fun Any.log() {
    Log.wtf(TAG, "$this")
}

fun ImageView.load(any: Any?, withCrossFade: Boolean = false) {
    Glide.with(this).load(any).placeholder(R.drawable.ic_img_loading).error(R.drawable.ic_error).apply {
        if (withCrossFade)
            transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
        else
            into(this@load)
    }
}




