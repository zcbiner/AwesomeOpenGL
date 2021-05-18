package com.zcbiner.awesomeopengl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zcbiner.awesomeopengl.gl.util.RenderConfig
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        initView()
    }

    private fun initView() {
        recyclerView.adapter = RvAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    inner class RvAdapter : RecyclerView.Adapter<RvViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
            val itemView = LayoutInflater.from(this@MainActivity).inflate(R.layout.view_rv_item, parent, false)
            return RvViewHolder(itemView)
        }

        override fun getItemCount(): Int = RenderConfig.TITLE_CONFIG.size

        override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
            holder.itemView.tag = position
            holder.itemView.setOnClickListener(this@MainActivity)
            holder.tvContent.text = RenderConfig.TITLE_CONFIG[position]
        }

    }

    inner class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }

    override fun onClick(v: View) {
        if (v.tag is Int) {
            GLFragmentActivity.start(this, v.tag as Int)
        }
    }
}