package whyraya.mvvm.di.retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import whyraya.mvvm.di.retrofit.R
import whyraya.mvvm.di.retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.itemAnimator = DefaultItemAnimator()
        binding.list.adapter = adapter

        viewModel.message.observe(this, Observer { it?.let { Log.e("Test222", "error : $it") } })

        viewModel.myData.observe(this, Observer { it?.let { adapter.updateData(it) } })
    }
}
