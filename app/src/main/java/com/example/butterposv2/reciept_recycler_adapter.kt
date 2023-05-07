package com.example.butterposv2

import android.view.*
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.ui.order.OrderFragment

//class ThirdAdapter : RecyclerView.Adapter<ThirdAdapter.ViewHolder>() {
//class drinkRecyclerAdapter( var drinkList: ArrayList<DrinkSelections>) : RecyclerView.Adapter<drinkRecyclerAdapter.DrinkViewHolder>() {
class reciept_recycler_adapter(var dataList: List<Pair<String,Int>>) : RecyclerView.Adapter<reciept_recycler_adapter.RecieptViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null
    //private var dataList: List<Pair<String, Int>> = emptyList()
    inner class RecieptViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemName :TextView = itemView.findViewById(R.id.reciept_item_name)
        var itemPrice:TextView = itemView.findViewById(R.id.reciept_item_price)
        init {
            itemView.setOnClickListener {
                // Handle single click if needed
            }

            itemView.setOnTouchListener(object : View.OnTouchListener {
                private val gestureDetector = GestureDetectorCompat(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onDoubleTap(e: MotionEvent): Boolean {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            val data = dataList[position]
                            val theListenter = onItemClickListener?.onItemDoubleClick(data)
                            onItemClickListener?.onItemDoubleClick(data)
                            return true
                        }
                        return false
                    }
                })

                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event != null) {
                        gestureDetector.onTouchEvent(event)
                    }
                    return true
                }
            })
        }

        // Rest of the ViewHolder code...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): reciept_recycler_adapter.RecieptViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order, parent, false)
        return RecieptViewHolder(view)
    }

    override fun onBindViewHolder(holder:RecieptViewHolder, position: Int) {
        val data = dataList[position]
        val currentItem = dataList[position]
        //stringValueTextView.text = data.first
        holder.itemName.text = data.first
        holder.itemPrice.text = data.second.toString()

        val myContext = holder.itemView.context


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setData(dataList: List<Pair<String, Int>>) {
        this.dataList = dataList
    }

    fun setOnItemClickListener(listener: OrderFragment) {
        onItemClickListener = listener
    }


}

