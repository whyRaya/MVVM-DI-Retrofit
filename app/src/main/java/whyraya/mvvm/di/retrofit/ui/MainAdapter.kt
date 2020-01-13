package whyraya.mvvm.di.retrofit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import whyraya.mvvm.di.retrofit.R
import whyraya.mvvm.di.retrofit.data.MyData
import whyraya.mvvm.di.retrofit.databinding.MainItemBinding

class MainAdapter(private var data: List<MyData>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MainItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.main_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = data[position]
    }

    override fun getItemCount() = data.size

    fun updateData(postList:List<MyData>){
        data = postList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MainItemBinding) :RecyclerView.ViewHolder(binding.root)
}